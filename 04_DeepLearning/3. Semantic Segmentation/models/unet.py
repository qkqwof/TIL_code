import torch
import torch.nn as nn
import torch.nn.functional as F


class DoubleConv(nn.Module):
    def __init__(self, in_channels, out_channels, stride=1):
        super().__init__()
        assert stride in [1, 2]
        self.double_conv = nn.Sequential(
            nn.Conv2d(in_channels, out_channels, kernel_size=3, padding=1, stride=stride),
            nn.BatchNorm2d(out_channels),
            nn.ReLU(inplace=True),
            nn.Conv2d(out_channels, out_channels, kernel_size=3, padding=1),
            nn.BatchNorm2d(out_channels),
            nn.ReLU(inplace=True))

    def forward(self, x):
        return self.double_conv(x)


class Down(nn.Module):
    def __init__(self, in_channels, out_channels):
        super().__init__()
        self.conv = nn.Sequential(
            DoubleConv(in_channels, out_channels, stride=2))

    def forward(self, x):
        x = self.conv(x)
        return x


class Up(nn.Module):
    def __init__(self, in_channels, out_channels):
        super().__init__()
        self.conv = DoubleConv(in_channels, out_channels)

    def forward(self, x1, x2):
        h, w = x1.size()[2:]
        x1 = F.interpolate(x1, (h*2,w*2))
        x = torch.cat([x2, x1], dim=1)
        return self.conv(x)


class UNet(nn.Module):
    def __init__(self, classes, model):
        super(UNet, self).__init__()
        if model == 'unet32':
            base_channels=32
        elif model == 'unet64':
            base_channels=64
        elif model == 'unet128':
            base_channels=128
        else:
            raise ValueError(f'{model} is not supported model')

        self.inc   = DoubleConv(3, base_channels) # Assume input has 3 channels
        self.down1 = Down(base_channels, base_channels*2)
        self.down2 = Down(base_channels*2, base_channels*4)
        self.down3 = Down(base_channels*4, base_channels*8)
        self.down4 = Down(base_channels*8, base_channels*8)
        self.up1   = Up(base_channels*16, base_channels*4)
        self.up2   = Up(base_channels*8, base_channels*2)
        self.up3   = Up(base_channels*4, base_channels)
        self.up4   = Up(base_channels*2, base_channels)
        self.outc  = nn.Conv2d(base_channels, classes, kernel_size=1)

    def forward(self, x):
        x1 = self.inc(x)
        x2 = self.down1(x1)
        x3 = self.down2(x2)
        x4 = self.down3(x3)
        x5 = self.down4(x4)
        x = self.up1(x5, x4)
        x = self.up2(x, x3)
        x = self.up3(x, x2)
        x = self.up4(x, x1)
        x = self.outc(x)
        return x


if __name__ == "__main__":
    import pdb

    model = UNet(2, 'unet128').cuda()
    x = torch.rand(4,3,256,256).cuda()
    y = model(x)
    print(y.size())
