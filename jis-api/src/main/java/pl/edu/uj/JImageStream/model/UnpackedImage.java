package pl.edu.uj.JImageStream.model;

import pl.edu.uj.JImageStream.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.stream.IntStream;

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

    private int getAlpha(int pixel) {
        return (bufferedImageRGBA[pixel] & -16777216) >>> 24;
    }

    private int getRed(int pixel) {
        return (bufferedImageRGBA[pixel] & 16711680) >>> 16;
    }

    private int getGreen(int pixel) {
        return (bufferedImageRGBA[pixel] & '\uff00') >>> 8;
    }

    private int getBlue(int pixel) {
        return bufferedImageRGBA[pixel] & 255;
    }

    public void setPixel(int x, int y, int red, int green, int blue, int alpha) {
        bufferedImageRGBAWorking[y * width + x] = (alpha << 24) + (red << 16) + (green << 8) + blue;
    }

    public int[] getPixel(int x, int y) {
        int pixel = y * width + x;
        return new int[]{getRed(pixel), getGreen(pixel), getBlue(pixel), getAlpha(pixel)};
    }

    private int getRawPixel(int x, int y) {
        return bufferedImageRGBA[y * width + x];
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
                copy[j * (x - 1) + i] = getRawPixel(i, j);
            }
        }

        height = y - 1;
        width = x - 1;

        bufferedImageRGBAWorking = copy;
        bufferedImageRGBA = Arrays.copyOf(bufferedImageRGBAWorking, bufferedImageRGBAWorking.length);


    }

    public void resize(int x, int y, boolean scale) {

        if (scale) {

            double xScale = (1. * width) / (x);
            double yScale = (1. * height) / (y);

            int[] copy = new int[x * y];

            for (int i = 0; i < x; ++i) {
                for (int j = 0; j < y; ++j) {
                    copy[j * (x - 1) + i] = getRawPixel((int) (i * xScale), (int) (j * yScale));
                }
            }

            height = y - 1;
            width = x - 1;

            bufferedImageRGBAWorking = copy;
            bufferedImageRGBA = Arrays.copyOf(bufferedImageRGBAWorking, bufferedImageRGBAWorking.length);

        } else {
            resize(x, y);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int[] getGrayScaleHistogram() {
        int[] histogram = new int[256];

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                ++histogram[(int) IntStream.of(getPixel(i, j)).limit(3).average().getAsDouble()];
            }
        }
        return histogram;
    }

    public int[][] getColorHistogram() {
        int[][] histogram = new int[3][256];

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int[] pixel = getPixel(i, j);
                ++histogram[0][pixel[0]];
                ++histogram[1][pixel[1]];
                ++histogram[2][pixel[2]];
            }
        }
        return histogram;
    }


}
