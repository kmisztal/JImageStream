package pl.uj.edu.JImageStream.api;

import pl.uj.edu.JImageStream.api.collectors.Collector;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class ImageStream {

    private static final Predicate<Point> TRUE_PREDICATE = point -> true;
    private static final ColorChannel[] ALL_CHANNELS = {ColorChannel.RED,
                                                        ColorChannel.GREEN,
                                                        ColorChannel.BLUE,
                                                        ColorChannel.ALPHA};

    private BufferedImage imageCopy;
    private List<ImageTransform> filters;
    private Predicate<Point> predicate;
    private ColorChannel[] colorChannels;

    public ImageStream apply(Filter filter) {
        // [kamil] todo support for ParallelBoundedImageTransform
        filters.add(new BoundedImageTransform(imageCopy, predicate != null ? predicate : TRUE_PREDICATE, filter,
                                                colorChannels != null ? colorChannels : ALL_CHANNELS));
        predicate = null;
        colorChannels = null;
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

    public ImageStream channel(ColorChannel... colorChannels) {
        this.colorChannels = colorChannels;
        return this;
    }

    public <T> T collect(Collector<T> collector) {
        if (!filters.isEmpty()) {
            filters.forEach(ImageTransform::apply);
        }
        return collector.collect(imageCopy);
    }

}