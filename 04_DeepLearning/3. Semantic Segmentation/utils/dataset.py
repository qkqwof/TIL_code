import os
import cv2
import numpy as np

from torch.utils.data import Dataset as Dataset_


IMG_EXTENSIONS = ['.jpg', '.jpeg', '.png', '.ppm', '.bmp', '.pgm']


def is_img_file(filename):
    filename_lower = filename.lower()
    return any(filename_lower.endswith(extension) for extension in IMG_EXTENSIONS)


def make_dataset(mode='train', data_root=None, data_list=None):
    assert mode in ['train', 'val', 'test']
    if not os.path.isfile(data_list):
        raise (RuntimeError(f"Image&Label list file do not exist: {data_list}\n"))
    img_label_list = []
    list_read = open(data_list).readlines()
    print(f"Totally {len(list_read)} samples in {mode} set.")
    print(f"Starting Checking image&label pair {mode} list...")
    for line in list_read:
        line = line.strip()
        line_split = line.split(' ')
        if mode == 'test':
            img_name = os.path.join(data_root, line_split[0])
            label_name = img_name  # just set place holder for label_name, not for use
        else:
            if len(line_split) != 2:
                raise (RuntimeError(f"Image list file read line error: {line}\n"))
            img_name = os.path.join(data_root, line_split[0])
            label_name = os.path.join(data_root, line_split[1])
        if is_img_file(img_name) and is_img_file(label_name) and \
           os.path.isfile(img_name) and os.path.isfile(label_name):
            item = (img_name, label_name)
            img_label_list.append(item)
        else:
            raise (RuntimeError(f"Image list file line error: {line}\n"))
    print("Checking image&label pair {} list done!".format(mode))
    return img_label_list


class Dataset(Dataset_):
    def __init__(self, mode='train', data_root=None, data_list=None, transform=None, label_transform=None):
        self.mode = mode
        self.data_list = make_dataset(mode, data_root, data_list)
        self.transform = transform
        self.label_transform = label_transform

    def __len__(self):
        return len(self.data_list)

    def __getitem__(self, idx):
        img_path, label_path = self.data_list[idx]
        img = cv2.imread(img_path, cv2.IMREAD_COLOR)
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        img = np.float32(img)
        if self.mode == 'test':
            label = img[:,:,0] # just set place holder, not for use
        else:
            label = cv2.imread(label_path, cv2.IMREAD_GRAYSCALE)
        if img.shape[0] != label.shape[0] or img.shape[1] != label.shape[1]:
            raise (RuntimeError(f"Image&label shape mismatch: {img_path} {label_path}\n"))
        if self.transform is not None:
            img, label = self.transform(img, label)
        if self.label_transform is not None:
            label = self.label_transform(label)
        return img, label, img_path


if __name__ == "__main__":
    import pdb
    from transform import transform

    data_root = '../../data/cityscapes/train'
    data_list = '../../data/cityscapes/train/train.txt'
    mode = 'train'

    transform = transform.Compose([transform.ToTensor()])
    dataset = Dataset(mode=mode, data_root=data_root, data_list=data_list, transform=transform)

    for i in range(len(dataset)):
        data = dataset[i]
        pdb.set_trace()
