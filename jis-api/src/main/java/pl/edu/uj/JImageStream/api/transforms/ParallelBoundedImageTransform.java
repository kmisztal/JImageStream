package pl.edu.uj.JImageStream.api.transforms;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.api.core.ImageTransform;
import pl.edu.uj.JImageStream.model.ColorChannel;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

//todo fix this grebosz
public class ParallelBoundedImageTransform implements ImageTransform {

    //todo create default filter for whole image
    //todo list of predicate
    private int height;
    private int width;
    private BufferedImage image;
    private Predicate<Point> predicate;
    private Filter filter;
    private ColorChannel[] colorChannels;
    private int numberOfThreads;

    public ParallelBoundedImageTransform(BufferedImage bufferedImage, Predicate<Point> predicate, Filter filter, ColorChannel[] colorChannels, int numberOfThreads){
        this.height = bufferedImage.getHeight();
        this.width = bufferedImage.getWidth();
        this.predicate = predicate;
        this.filter = filter;
        this.image = bufferedImage;
        this.colorChannels = colorChannels;
        this.numberOfThreads = numberOfThreads;
    }

    @Override
    public void apply() {
        //todo set executors from stream
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(numberOfThreads, width));
        filter.setSource(image);
        filter.setRestrictions(colorChannels);

        for (int i = 0; i < width; ++i) {
                //todo create class pixel with height and width instead of x y for clarity
                executor.execute(new PixelExecutor(predicate, filter, i, height));
        }

        try {
            executor.shutdown();
            executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdownNow();
            filter.saveToImage(image);
        }
    }

    class PixelExecutor implements Runnable {

        private Predicate<Point> predicate;
        private Filter filter;
        private int i;
        private int height;

        public PixelExecutor(Predicate<Point> predicate, Filter filter, int i, int height) {
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
