package pl.edu.uj.JImageStream.api.transforms;

import java.util.function.Predicate;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;
import pl.edu.uj.JImageStream.model.UnpackedImage;

public class BoundedImageTransform extends AbstractImageTransform {

    protected Predicate<Pixel> predicate;

    public BoundedImageTransform(UnpackedImage image, Filter filter, Predicate<Pixel> predicate) {
        super(image, filter);
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
