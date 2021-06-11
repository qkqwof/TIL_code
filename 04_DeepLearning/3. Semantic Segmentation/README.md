# PyTorch Semantic Segmentation

### Introduction

This repository is a PyTorch implementation for semantic segmentation. The code can be used for training and testing on any dataset. 
Each dataset has own config & util files for pre-processing, post-processing, loss function, hyperparameters, directories, and some utility functions. The rest of code share

### Models

* [DeepLab V3+](https://arxiv.org/pdf/1802.02611.pdf) 
* [PSPNet](https://arxiv.org/abs/1612.01105) 
* [U-Net](https://arxiv.org/abs/1505.04597) 

### Data

* [ISIC](https://isic-archive.com/)
* [Cityscapes](https://www.cityscapes-dataset.com/)
* Anything you want !

### Requirements

* python >= 3.6
* pytorch >= 1.0
* torchvision >= 0.3
* opencv-python
* numpy
* pillow


### Usage

1. Requirement:
    - Hardware: GPU(s)
    - Software: see *Requirements*
    
2. Data download:
    - Prepare desired dataset ([ISIC](https://isic-archive.com/), [Cityscapes](https://www.cityscapes-dataset.com/), ...)

3. Clone the repository:
    ```
    git clone https://github.com/JooHyun-Lee/semantic-segmentation-pytorch.git
    ```

4. Train:
    ```
    python train.py
    ```
  
5. Test:
    ```
    python test.py
    ```




### Citation

```
@misc{semseg2019,
  author={Zhao, Hengshuang},
  title={semseg},
  howpublished={\url{https://github.com/hszhao/semseg}},
  year={2019}
}

@misc{classification2019,
  author={kuangliu},
  title={pytorch-cifar},
  howpublished={\url{https://github.com/kuangliu/pytorch-cifar}},
  year={2019}
}

@inproceedings{ronneberger2015u,
  title={U-net: Convolutional networks for biomedical image segmentation},
  author={Ronneberger, Olaf and Fischer, Philipp and Brox, Thomas},
  booktitle={International Conference on Medical image computing and computer-assisted intervention},
  pages={234--241},
  year={2015},
  organization={Springer}
}

@inproceedings{zhao2017pyramid,
  title={Pyramid scene parsing network},
  author={Zhao, Hengshuang and Shi, Jianping and Qi, Xiaojuan and Wang, Xiaogang and Jia, Jiaya},
  booktitle={Proceedings of the IEEE conference on computer vision and pattern recognition},
  pages={2881--2890},
  year={2017}
}

@inproceedings{chen2018encoder,
  title={Encoder-decoder with atrous separable convolution for semantic image segmentation},
  author={Chen, Liang-Chieh and Zhu, Yukun and Papandreou, George and Schroff, Florian and Adam, Hartwig},
  booktitle={Proceedings of the European conference on computer vision (ECCV)},
  pages={801--818},
  year={2018}
}
```
