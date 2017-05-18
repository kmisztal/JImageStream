package pl.edu.uj.JImageStream.api.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;
import pl.edu.uj.JImageStream.model.UnpackedImage;

import java.util.stream.IntStream;

public abstract class Filter {

    protected final Logger logger = LogManager.getLogger(this.getClass());
    private long startTime;

    private UnpackedImage unpackedImage;

    public void setSource(UnpackedImage image) {
        this.unpackedImage = image;
    }


    public abstract void apply(int x, int y);

    protected final void setPixel(int x, int y, Pixel pixel) {
        int[] outputColors = pixel.getColors();
        for (int i = 0; i < outputColors.length; ++i) {
            outputColors[i] = Math.min(Math.max(outputColors[i], 0), 255);
        }
        unpackedImage.setPixel(x, y, outputColors[0], outputColors[1], outputColors[2], outputColors[3]);
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

    public final void start() {
        startTime = System.currentTimeMillis();
        logger.info(this.getClass() + " starting filter");
        setUp();
    }

    public final void stop() {
        tearDown();
        logger.info("filter {} has ended, time {} ms", this.getClass(), System.currentTimeMillis() - startTime);
    }

    public void setUp() {
    }

    public void tearDown() {
    }

    protected int[] getGrayScaleHistogram() {
        return unpackedImage.getGrayScaleHistogram();
    }

    public int[][] getColorHistogram() {
        return unpackedImage.getColorHistogram();
    }

}
