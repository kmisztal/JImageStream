package pl.edu.uj.JImageStream.filters.statistical;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;
import pl.edu.uj.JImageStream.utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawHistogram extends Filter {

    private int[][] histogram = new int[4][256];    // 0=R, 1=G, 2=B, 3=A
    private boolean[] channel = new boolean[4];     // 0=R, 1=G, 2=B, 3=A
    private BufferedImage image = null;
    private Color[][] outputT;
    private int outputWidth;
    private int outputHeight;

    private final int TOPMARGIN = 0;                // top and bottom margin
    private boolean readyToApply;
    private boolean blackAndWhite;

    public DrawHistogram(BufferedImage image, ColorChannel... channels) {
        readyToApply = false;
        blackAndWhite = false;

        if (!isBufferedImageTypeProper(image.getType())) {
            this.image = ImageUtils.convert(image, BufferedImage.TYPE_INT_ARGB);
        } else {
            this.image = ImageUtils.convert(image, image.getType());
        }

        if (channels.length > 0) {
            setChannels(channels);
        } else {
            blackAndWhite = true;
        }
    }

    private void init() {
        outputHeight = getSourceHeight();
        outputWidth = getSourceWidth();
        outputT = new Color[outputWidth][outputHeight];
        for (int i = 0; i < outputWidth; ++i) {
            for (int j = 0; j < outputHeight; ++j) {
                outputT[i][j] = new Color(0x00000000, true); //100% transparent pixel
            }
        }

        if ((outputWidth >= 256) && (image != null)) {
            for (int j = 0; j < 256; ++j) {
                for (int i = 0; i < 4; ++i) {
                    histogram[i][j] = 0;
                }
            }

            if (blackAndWhite) {
                calculateBlackAndWhiteHistogram();
            } else {
                calculateRGBHistogram();
            }
            setScale();
            drawHistogram();

        } else {
            logger.warn("Cannot draw a histogram");
        }
        readyToApply = true;
    }

    private void calculateBlackAndWhiteHistogram() {
        for (int i = 0; i < image.getWidth(); ++i) {
            for (int j = 0; j < image.getHeight(); ++j) {
                Color color = new Color(image.getRGB(i, j), true);
                histogram[3][color.getAlpha()]++;
                if (color.getAlpha() != 0) {
                    histogram[0][color.getRed()]++;
                    histogram[0][color.getGreen()]++;
                    histogram[0][color.getBlue()]++;
                }
            }
        }

        for (int i = 0; i < 256; ++i) {
            histogram[0][i] /= 3;
        }

        if (isAlphaIdentical()) {
            for (int j = 0; j < 256; ++j) {
                histogram[3][j] = 0;
            }
        }
    }

    private void calculateRGBHistogram() {
        for (int i = 0; i < image.getWidth(); ++i) {
            for (int j = 0; j < image.getHeight(); ++j) {
                Color color = new Color(image.getRGB(i, j), true);
                histogram[3][color.getAlpha()]++;
                if (color.getAlpha() != 0) {
                    histogram[0][color.getRed()]++;
                    histogram[1][color.getGreen()]++;
                    histogram[2][color.getBlue()]++;
                }
            }
        }

        if (isAlphaIdentical()) {
            channel[3] = false;
        }

        for (int i = 0; i < 4; ++i) {
            if (!channel[i]) {
                for (int j = 0; j < 256; ++j) {
                    histogram[i][j] = 0;
                }
            }
        }
    }

    private void setScale() {

        double yScale;
        int maxV;

        if (blackAndWhite) {
            maxV = histogram[0][0];
            for (int i = 1; i < 256; ++i) {
                if (histogram[0][i] > maxV) {
                    maxV = histogram[0][i];
                }
            }
            yScale = maxV;
            yScale /= (outputHeight - 2 * TOPMARGIN);
            if (yScale != 0) {
                for (int i = 0; i < 256; ++i) {
                    histogram[0][i] /= yScale;
                }
            }

        } else {

            maxV = 0;
            for (int k = 0; k < 3; ++k) {
                if (channel[k]) {
                    for (int i = 0; i < 256; ++i) {
                        if (histogram[k][i] > maxV) {
                            maxV = histogram[k][i];
                        }
                    }
                }
            }
            yScale = maxV;
            yScale /= (outputHeight - 2 * TOPMARGIN);
            if (yScale != 0) {
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 256; ++j) {
                        histogram[i][j] /= yScale;
                    }
                }
            }
        }

        maxV = histogram[3][0];
        for (int i = 1; i < 256; ++i) {
            if (histogram[3][i] > maxV) {
                maxV = histogram[3][i];
            }
        }
        double alphaScale = maxV;
        alphaScale /= (outputHeight - 2 * TOPMARGIN);
        if (alphaScale != 0) {
            for (int i = 0; i < 256; ++i) {
                histogram[3][i] /= alphaScale;
            }
        }
    }


    private void drawHistogram() {

        int barWidth = outputWidth / 256;
        int leftmargin = outputWidth - 256 * barWidth;
        leftmargin /= 2;
        int rightmargin = leftmargin;
        if ((leftmargin * 2) != (outputWidth - 256 * barWidth)) {
            leftmargin++;
        }

        int index = 0;
        for (int i = leftmargin; i < outputWidth - rightmargin; ++i) {
            for (int j = TOPMARGIN; j < outputHeight - TOPMARGIN; ++j) {
                int color = 0xFF000000;     // background color
                if (blackAndWhite) {
                    if (histogram[0][index / barWidth] >= outputHeight - TOPMARGIN - j) {
                        color += 0xb0b0b0;
                    }
                } else {
                    if (histogram[0][index / barWidth] >= outputHeight - TOPMARGIN - j) {
                        color += 0xc80000;
                    }
                    if (histogram[1][index / barWidth] >= outputHeight - TOPMARGIN - j) {
                        color += 0x00c800;
                    }
                    if (histogram[2][index / barWidth] >= outputHeight - TOPMARGIN - j) {
                        color += 0x0000c8;
                    }
                    if (color == 0xFFc8c8c8) {
                        color = 0xFFb0b0b0;
                    }
                    if (histogram[3][index / barWidth] >= outputHeight - TOPMARGIN - j) {
                        color += 0x323232;
                    }
                }
                outputT[i][j] = new Color(color, true);
            }
            index++;
        }
    }

    private void setChannels(ColorChannel[] channels) {
        for (int i = 0; i < 4; ++i) {
            channel[i] = false;
        }
        for (ColorChannel c : channels) {
            switch (c) {
                case RED:
                    channel[0] = true;
                    break;
                case GREEN:
                    channel[1] = true;
                    break;
                case BLUE:
                    channel[2] = true;
                    break;
                case ALPHA:
                    channel[3] = true;
                    break;
                default:
                    break;
            }
        }
    }

    private boolean isAlphaIdentical() {
        int moreThanZeroColumns = 0;
        for (int i = 0; i < 256; ++i) {
            if (histogram[3][i] > 0) {
                moreThanZeroColumns++;
            }
        }
        return moreThanZeroColumns <= 1;
    }

    private boolean isBufferedImageTypeProper(int type) {
        return type == 2 || type == 3 || type == 6 || type == 7;
    }

    @Override
    public void apply(int x, int y) {
        if (!readyToApply) {
            init();
        }
        setPixel(x, y, new Pixel(x, y, outputT[x][y].getRed(), outputT[x][y].getGreen(), outputT[x][y].getBlue(), outputT[x][y].getAlpha()));
    }
}