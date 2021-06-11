import os
import pdb
import argparse
import numpy as np
import torch
import torch.nn as nn

import models
from utils import dataset, config, utils, dataset_utils

def get_parser():
    parser = argparse.ArgumentParser(description='PyTorch Semantic Segmentation')
    parser.add_argument('--config', type=str, default='config/cityscapes.yaml')
    args = parser.parse_args()
    assert args.config is not None
    cfg = config.load_cfg_from_cfg_file(args.config)
    return cfg


def check(args):
    assert args.data_name in ['crack', 'cityscapes', 'isic']
    assert args.classes >= 1
    assert args.model in ['unet32', 'unet64', 'unet128',
                          'deeplab34','deeplab50','deeplab101',
                          'pspnet18', 'pspnet34', 'pspnet50']
    assert args.loss in ['bce','ce','dice']


def main():
    args = get_parser()
    check(args)
    os.environ["CUDA_VISIBLE_DEVICES"] = ','.join(str(x) for x in args.gpu)
    main_worker(args)


def main_worker(argss):
    global args
    args = argss

    ##### Model #####
    model = models.load_model(args)
    optimizer = torch.optim.Adam(model.parameters(), lr=args.base_lr)
    scheduler = torch.optim.lr_scheduler.StepLR(optimizer, step_size=10, gamma=0.5)
    best_score = 0.0

    if len(args.gpu) > 1:
        model = torch.nn.DataParallel(model.cuda())
    else:
        model = model.cuda()
    print(f"=> creating {args.model} (classes:{args.classes})")

    os.makedirs(args.ckpt_root, exist_ok=True)
    if args.weight:
        if os.path.isfile(args.weight):
            ckpt = torch.load(args.weight, map_location=lambda storage, loc: storage.cuda())
            args.start_epoch = ckpt['epoch']
            model.load_state_dict(ckpt['state_dict'], strict=False)
            optimizer.load_state_dict(ckpt['optimizer'])
            print(f"=> loaded checkpoint '{args.weight}' (epoch {ckpt['epoch']})")
        else:
            print(f"=> no weight found at '{args.weight}'")


    ##### Data #####
    criterion, transforms, calc_score, _, _ = dataset_utils.dataset_utils(args)

    train_loader = torch.utils.data.DataLoader(
                    dataset.Dataset(mode='train', data_root=args.train_root, data_list=args.train_list,
                                    transform=transforms[0], label_transform=transforms[3]),
                    batch_size=args.batch_size, shuffle=True, num_workers=args.workers,
                    pin_memory=True, drop_last=True)

    val_loader = torch.utils.data.DataLoader(
                    dataset.Dataset(mode='val', data_root=args.val_root, data_list=args.val_list,
                                    transform=transforms[1], label_transform=transforms[3]),
                    batch_size=args.batch_size, shuffle=False, num_workers=args.workers,
                    pin_memory=True)


    ##### Train & Validation #####
    del_ckpt = None
    for epoch in range(args.start_epoch, args.epochs):
        loss_train, score_train = train(train_loader, model, criterion,
                                        optimizer, scheduler, calc_score, epoch)
        loss_val, score_val = validate(val_loader, model, criterion, calc_score, epoch)

        if best_score < score_val:
            best_score = score_val
            if del_ckpt:
                os.remove(del_ckpt)
            filename = f'{args.ckpt_root}/{args.data_name}_{args.model}_{epoch+1}.pth'
            torch.save({'epoch': epoch+1,
                        'state_dict': model.state_dict(),
                        'optimizer': optimizer.state_dict()}, filename)
            del_ckpt = filename
            print(f'Saving checkpoint to: {filename}')


def train(train_loader, model, criterion, optimizer, scheduler, calc_score, epoch):
    print(f'\nMode: Train | Epoch: {epoch+1}/{args.epochs} | Model: {args.model} | Data: {args.data_name}')
    loss_meter = utils.AverageMeter()
    score_meter = utils.AverageMeter()

    torch.set_grad_enabled(True)
    model.train()
    for i, data in enumerate(train_loader):
        img, label = data[0].cuda(), data[1].cuda()
        output = model(img)
        if isinstance(output, tuple): # For PSPNet Aux Loss
            loss = criterion(output[0], label) + args.aux_weight*criterion(output[1], label)
            score = calc_score(output[0], label)
        else:
            loss = criterion(output, label)
            score = calc_score(output, label)
        optimizer.zero_grad()
        loss.backward()
        optimizer.step()

        loss_meter.update(loss.item(), img.size(0))
        score_meter.update(score, img.size(0))
        utils.progress_bar(i, len(train_loader),
            f'Loss:{loss_meter.avg:.4f} | Score:{score_meter.avg:.4f}')

    scheduler.step()
    torch.cuda.empty_cache()
    return loss_meter.avg, score_meter.avg


def validate(val_loader, model, criterion, calc_score, epoch):
    print(f'\nMode: Validation  |  Epoch: {epoch+1}/{args.epochs}  |  Model: {args.model}')
    loss_meter = utils.AverageMeter()
    score_meter = utils.AverageMeter()

    torch.set_grad_enabled(False)
    model.eval()
    for i, data in enumerate(val_loader):
        img, label = data[0].cuda(), data[1].cuda()
        output = model(img)
        if isinstance(output, tuple):
            loss = criterion(output[0], label) + args.aux_weight*criterion(output[1], label)
            score = calc_score(output[0], label)
        else:
            loss = criterion(output, label)
            score = calc_score(output, label)

        loss_meter.update(loss.item(), img.size(0))
        score_meter.update(score, img.size(0))
        utils.progress_bar(i, len(val_loader),
            f'Loss:{loss_meter.avg:.4f} | Score:{score_meter.avg:.4f}')

    torch.cuda.empty_cache()
    return loss_meter.avg, score_meter.avg


if __name__ == '__main__':
    main()
