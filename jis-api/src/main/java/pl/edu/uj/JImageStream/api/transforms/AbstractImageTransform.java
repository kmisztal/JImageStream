package pl.edu.uj.JImageStream.api.transforms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.api.core.ImageTransform;
import pl.edu.uj.JImageStream.model.Pixel;
import pl.edu.uj.JImageStream.model.UnpackedImage;

public abstract class AbstractImageTransform implements ImageTransform {
    protected Logger logger = LogManager.getLogger(this.getClass());

    protected int height;
    protected int width;
    protected UnpackedImage image;
    protected Filter filter;

    public AbstractImageTransform(UnpackedImage image, Filter filter) {
        this.filter = filter;
        this.image = image;
    }

    abstract protected void applyToPixels();

    protected final Pixel getPixel(int x, int y) {
        int[] colors = image.getPixel(x, y);
        return new Pixel(x, y, colors[0], colors[1], colors[2], colors[3]);
    }

    @Override
    final public void apply() {
        height = image.getHeight();
        width = image.getWidth();

        filter.setSource(image);
        filter.start();
        applyToPixels();
        image.update();
        filter.stop();
    }
}
