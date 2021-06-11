import torch
import torch.nn as nn
import numpy as np
import os
import cv2

from utils import transform
from utils import utils

def data_transform(args):
    assert (args.data_name == 'cityscapes')
    value_scale = 255
    mean = [0.485, 0.456, 0.406]
    mean = [item * value_scale for item in mean]
    std = [0.229, 0.224, 0.225]
    std = [item * value_scale for item in std]

    train_transform = transform.Compose([
        transform.RandScale([args.scale_min, args.scale_max]),
        transform.RandRotate([args.rotate_min, args.rotate_max],
                             padding=mean,
                             ignore_label=args.ignore_label),
        transform.RandomGaussianBlur(),
        transform.RandomHorizontalFlip(),
        transform.Crop(args.train_input_size,
                       crop_type='rand', padding=mean,
                       ignore_label=args.ignore_label),
        transform.ToTensor(),
        transform.Normalize(mean=mean, std=std)])

    val_transform = transform.Compose([
            transform.Crop(args.train_input_size, crop_type='center',
                           padding=mean, ignore_label=args.ignore_label),
            transform.ToTensor(),
            transform.Normalize(mean=mean, std=std)])

    test_transform = transform.Compose([
            transform.Crop(args.train_input_size, crop_type='center',
                           padding=mean, ignore_label=args.ignore_label),
            transform.ToTensor(),
            transform.Normalize(mean=mean, std=std)])

    label_transform = None

    return train_transform, val_transform, test_transform, label_transform


def loss_func(args):
    assert args.loss in ['ce', 'dice']
    if args.loss == 'ce':
        loss_function = nn.CrossEntropyLoss(ignore_index=args.ignore_label)
    else:
        raise NotImplementedError('Dice Coefficient loss is not implemented')

    def calc(output, label):
        return loss_function(output, label)
    return calc


def calc_mIoU(args):
    classes = args.classes
    ignore_index = args.ignore_label

    def calc(output, label):
        return utils.intersection_of_union_gpu(output.max(1)[1], label, classes, ignore_index)
    return calc


def postprocess(args):
    def worker(output):
        return 1

def draw_pred(args):
    def worker(imgs, preds, filenames, folder, overlap=True):
        return 1
    return worker

def draw_label():
    def worker(img_path, label_path, folder, resize_shape=None):
        return 1
    return worker
