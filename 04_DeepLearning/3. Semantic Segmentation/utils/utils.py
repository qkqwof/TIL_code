import pdb
import os
import sys
import time
import math
import cv2
import numpy as np
import shutil

import torch


class AverageMeter(object):
    """Computes and stores the average and current value"""
    def __init__(self):
        self.reset()

    def reset(self):
        self.val = 0
        self.avg = 0
        self.sum = 0
        self.count = 0

    def update(self, val, n=1):
        self.val = val
        self.sum += val * n
        self.count += n
        self.avg = self.sum / self.count


def onehot_encoding(label, C=2):
    label = label.unsqueeze(1).type(torch.cuda.LongTensor)
    one_hot = torch.cuda.FloatTensor(
                label.size(0), C, label.size(2), label.size(3)).zero_()
    label = one_hot.scatter_(1, label.data, 1)
    return label


def intersection_of_union(output, label, classes, ignore_index=255):
    assert (output.dim() in [1, 2, 3])
    assert output.shape == label.shape
    C = classes
    output = output.view(-1)
    label = label.view(-1)
    output[label == ignore_index] = ignore_index
    intersection = output[output == label]
    # https://github.com/pytorch/pytorch/issues/1382
    area_inter = torch.histc(intersection.float().cpu(), bins=C, min=0, max=C-1)
    area_output = torch.histc(output.float().cpu(), bins=C, min=0, max=C-1)
    area_label = torch.histc(label.float().cpu(), bins=C, min=0, max=C-1)
    area_union = area_output + area_label - area_inter
    #return area_inter.cuda(), area_union.cuda(), area_label.cuda()
    mIoU = (area_inter/(area_union+1e-10)).mean().item()
    return mIoU


def intersection_of_union_gpu(output, label, classes, ignore_index=255):
    # 'K' classes, output and target sizes are N or N * L or N * H * W, each value in range 0 to K - 1.
    assert (output.dim() in [1, 2, 3])
    assert output.shape == label.shape
    C = classes
    output = output.view(-1)
    label = label.view(-1)
    output[label == ignore_index] = ignore_index
    intersection = output[output == label]
    # https://github.com/pytorch/pytorch/issues/1382
    area_intersection = torch.histc(intersection.float().cpu(), bins=C, min=0, max=C-1)
    area_output = torch.histc(output.float().cpu(), bins=C, min=0, max=C-1)
    area_label = torch.histc(label.float().cpu(), bins=C, min=0, max=C-1)
    area_union = area_output + area_label - area_intersection + 1e-3
    return (area_intersection/area_union).mean().item()



'''
    MIT License
    Copyright (c) 2017 liukuang

    https://github.com/kuangliu/pytorch-cifar
'''

#_, term_width = os.popen('stty size', 'r').read().split()
_, term_width = shutil.get_terminal_size()
term_width = int(term_width)

TOTAL_BAR_LENGTH = 15.
last_time = time.time()
begin_time = last_time

def progress_bar(current, total, msg=None):
    global last_time, begin_time
    if current == 0:
        begin_time = time.time()  # Reset for new bar.

    cur_len = int(TOTAL_BAR_LENGTH*current/total)
    rest_len = int(TOTAL_BAR_LENGTH - cur_len) - 1

    sys.stdout.write(' [')
    for i in range(cur_len):
        sys.stdout.write('=')
    sys.stdout.write('>')
    for i in range(rest_len):
        sys.stdout.write('.')
    sys.stdout.write(']')

    cur_time = time.time()
    step_time = cur_time - last_time
    last_time = cur_time
    tot_time = cur_time - begin_time

    L = []
    L.append('  Step: %s' % format_time(step_time))
    L.append(' | Tot: %s' % format_time(tot_time))
    if msg:
        L.append(' | ' + msg)

    msg = ''.join(L)
    sys.stdout.write(msg)
    for i in range(term_width-int(TOTAL_BAR_LENGTH)-len(msg)-3):
        sys.stdout.write(' ')

    # Go back to the center of the bar.
    for i in range(term_width-int(TOTAL_BAR_LENGTH/2)+2):
        sys.stdout.write('\b')
    sys.stdout.write(' %d/%d ' % (current+1, total))

    if current < total-1:
        sys.stdout.write('\r')
    else:
        sys.stdout.write('\n')
    sys.stdout.flush()

def format_time(seconds):
    days = int(seconds / 3600/24)
    seconds = seconds - days*3600*24
    hours = int(seconds / 3600)
    seconds = seconds - hours*3600
    minutes = int(seconds / 60)
    seconds = seconds - minutes*60
    secondsf = int(seconds)
    seconds = seconds - secondsf
    millis = int(seconds*1000)

    f = ''
    i = 1
    if days > 0:
        f += str(days) + 'D'
        i += 1
    if hours > 0 and i <= 2:
        f += str(hours) + 'h'
        i += 1
    if minutes > 0 and i <= 2:
        f += str(minutes) + 'm'
        i += 1
    if secondsf > 0 and i <= 2:
        f += str(secondsf) + 's'
        i += 1
    if millis > 0 and i <= 2:
        f += str(millis) + 'ms'
        i += 1
    if f == '':
        f = '0ms'
    return f
