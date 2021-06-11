from utils.dataset_utils import crack
from utils.dataset_utils import cityscapes
from utils.dataset_utils import isic


def dataset_utils(args):
    if args.data_name == 'crack':
        criterion = crack.loss_func(args)
        transforms = crack.data_transform(args)
        calc_score = crack.calc_dice(args)
        postprocess = crack.postprocess(args)
        draw_pred = crack.draw_pred(args)
    elif args.data_name == 'cityscapes':
        criterion = cityscapes.loss_func(args)
        transforms = cityscapes.data_transform(args)
        calc_score = cityscapes.calc_mIoU(args)
        postprocess = cityscapes.postprocess(args)
        draw_pred = cityscapes.draw_pred(args)
    elif args.data_name == 'isic':
        criterion = isic.loss_func(args)
        transforms = isic.data_transform(args)
        calc_score = isic.calc_dice(args)
        postprocess = isic.postprocess(args)
        draw_pred = isic.draw_pred(args)
    else:
        raise ValueError(f'"{args.data_name}" is not supported dataset')
    return criterion, transforms, calc_score, postprocess, draw_pred


def draw_label(data_name):
    if data_name == 'crack':
        draw_label = crack.draw_label()
    elif data_name == 'cityscapes':
        draw_label = cityscapes.draw_label()
    elif data_name == 'isic':
        draw_label = isic.draw_label()
    else:
        raise ValueError(f'"{data_name}" is not supported dataset')
    return draw_label

