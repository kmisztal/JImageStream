package pl.edu.uj.JImageStream.utils;

import java.awt.image.BufferedImage;

public class ImageUtils {

    public static BufferedImage convert(BufferedImage src, int bufImgType) {
        BufferedImage img = new BufferedImage(src.getWidth(), src.getHeight(), bufImgType);
        img.getGraphics().drawImage(src, 0, 0, null);
        return img;
    }

}
