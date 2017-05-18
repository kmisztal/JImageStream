package pl.edu.uj.JImageStream.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.uj.JImageStream.api.core.Collector;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.api.core.ImageTransform;
import pl.edu.uj.JImageStream.api.transforms.BoundedImageTransform;
import pl.edu.uj.JImageStream.api.transforms.ParallelBoundedImageTransform;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;
import pl.edu.uj.JImageStream.model.UnpackedImage;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class ImageStream {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    protected UnpackedImage image;
    protected List<ImageTransform> transforms;

    private static final Predicate<Pixel> TRUE_PREDICATE = pixel -> true;

    private Predicate<Pixel> predicate;
    private int numberOfThreads;
    private final int defaultNumberOfThreads;
    private final boolean isParallel;
    private int numberOfFilterApplying;

    public ImageStream(BufferedImage bufferedImage, boolean isParallel) {
        image = new UnpackedImage(bufferedImage);
        transforms = new LinkedList<>();
        this.isParallel = isParallel;
        if (isParallel) {
            defaultNumberOfThreads = Runtime.getRuntime().availableProcessors();
        } else {
            defaultNumberOfThreads = 1;
        }
        numberOfThreads = defaultNumberOfThreads;
        numberOfFilterApplying = 1;
    }

    public final ImageStream apply(Filter filter) {
        if (isParallel) {
            for (int i = 0; i < numberOfFilterApplying; i++) {
                transforms.add(new ParallelBoundedImageTransform(image, filter,
                        predicate != null ? predicate : TRUE_PREDICATE, numberOfThreads));
            }
        } else {
            for (int i = 0; i < numberOfFilterApplying; i++) {
                transforms.add(new BoundedImageTransform(image, filter,
                        predicate != null ? predicate : TRUE_PREDICATE));
            }
        }

        predicate = null;
        numberOfThreads = defaultNumberOfThreads;
        numberOfFilterApplying = 1;
        LOGGER.info(filter.getClass() + " has been applied");
        return this;
    }

    public final ImageStream bounds(Predicate<Pixel> predicate) {
        this.predicate = predicate;
        return this;
    }

    public final ImageStream times(int numberOfFilterApplying) {
        this.numberOfFilterApplying = numberOfFilterApplying;
        return this;
    }

    public final ImageStream setThreads(int numberOfThreads) {
        if (!isParallel) {
            throw new UnsupportedOperationException("Only parallel streams can use multiple threads");
        }
        if (numberOfThreads < 1) {
            LOGGER.warn("Wrong number of threads, one thread set instead");
            this.numberOfThreads = this.defaultNumberOfThreads;
        } else {
            this.numberOfThreads = numberOfThreads;
        }
        return this;
    }

    public final ImageStream resize(int width, int height) {
        transforms.add(() -> image.resize(width, height));
        return this;
    }

    public final <T> T collect(Collector<T> collector) {
        if (!transforms.isEmpty()) {
            transforms.forEach(ImageTransform::apply);
        }
        return collector.collect(image.getBufferedImage());
    }


}