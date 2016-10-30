package pl.uj.edu.JImageStream.api;

import pl.uj.edu.JImageStream.model.StreamableImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class ImageStream {

    private static final Predicate<Point> TRUE_PREDICATE = point -> true;

    private BufferedImage imageCopy;
    private List<ImageTransform> filters;
    private Predicate<Point> predicate;

    public ImageStream apply(Filter filter) {
        // [kamil] todo support for ParallelBoundedImageTransform
        filters.add(new BoundedImageTransform(imageCopy, predicate != null ? predicate : TRUE_PREDICATE, filter));
        predicate = null;
        return this;
    }

    public ImageStream(BufferedImage bufferedImage) {
        ColorModel cm = bufferedImage.getColorModel();
        boolean isAlpha = cm.isAlphaPremultiplied();
        this.imageCopy = new BufferedImage(cm, bufferedImage.copyData(null), isAlpha, null);
        filters = new LinkedList<>();
    }

    public ImageStream bounds(Predicate<Point> predicate) {
        this.predicate = predicate;
        return this;
    }

    public StreamableImage collect() {
        // [martyna] todo Collector interface
        if (!filters.isEmpty()) {
            filters.forEach(ImageTransform::apply);
        }
        return new StreamableImage(imageCopy);
    }

    public <T> T collect(Collector<T> collector) {
        if (!filters.isEmpty()) {
            filters.forEach(ImageTransform::apply);
        }
        return collector.collect(imageCopy);
    }

    // [pawel] todo channel() operation

}