package pl.edu.uj.JImageStream.api.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;
import pl.edu.uj.JImageStream.model.UnpackedImage;

import java.util.stream.IntStream;

public abstract class Filter {

    protected final Logger logger = LogManager.getLogger(this.getClass());
    private ColorChannel[] colorRestrictions;
    private long startTime;

    private UnpackedImage unpackedImage;

    public void setSource(UnpackedImage image) {
        this.unpackedImage = image;
    }

    public final void setRestrictions(ColorChannel[] colorChannels) {
        this.colorRestrictions = colorChannels;
    }


    public abstract void apply(int x, int y);

    protected final void setPixel(int x, int y, Pixel pixel) {
        int[] sourceColors = getPixel(x, y).getColors();
        int[] outputColors = pixel.getColors();

        for (ColorChannel colorRestriction : colorRestrictions) {
            colorRestriction.process(sourceColors, outputColors);
        }

        for (int i = 0; i < sourceColors.length; ++i) {
            sourceColors[i] = Math.min(Math.max(sourceColors[i], 0), 255);
        }
        unpackedImage.setPixel(x, y, sourceColors[0], sourceColors[1], sourceColors[2], sourceColors[3]);
    }

    protected final Pixel getPixel(int x, int y) {
        int[] pixel = unpackedImage.getPixel(x, y);
        return new Pixel(x, y, pixel[0], pixel[1], pixel[2], pixel[3]);
    }

    protected final int getSourceHeight() {
        return unpackedImage.getHeight();
    }

    protected final int getSourceWidth() {
        return unpackedImage.getWidth();
    }

    public void setUp() {
        startTime = System.currentTimeMillis();
        logger.info(this.getClass() + " starting filter");
    }

    public void tearDown() {
        logger.info("filter {} has ended, time {} ms", this.getClass(), System.currentTimeMillis() - startTime);
    }

    protected int[] getGreyscaleHistogram() {
        int[] histogram = new int[255];

        for (int i = 0; i < getSourceWidth(); ++i) {
            for (int j = 0; j < getSourceHeight(); ++j) {
                ++histogram[(int) IntStream.of(getPixel(i, j).getColors()).limit(3).average().getAsDouble()];
            }
        }
        return histogram;
    }

    protected int[][] getColorHistogram() {
        int[][] histogram = new int[3][256];

        for (int i = 0; i < getSourceWidth(); ++i) {
            for (int j = 0; j < getSourceHeight(); ++j) {
                Pixel pixel = getPixel(i, j);
                ++histogram[0][pixel.getRed()];
                ++histogram[1][pixel.getGreen()];
                ++histogram[2][pixel.getBlue()];
            }
        }
        return histogram;
    }

}
