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


class PPM(nn.Module):
    def __init__(self, in_dim, reduction_dim, bins):
        super(PPM, self).__init__()
        self.features = []
        for bin in bins:
            self.features.append(nn.Sequential(
                nn.AdaptiveAvgPool2d(bin),
                nn.Conv2d(in_dim, reduction_dim, kernel_size=1, bias=False),
                nn.BatchNorm2d(reduction_dim),
                nn.ReLU(inplace=True)
            ))
        self.features = nn.ModuleList(self.features)

    def forward(self, x):
        x_size = x.size()
        out = [x]
        for f in self.features:
            out.append(F.interpolate(f(x), x_size[2:], mode='bilinear', align_corners=True))
        return torch.cat(out, 1)


class PSPNet(nn.Module):
    def __init__(self, classes, model='pspnet50'):
        super(PSPNet, self).__init__()
        resnet_dilations=[1,1,2,4]
        resnet_strides=[1,2,1,1]
        self.dropout=0.1

        if model == 'pspnet18':
            resnet = resnet18(strides=resnet_strides, dilations=resnet_dilations)
            fea_dim = 512
        elif model == 'pspnet34':
            resnet = resnet34(strides=resnet_strides, dilations=resnet_dilations)
            fea_dim = 512
        elif model == 'pspnet50':
            resnet = resnet50(strides=resnet_strides, dilations=resnet_dilations)
            fea_dim = 2048
        else:
            raise ValueError(f'{model} is not supported model')

        self.layer0 = nn.Sequential(conv3x3(3, 64, stride=2),
                                    conv3x3(64,64),
                                    conv3x3(64,64),
                                    nn.MaxPool2d(3,2,1))
        self.layer1 = resnet.layer1
        self.layer2 = resnet.layer2
        self.layer3 = resnet.layer3
        self.layer4 = resnet.layer4

        bins = (1,2,3,6)
        self.ppm = PPM(fea_dim, int(fea_dim/len(bins)), bins)

        self.cls = nn.Sequential(
            nn.Conv2d(fea_dim*2, 512, kernel_size=3, padding=1, bias=False),
            nn.BatchNorm2d(512),
            nn.ReLU(inplace=True),
            nn.Dropout2d(p=self.dropout),
            nn.Conv2d(512, classes, kernel_size=1))

        if self.training:
            aux_ch = fea_dim // 2
            self.aux = nn.Sequential(
                nn.Conv2d(aux_ch, aux_ch//4, kernel_size=3, padding=1, bias=False),
                nn.BatchNorm2d(aux_ch//4),
                nn.ReLU(inplace=True),
                nn.Dropout2d(p=self.dropout),
                nn.Conv2d(aux_ch//4, classes, kernel_size=1)
            )

    def forward(self, x):
        size = x.size()

        x = self.layer0(x)
        x = self.layer1(x)
        x = self.layer2(x)
        x = self.layer3(x)
        x_low = x
        x = self.layer4(x)
        x = self.ppm(x)
        x = self.cls(x)
        x = F.interpolate(x, size=size[2:], mode='bilinear', align_corners=True)
        if self.training:
            aux = self.aux(x_low)
            aux = F.interpolate(aux, size=size[2:], mode='bilinear', align_corners=True)
            return x, aux
        else:
            return x


if __name__ == '__main__':
    import pdb
    from resnet import *

    model = PSPNet(3, 'pspnet18').cuda()
    x = torch.rand(2, 3, 448, 448).cuda()
    y = model(x)
    print(y[0].size(), y[1].size())

