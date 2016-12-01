package pl.edu.uj.JImageStream.api.transforms;

import java.awt.image.BufferedImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.api.core.ImageTransform;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;

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

    protected final Pixel getPixel(int x, int y) {
        int[] colors = image.getRaster().getPixel(x, y, (int[]) null);
        return new Pixel(x, y, colors[0], colors[1], colors[2], colors[3]);
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
