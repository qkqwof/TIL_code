import os
import pdb
import argparse
import torch

import models
from utils import dataset, config, utils, dataset_utils


def get_parser():
    parser = argparse.ArgumentParser(description='PyTorch Semantic Segmentation')
    parser.add_argument('--config', type=str, default='config/crack.yaml')
    args = parser.parse_args()
    assert args.config is not None
    cfg = config.load_cfg_from_cfg_file(args.config)
    return cfg


def check(args):
    assert args.data_name in ['crack', 'cityscapes', 'isic']
    assert args.classes >= 1
    assert args.model in ['unet32', 'unet64', 'unet128',
                          'deeplab34', 'deeplab50', 'deeplab101',
                          'pspnet18', 'pspnet34', 'pspnet50']


def main():
    global args
    args = get_parser()
    check(args)
    os.environ["CUDA_VISIBLE_DEVICES"] = ','.join(str(x) for x in args.gpu)

    folders = [os.path.join(args.save_folder, 'prediction/gray'),
               os.path.join(args.save_folder, 'prediction/color')]
    os.makedirs(folders[0], exist_ok=True)
    os.makedirs(folders[1], exist_ok=True)

    _, transforms, _, postprocess, draw_result = dataset_utils.dataset_utils(args)
    test_loader = torch.utils.data.DataLoader(
                    dataset.Dataset(mode='test', data_root=args.test_root,
                                    data_list=args.test_list, transform=transforms[2]),
                    batch_size=args.batch_size, shuffle=False,
                    num_workers=args.workers, pin_memory=True)

    model = models.load_model(args)
    if len(args.gpu) > 1:
        model = torch.nn.DataParallel(model.cuda())
    else:
        model = model.cuda()

    if os.path.isfile(args.test_weight):
        ckpt = torch.load(args.test_weight,map_location=lambda storage,loc: storage.cuda())
        model.load_state_dict(ckpt['state_dict'], strict=True)
        print(f"=> loaded checkpoint {args.test_weight}")
    else:
        print(f"=> no weight found at {args.test_weight}")

    test(test_loader, model, postprocess, draw_result, folders)


def test(test_loader, model, postprocess, draw_result, folders):
    print(f'\nMode: Test | Model: {args.model} | Data: {args.data_name}')

    torch.set_grad_enabled(False)
    model.eval()
    for i, data in enumerate(test_loader):
        img = data[0].cuda()
        output = model(img)

        filenames = data[2]
        img = img.detach().cpu().numpy()
        pred = postprocess(output)
        draw_result(img, pred, filenames, folders[1], overlap=True)
        draw_result(img, pred, filenames, folders[0], overlap=False)
        utils.progress_bar(i, len(test_loader), '')
    torch.cuda.empty_cache()

if __name__ == '__main__':
    main()
