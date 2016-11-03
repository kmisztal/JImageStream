package pl.uj.edu.JImageStream.api;

import pl.uj.edu.JImageStream.api.collectors.Collector;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.EmptyStackException;
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
    private int numberOfThreads;
    private int defaultNumberOfThreads;
    private boolean parallel;


    public ImageStream apply(Filter filter) {
        if(parallel){
            filters.add(new ParallelBoundedImageTransform(imageCopy, predicate != null ? predicate : TRUE_PREDICATE, filter,
                    colorChannels != null ? colorChannels : ALL_CHANNELS, numberOfThreads));
        }else {
            filters.add(new BoundedImageTransform(imageCopy, predicate != null ? predicate : TRUE_PREDICATE, filter,
                    colorChannels != null ? colorChannels : ALL_CHANNELS));
        }
        predicate = null;
        colorChannels = null;
        numberOfThreads = defaultNumberOfThreads;
        return this;
    }


    public ImageStream(BufferedImage bufferedImage, boolean parallel) {
        ColorModel cm = bufferedImage.getColorModel();
        boolean isAlpha = cm.isAlphaPremultiplied();
        imageCopy = new BufferedImage(cm, bufferedImage.copyData(null), isAlpha, null);
        filters = new LinkedList<>();
        this.parallel = parallel;
        if(parallel){
            defaultNumberOfThreads = Runtime.getRuntime().availableProcessors();
        }else{
            defaultNumberOfThreads = 1;
        }
        numberOfThreads = defaultNumberOfThreads;
    }


    public ImageStream bounds(Predicate<Point> predicate) {
        this.predicate = predicate;
        return this;
    }

    public ImageStream channel(ColorChannel... colorChannels) {
        this.colorChannels = colorChannels;
        return this;
    }

    public ImageStream setThreads(int numberOfThreads) throws Exception{
        if(!parallel){
            throw new UnsupportedOperationException("Only parallel streams can use multiple threads");
        }
        if(numberOfThreads < 1){
            throw new Exception("Number of threads must be at least 1");
        }
        this.numberOfThreads = numberOfThreads;
        return this;
    }

    public <T> T collect(Collector<T> collector) {
        if (!filters.isEmpty()) {
            filters.forEach(ImageTransform::apply);
        }
        return collector.collect(imageCopy);
    }

}