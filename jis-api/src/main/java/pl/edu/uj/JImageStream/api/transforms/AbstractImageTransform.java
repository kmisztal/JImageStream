package pl.edu.uj.JImageStream.api.transforms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.api.core.ImageTransform;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;

import java.awt.image.BufferedImage;

public abstract class AbstractImageTransform implements ImageTransform {
    protected Logger logger = LogManager.getLogger(this.getClass());

    protected int height;
    protected int width;
    protected BufferedImage image;
    protected Filter filter;
    protected ColorChannel[] colorChannels;

    public AbstractImageTransform(BufferedImage bufferedImage, Filter filter, ColorChannel[] colorChannels) {
        this.image = bufferedImage;
        this.height = bufferedImage.getHeight();
        this.width = bufferedImage.getWidth();
        this.filter = filter;
        this.colorChannels = colorChannels;
    }

    abstract protected void applyToPixels();

    protected Pixel getPixel(int x, int y) {
        int[] pixel = image.getRaster().getPixel(x, y, (int[]) null);
        return new Pixel(x, y, pixel[0], pixel[1], pixel[2], pixel[3]);
    }

    @Override
    final public void apply() {
        filter.setSource(image);
        filter.setRestrictions(colorChannels);
        filter.setUp();
        applyToPixels();
        filter.saveToImage(image);
        filter.tearDown();
    }
}
