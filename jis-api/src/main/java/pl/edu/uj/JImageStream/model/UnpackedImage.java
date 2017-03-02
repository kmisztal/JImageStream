package pl.edu.uj.JImageStream.model;

import pl.edu.uj.JImageStream.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class UnpackedImage {

    protected int height;
    protected int width;

    protected int[] bufferedImageRGBA;
    protected int[] bufferedImageRGBAWorking;

    public UnpackedImage(BufferedImage bufferedImage) {
        height = bufferedImage.getHeight();
        width = bufferedImage.getWidth();
        bufferedImageRGBAWorking = ImageUtils.convert(bufferedImage, BufferedImage.TYPE_INT_ARGB).getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null, 0, bufferedImage.getWidth());
        update();
    }

    public void update() {
        bufferedImageRGBA = Arrays.copyOf(bufferedImageRGBAWorking, bufferedImageRGBAWorking.length);
    }

    private int getAlpha(int x, int y) {
        return (bufferedImageRGBA[y * width + x] & -16777216) >>> 24;
    }

    private int getRed(int x, int y) {
        return (bufferedImageRGBA[y * width + x] & 16711680) >>> 16;
    }

    private int getGreen(int x, int y) {
        return (bufferedImageRGBA[y * width + x] & '\uff00') >>> 8;
    }

    private int getBlue(int x, int y) {
        return bufferedImageRGBA[y * width + x] & 255;
    }

    public void setPixel(int x, int y, int red, int green, int blue, int alpha) {
        bufferedImageRGBAWorking[y * width + x] = (alpha << 24) + (red << 16) + (green << 8) + blue;
    }

    public int[] getPixel(int x, int y) {
        return new int[]{getRed(x, y), getGreen(x, y), getBlue(x, y), getAlpha(x, y)};
    }

    public BufferedImage getBufferedImage() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferedImage.setRGB(0, 0, width, height, bufferedImageRGBA, 0, width);
        return bufferedImage;
    }

    public void resize(int x, int y) {
        int[] copy = new int[x * y];

        for (int i = 0; i < Math.min(width, x); ++i) {
            for (int j = 0; j < Math.min(height, y); ++j) {
                copy[j * (x - 1) + i] = bufferedImageRGBA[j * width + i];
            }
        }

        height = y - 1;
        width = x - 1;

        bufferedImageRGBAWorking = copy;
        bufferedImageRGBA = Arrays.copyOf(bufferedImageRGBAWorking, bufferedImageRGBAWorking.length);


    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
