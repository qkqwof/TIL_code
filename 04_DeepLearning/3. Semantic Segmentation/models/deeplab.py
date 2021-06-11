import math
import torch
import torch.nn as nn
import torch.nn.functional as F

from models.resnet import *


def conv1x1(in_channels, out_channels, dropout=0):
    return nn.Sequential(nn.Conv2d(in_channels, out_channels, 1, 1, 0),
                         nn.BatchNorm2d(out_channels),
                         nn.ReLU(inplace=True),
                         nn.Dropout(dropout))


def conv3x3(in_channels, out_channels, stride=1, dropout=0):
    return nn.Sequential(nn.Conv2d(in_channels, out_channels, 3, stride, 1),
                         nn.BatchNorm2d(out_channels),
                         nn.ReLU(inplace=True),
                         nn.Dropout(dropout))


class ASPP(nn.Module):
    def __init__(self, in_channels, out_channels, dilation):
        super(ASPP, self).__init__()
        if dilation == 1:
            kernel_size = 1
            padding = 0
        else:
            kernel_size= 3
            padding = dilation

        self.atrous_conv = nn.Conv2d(in_channels, out_channels, kernel_size=kernel_size,
                                     stride=1, padding=padding, dilation=dilation, bias=False)
        self.bn = nn.BatchNorm2d(out_channels)
        self.relu = nn.ReLU()
        self.dropout = nn.Dropout(0.1)

    def forward(self, x):
        x = self.atrous_conv(x)
        x = self.bn(x)
        x = self.relu(x)
        return self.dropout(x)


class DeeplabV3(nn.Module):
    def __init__(self, classes, model='deeplab101'):
        super(DeeplabV3, self).__init__()
        resnet_dilations=[1,1,2,4]
        resnet_strides=[1,2,2,1]
        if model =='deeplab34':
            resnet = resnet34(strides=resnet_strides, dilations=resnet_dilations)
            low_channels = 64
            high_channels = 512
        elif model =='deeplab50':
            resnet = resnet50(strides=resnet_strides, dilations=resnet_dilations)
            low_channels = 256
            high_channels = 2048
        elif model =='deeplab101':
            resnet = resnet101(strides=resnet_strides, dilations=resnet_dilations)
            low_channels = 256
            high_channels = 2048
        else:
            raise ValueError(f'{model} is not supported model')

        self.layer0 = nn.Sequential(resnet.conv1, resnet.bn1, resnet.relu)#, resnet.maxpool)
        self.layer1 = resnet.layer1
        self.layer2 = resnet.layer2
        self.layer3 = resnet.layer3
        self.layer4 = resnet.layer4

        dilations = [1,12,24,36]
        self.aspp1 = ASPP(high_channels, 192, dilation=dilations[0])
        self.aspp2 = ASPP(high_channels, 192, dilation=dilations[1])
        self.aspp3 = ASPP(high_channels, 192, dilation=dilations[2])
        self.aspp4 = ASPP(high_channels, 192, dilation=dilations[3])
        self.global_avg_pool = nn.Sequential(nn.AdaptiveAvgPool2d((1,1)),
                                             conv1x1(high_channels, 192))
        self.layer5 = conv1x1(960, 256)
        self.layer6 = conv1x1(low_channels, 48)
        self.layer7 = nn.Sequential(conv3x3(304, 256, dropout=0.5),
                                    conv3x3(256, 256, dropout=0.1),
                                    nn.Conv2d(256, classes, kernel_size=1, stride=1))

    def forward(self, x):
        size = x.size()

        x = self.layer0(x)
        x_low = self.layer1(x)
        x = self.layer2(x_low)
        x = self.layer3(x)
        x = self.layer4(x)

        x1 = self.aspp1(x)
        x2 = self.aspp2(x)
        x3 = self.aspp3(x)
        x4 = self.aspp4(x)
        x5 = self.global_avg_pool(x)
        x5 = F.interpolate(x5, size=x4.size()[2:], mode='bilinear', align_corners=True)
        x = torch.cat((x1,x2,x3,x4,x5), dim=1)
        x = self.layer5(x)

        x_low = self.layer6(x_low)
        x = F.interpolate(x, size=x_low.size()[2:], mode='bilinear', align_corners=True)
        x = torch.cat((x, x_low), dim=1)
        x = self.layer7(x)
        x = F.interpolate(x, size=size[2:], mode='bilinear', align_corners=True)

        return x


if __name__ == "__main__":
    import pdb
    from resnet import *

    model = DeeplabV3(2, 'deeplab101').cuda()
    x = torch.rand(2,3,256,256).cuda()
    y = model(x)
    print(y.size())
