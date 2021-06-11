from models.unet import UNet
from models.deeplab import DeeplabV3
from models.pspnet import PSPNet


def load_model(args):
    if args.model in ['unet32', 'unet64', 'unet128']:
        model = UNet(args.classes, args.model)
    elif args.model in ['deeplab34', 'deeplab50', 'deeplab101']:
        model = DeeplabV3(args.classes, args.model)
    elif args.model in ['pspnet18', 'pspnet34', 'pspnet50']:
        model = PSPNet(args.classes, args.model)
    else:
        raise (RuntimeError(f"{args.model} is not supported model"))
    return model
