import torch
import torch.nn as nn
import numpy as np
import os
import cv2
import pdb

from utils import transform
from utils import dice_loss


def data_transform(args):
    assert(args.data_name == 'isic')
    label_transform = lambda x: (x>125).type(torch.float32)
    train_transform = transform.Compose([
        transform.Resize((args.input_size[0], args.input_size[1])),
        transform.ColorJitter(brightness=args.brightness,
                              contrast=args.contrast,
                              saturation=args.saturation),
        transform.RandRotate([args.rotate_min, args.rotate_max], padding=[0,0,0]),
        transform.RandomGaussianBlur(),
        transform.RandomHorizontalFlip(),
        transform.ToTensor()])
    val_transform = transform.Compose([
        transform.Resize((args.input_size[0], args.input_size[1])),
        transform.ToTensor()])
    test_transform = transform.Compose([
        transform.Resize((args.input_size[0], args.input_size[1])),
        transform.ToTensor()])
    return train_transform, val_transform, test_transform, label_transform


def loss_func(args):
    assert args.loss in ['bce', 'ce', 'dice']
    if args.loss == 'bce':
        loss_function = nn.BCELoss()
    elif args.loss == 'ce':
        loss_function = nn.CrossEntropyLoss()
    else:
        loss_function = dice_loss.DiceCoef(args.classes)

    def calc(output, label):
        return loss_function(output, label)
    return calc


def calc_dice(args):
    dice_func = dice_loss.DiceCoef(args.classes)
    def calc(output, label):
        return 1-dice_func(torch.sigmoid(output), label)
    return calc


def postprocess(args):
    def worker(output):
        return torch.sigmoid(output).max(1)[1].detach().cpu().numpy()
    return worker


def draw_pred(args):
    def worker(imgs, preds, filenames, folder, overlap=True):
        for idx in range(imgs.shape[0]):
            if overlap:
                img = np.moveaxis(imgs[idx], 0, -1)
                img = cv2.cvtColor(img, cv2.COLOR_RGB2BGR)
                pred = np.expand_dims(preds[idx], -1)
                zero = np.zeros(pred.shape)
                color = np.concatenate((zero,zero,pred), -1)

                mask = (pred>0).astype(int)
                mask = np.concatenate((mask,mask,mask), -1)
                rev_mask = 1 - mask

                color = img*mask + color
                color = color/(color.max()+1e-3)*255
                final = img * rev_mask * 255 + color
                cv2.imwrite(os.path.join(folder, filenames[idx].split('/')[-1]), final)
            else:
                cv2.imwrite(os.path.join(folder, filenames[idx].split('/')[-1]), preds[idx]*255)
    return worker


def draw_label():
    def worker(img_path, label_path, folder, resize_shape=None):
        img = cv2.imread(img_path)#.astype(float)
        label = cv2.imread(label_path)#.astype(float)
        if resize_shape is not None:
            img = cv2.resize(img, resize_shape)
            label = cv2.resize(label, resize_shape)
        label = np.expand_dims(label[:,:,0], -1)
        zero = np.zeros(label.shape)
        color = np.concatenate((label,zero,zero), -1)

        mask = (label>122).astype(float)
        mask = np.concatenate((mask,mask,mask), -1)
        rev_mask = 1 - mask

        color = img*mask + color
        color = color/(color.max()+1e-3)*255
        final = img * rev_mask + color
        cv2.imwrite(os.path.join(folder, img_path.split('/')[-1]), final)
    return worker


