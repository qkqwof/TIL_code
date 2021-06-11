import pdb
import torch.nn as nn


############# Convolution Modules #############

def conv_2d(in_channels, out_channels, kernel_size, stride, padding, bias=False,
            dilation=1, act_fn=nn.ReLU(inplace=True), norm=nn.BatchNorm2d, drop_rate=0):
    drop_layer = nn.Dropout2d(drop_rate)
    norm_layer = norm(out_channels)

    return nn.Sequential(
        nn.Conv2d(in_channels, out_channels, kernel_size, stride, padding, dilation, bias=bias),
        norm_layer,
        drop_layer,
        act_fn)


def conv1x1_2d(in_channels, out_channels, bias=False,
               act_fn=nn.ReLU(inplace=True), norm=nn.BatchNorm2d, drop_rate=0):
    return conv_2d(in_channels, out_channels, 1, 1, 0, bias, 1, act_fn, norm, drop_rate)


def conv3x3_2d(in_channels, out_channels, bias=False,
               act_fn=nn.ReLU(inplace=True), norm=nn.BatchNorm2d, drop_rate=0):
    return conv_2d(in_channels, out_channels, 3, 1, 1, bias, 1, act_fn, norm, drop_rate)


def conv5x5_2d(in_channels, out_channels, bias=False,
               act_fn=nn.ReLU(inplace=True), norm=nn.BatchNorm2d, drop_rate=0):
    return conv_2d(in_channels, out_channels, 5, 1, 2, bias, 1, act_fn, norm, drop_rate)


def conv3x3_2d_2(in_channels, out_channels, bias=False,
                 act_fn=nn.ReLU(inplace=True), norm=nn.BatchNorm2d, drop_rate=0):
    return nn.Sequential(
        conv3x3_2d(in_channels, out_channels, bias, act_fn, norm, drop_rate),
        conv3x3_2d(out_channels, out_channels, bias, act_fn, norm, drop_rate))


def conv3x3_2d_3(in_channels, out_channels, bias=False,
                 act_fn=nn.ReLU(inplace=True), norm=nn.BatchNorm2d, drop_rate=0):
    return nn.Sequential(
        conv3x3_2d(in_channels, out_channels, bias, act_fn, norm, drop_rate),
        conv3x3_2d(out_channels, out_channels, bias, act_fn, norm, drop_rate),
        conv3x3_2d(out_channels, out_channels, bias, act_fn, norm, drop_rate))




############# Deconvolution Modules #############

def conv_trans_2d(in_channels, out_channels, kernel_size, stride, padding, output_padding=0,
                  bias=True, act_fn=nn.ReLU(inplace=True), norm=nn.BatchNorm2d, drop_rate=0):

    drop_layer = nn.Dropout2d(drop_rate)
    norm_layer = norm
    return nn.Sequential(
        nn.ConvTranspose2d(in_channels, out_channels, kernel_size, stride, padding,
                           output_padding=output_padding, bias=bias),
        norm_layer,
        drop_layer,
        act_fn)


def conv3x3_trans_2d(in_channels, out_channels, bias=True, act_fn=nn.ReLU(inplace=True),
                     norm=nn.BatchNorm2d, drop_rate=0):
    return conv_trans_2d(in_channels, out_channels, 3, 2, 1, 1, bias, act_fn, norm, drop_rate)



############# Pooling Modules #############

def maxpool_2d(kernel_size=2):
    return nn.MaxPool2d(kernel_size=kernel_size, stride=kernel_size, padding=0)


def avgpool_2d(kernel_size=2):
    return nn.AvgPool2d(kernel_size=kernel_size, stride=kernel_size, padding=0)


