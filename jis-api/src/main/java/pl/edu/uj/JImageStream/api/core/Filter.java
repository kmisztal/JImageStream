package pl.edu.uj.JImageStream.api.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public abstract class Filter {

    private WritableRaster source;
    private WritableRaster output;
    private ColorChannel[] colorRestrictions;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private long startTime;

    public void setSource(BufferedImage bufferedImage) {
        this.source = bufferedImage.copyData(null);
        this.output = bufferedImage.copyData(null);
    }

    public void setRestrictions(ColorChannel[] colorChannels) {
        this.colorRestrictions = colorChannels;
    }

    public void saveToImage(BufferedImage bufferedImage) {
        bufferedImage.setData(output);
    }

    public abstract void apply(int x, int y);

    protected void setPixel(int x, int y, Pixel pixel) {
        int[] sourceColors = new int[4];
        source.getPixel(x, y, sourceColors);
        int[] outputColors = pixel.getColors();

        for (ColorChannel colorRestriction : colorRestrictions) {
            colorRestriction.process(sourceColors, outputColors);
        }

        for (int i = 0; i < sourceColors.length; ++i) {
            sourceColors[i] = Math.min(Math.max(sourceColors[i], 0), 255);
        }

        output.setPixel(x, y, sourceColors);
    }

    protected Pixel getPixel(int x, int y) {
        int[] pixel = source.getPixel(x, y, (int[]) null);
        return new Pixel(pixel[0], pixel[1], pixel[2], pixel[3]);
    }

    protected int getSourceHeight() {
        return source.getHeight();
    }

    protected int getSourceWidth() {
        return source.getWidth();
    }

    public void setUp() {
        startTime = System.currentTimeMillis();
        logger.info("starting filter");
    }

    public void tearDown() {

        logger.info("ending filter");
        logger.info(String.valueOf(System.currentTimeMillis() - startTime) + " milliseconds - total filtering time");
    }

}
