package pl.edu.uj.JImageStream.api.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;
import pl.edu.uj.JImageStream.model.UnpackedImage;

public abstract class Filter {

    private ColorChannel[] colorRestrictions;
    protected final Logger logger = LogManager.getLogger(this.getClass());
    private long startTime;

    UnpackedImage unpackedImage;

    public void setSource(UnpackedImage image) {
        this.unpackedImage = image;
    }

    public void setRestrictions(ColorChannel[] colorChannels) {
        this.colorRestrictions = colorChannels;
    }


    public abstract void apply(int x, int y);

    protected void setPixel(int x, int y, Pixel pixel) {
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

    protected Pixel getPixel(int x, int y) {
        int[] pixel = unpackedImage.getPixel(x, y);
        return new Pixel(x, y, pixel[0], pixel[1], pixel[2], pixel[3]);
    }

    protected int getSourceHeight() {
        return unpackedImage.getHeight();
    }

    protected int getSourceWidth() {
        return unpackedImage.getWidth();
    }

    public void setUp() {
        startTime = System.currentTimeMillis();
        logger.info(this.getClass() + " starting filter");
    }

    public void tearDown() {

        logger.info("filter {} has ended, time {} ms", this.getClass(), System.currentTimeMillis() - startTime);
    }

}
