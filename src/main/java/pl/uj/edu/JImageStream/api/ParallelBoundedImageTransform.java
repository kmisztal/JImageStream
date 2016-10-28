package pl.uj.edu.JImageStream.api;

import java.awt.Point;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

//todo fix this grebosz
//todo should extend BoundedImageTransform
public class ParallelBoundedImageTransform implements ImageTransform {

    private int height;
    private int width;
    private Predicate<Point> predicate;
    private Filter filter;

    public ParallelBoundedImageTransform(int height, int width, Predicate<Point> predicate, Filter filter) {
        this.height = height;
        this.width = width;
        this.predicate = predicate;
        this.filter = filter;
    }

    @Override
    public void apply() {
        //todo set amount of executors per filter
        //todo don't start more threads than width since won't be used anyway
        ExecutorService executor = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < width; ++i) {
                //todo create class pixel with height and width instead of x y for clarity, can wait
                executor.execute(new PixelExecutor(predicate, filter, i, height));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
