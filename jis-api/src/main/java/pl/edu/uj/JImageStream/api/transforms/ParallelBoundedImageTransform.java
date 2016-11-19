package pl.edu.uj.JImageStream.api.transforms;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.ColorChannel;

public class ParallelBoundedImageTransform extends BoundedImageTransform {

    private int numberOfThreads;

    public ParallelBoundedImageTransform(BufferedImage bufferedImage, Filter filter, ColorChannel[] colorChannels, Predicate<Point> predicate, int numberOfThreads) {
        super(bufferedImage, filter, colorChannels, predicate);
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

        private Predicate<Point> predicate;
        private Filter filter;
        private int i;
        private int height;

        PixelExecutor(Predicate<Point> predicate, Filter filter, int i, int height) {
            this.predicate = predicate;
            this.filter = filter;
            this.i = i;
            this.height = height;
        }

        @Override
        public void run() {
            for (int j = 0; j < height; ++j) {
                if (predicate.test(new Point(i, j))) {
                    filter.apply(i, j);
                }
            }
        }
    }
}
