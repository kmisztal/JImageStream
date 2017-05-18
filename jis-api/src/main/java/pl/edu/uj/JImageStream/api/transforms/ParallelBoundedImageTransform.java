package pl.edu.uj.JImageStream.api.transforms;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;
import pl.edu.uj.JImageStream.model.UnpackedImage;

public class ParallelBoundedImageTransform extends BoundedImageTransform {

    private int numberOfThreads;

    public ParallelBoundedImageTransform(UnpackedImage image, Filter filter, Predicate<Pixel> predicate, int numberOfThreads) {
        super(image, filter, predicate);
        this.numberOfThreads = numberOfThreads;
    }

    @Override
    protected void applyToPixels() {
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(numberOfThreads, width));
        for (int i = 0; i < width; ++i) {
            executor.execute(new PixelExecutor(predicate, filter, i, height));
        }
        try {
            executor.shutdown();
            executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            executor.shutdownNow();
        }
    }

    private class PixelExecutor implements Runnable {

        private Predicate<Pixel> predicate;
        private Filter filter;
        private int i;
        private int height;

        PixelExecutor(Predicate<Pixel> predicate, Filter filter, int i, int height) {
            this.predicate = predicate;
            this.filter = filter;
            this.i = i;
            this.height = height;
        }

        @Override
        public void run() {
            for (int j = 0; j < height; ++j) {
                if (predicate.test(getPixel(i, j))) {
                    filter.apply(i, j);
                }
            }
        }
    }
}
