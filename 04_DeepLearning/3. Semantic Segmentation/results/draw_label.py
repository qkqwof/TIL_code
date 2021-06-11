import os
import pdb
import sys
sys.path.insert(0, '..')
from utils import dataset_utils

def draw_labels(data_name, data_root, data_list, resize_shape=None):
    draw_label = dataset_utils.draw_label(data_name)
    if not os.path.isfile(data_list):
        raise (RuntimeError(f"Image&Label list file do not exist: {data_list}\n"))
    list_read = open(data_list).readlines()

    folder = os.path.join(data_name, 'label')
    os.makedirs(folder, exist_ok=True)
    for idx, line in enumerate(list_read):
        line = line.strip()
        line_split = line.split(' ')
        if len(line_split) != 2:
            raise (RuntimeError(f"Image list file read line error: {line}\n"))
        img_path = os.path.join(data_root, line_split[0])
        label_path = os.path.join(data_root, line_split[1])
        draw_label(img_path, label_path, folder, resize_shape)
        print(f'{idx+1} / {len(list_read)}')

if __name__ == "__main__":
    data_name = 'crack'
    data_root = f'../../data/{data_name}/train'
    data_list = f'../../data/{data_name}/train/train.txt'
    resize_shape = (320,320)
    draw_labels(data_name, data_root, data_list, resize_shape)

