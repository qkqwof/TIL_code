import pdb
import numpy as np
import torch.nn as nn

from utils import utils

class DiceCoef(nn.Module):
    def __init__(self, classes=2):
        super(DiceCoef, self).__init__()
        self.classes = classes

    def forward(self, preds, labels, backprop=True):
        smooth = 1.0
        loss = 0

        labels = utils.onehot_encoding(labels, self.classes)
        if backprop:
            # maybe memory leak
            for i in range(self.classes):
                pred = preds[:,i,:,:]
                label = labels[:,i,:,:]
                intersection = (pred * label).sum()
                loss += (1 - ((2.0 * intersection + smooth) / (pred.sum() + label.sum() + smooth)))
        else:
            labels = np.array(labels.cpu().argmax(1))
            preds = np.array(preds.cpu().argmax(1))
            for i in range(self.classes):
                pred = (preds==i).astype(np.uint8)
                label= (labels==i).astype(np.uint8)
                intersection = (pred * label).sum()
                loss += (1 - ((2.0 * intersection + smooth) / (pred.sum() + label.sum() + smooth)))
        return (loss/self.classes).item()
