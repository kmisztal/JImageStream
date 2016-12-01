package pl.edu.uj.JImageStream.api.transforms;

import java.awt.image.BufferedImage;
import java.util.function.Predicate;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;

public class BoundedImageTransform extends AbstractImageTransform {

    protected Predicate<Pixel> predicate;

    public BoundedImageTransform(BufferedImage bufferedImage, Filter filter, ColorChannel[] colorChannels, Predicate<Pixel> predicate) {
        super(bufferedImage, filter, colorChannels);
        this.predicate = predicate;
    }

    protected void applyToPixels() {
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (predicate.test(getPixel(i, j))) {
                    filter.apply(i, j);
                }
            }
        }
    }
}
