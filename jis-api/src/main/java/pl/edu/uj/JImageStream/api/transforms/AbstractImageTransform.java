package pl.edu.uj.JImageStream.api.transforms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.api.core.ImageTransform;
import pl.edu.uj.JImageStream.model.ColorChannel;

import java.awt.image.BufferedImage;

public abstract class AbstractImageTransform implements ImageTransform {
    protected Logger logger = LogManager.getLogger(this.getClass());

    protected int height;
    protected int width;
    protected BufferedImage image;
    protected Filter filter;
    protected ColorChannel[] colorChannels;

    public AbstractImageTransform(BufferedImage bufferedImage, Filter filter, ColorChannel[] colorChannels) {
        this.height = bufferedImage.getHeight();
        this.width = bufferedImage.getWidth();
        this.filter = filter;
        this.image = bufferedImage;
        this.colorChannels = colorChannels;
    }

    abstract protected void applyToPixels();

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
