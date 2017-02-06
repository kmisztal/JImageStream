package pl.edu.uj.JImageStream.api.transforms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.api.core.ImageTransform;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;
import pl.edu.uj.JImageStream.model.UnpackedImage;

public abstract class AbstractImageTransform implements ImageTransform {
    protected Logger logger = LogManager.getLogger(this.getClass());

    protected int height;
    protected int width;
    protected UnpackedImage image;
    protected Filter filter;
    protected ColorChannel[] colorChannels;

    public AbstractImageTransform(UnpackedImage image, Filter filter, ColorChannel[] colorChannels) {
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.filter = filter;
        this.colorChannels = colorChannels;
        this.image = image;
    }

    abstract protected void applyToPixels();

    protected final Pixel getPixel(int x, int y) {
        int[] colors = image.getPixel(x, y);
        return new Pixel(x, y, colors[1], colors[2], colors[3], colors[0]);
    }

    @Override
    final public void apply() {
        filter.setSource(image);
        filter.setRestrictions(colorChannels);
        filter.setUp();
        applyToPixels();
        image.update();
        filter.tearDown();
    }
}
