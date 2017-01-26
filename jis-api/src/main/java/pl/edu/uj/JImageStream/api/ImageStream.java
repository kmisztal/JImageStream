package pl.edu.uj.JImageStream.api;


import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.uj.JImageStream.api.core.Collector;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.api.core.ImageTransform;
import pl.edu.uj.JImageStream.api.transforms.BoundedImageTransform;
import pl.edu.uj.JImageStream.api.transforms.ParallelBoundedImageTransform;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;

public class ImageStream {

    private static final Predicate<Pixel> TRUE_PREDICATE = pixel -> true;
    private static final ColorChannel[] ALL_CHANNELS = {ColorChannel.RED,
            ColorChannel.GREEN, ColorChannel.BLUE, ColorChannel.ALPHA};

    private BufferedImage imageCopy;
    private List<ImageTransform> filters;
    private Predicate<Pixel> predicate;
    private ColorChannel[] colorChannels;
    private int numberOfThreads;
    private final int defaultNumberOfThreads;
    private final boolean isParallel;
    private int numberOfFilterApplying;
    private final Logger logger = LogManager.getLogger(this.getClass());


    public ImageStream(BufferedImage bufferedImage, boolean isParallel) {
        ColorModel cm = bufferedImage.getColorModel();
        boolean isAlpha = cm.isAlphaPremultiplied();
        imageCopy = new BufferedImage(cm, bufferedImage.copyData(null), isAlpha, null);
        filters = new LinkedList<>();
        this.isParallel = isParallel;
        if (isParallel) {
            defaultNumberOfThreads = Runtime.getRuntime().availableProcessors();
        } else {
            defaultNumberOfThreads = 1;
        }
        numberOfThreads = defaultNumberOfThreads;
        numberOfFilterApplying = 1;
    }

    public ImageStream apply(Filter filter) {
        if (isParallel) {
            for (int i = 0; i < numberOfFilterApplying; i++) {
                filters.add(new ParallelBoundedImageTransform(imageCopy, filter, colorChannels != null ? colorChannels : ALL_CHANNELS,
                        predicate != null ? predicate : TRUE_PREDICATE, numberOfThreads));
            }
        } else {
            for (int i = 0; i < numberOfFilterApplying; i++) {
                filters.add(new BoundedImageTransform(imageCopy, filter, colorChannels != null ? colorChannels : ALL_CHANNELS,
                        predicate != null ? predicate : TRUE_PREDICATE));
            }
        }

        predicate = null;
        colorChannels = null;
        numberOfThreads = defaultNumberOfThreads;
        numberOfFilterApplying = 1;
        logger.info(filter.getClass() + " has been applied");
        return this;
    }

    public ImageStream bounds(Predicate<Pixel> predicate) {
        this.predicate = predicate;
        return this;
    }

    public ImageStream times(int numberOfFilterApplying) {
        this.numberOfFilterApplying = numberOfFilterApplying;
        return this;
    }

    public ImageStream channel(ColorChannel... colorChannels) {
        this.colorChannels = colorChannels;
        return this;
    }

    public ImageStream setThreads(int numberOfThreads) {
        if (!isParallel) {
            throw new UnsupportedOperationException("Only parallel streams can use multiple threads");
        }
        if (numberOfThreads < 1) {
            logger.warn("Wrong number of threads, one thread set instead");
            this.numberOfThreads = this.defaultNumberOfThreads;
        } else {
            this.numberOfThreads = numberOfThreads;
        }
        return this;
    }

    public <T> T collect(Collector<T> collector) {
        if (!filters.isEmpty()) {
            filters.forEach(ImageTransform::apply);
        }
        return collector.collect(imageCopy);
    }

    public ImageStream resize(int x, int y){
        return resize(x,y,new Pixel(255,255,255,255));
    }

    public ImageStream resize(int x, int y, Pixel p){
        WritableRaster output = ColorModel.getRGBdefault().createCompatibleWritableRaster(x,y);
        WritableRaster input = imageCopy.copyData(null);

        int nx = (x-imageCopy.getWidth())/2;
        int ny = (y-imageCopy.getHeight())/2;
        int []pix = {p.getRed(),p.getGreen(),p.getBlue(),p.getAlpha()};

        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(i < nx || i >= x-nx || j < ny || j >= y-ny){
                    output.setPixel(i,j,pix);
                } else{
                    output.setPixel(i,j,input.getPixel(i-nx,j-ny,(int[]) null));
                }
            }
        }
        imageCopy = new BufferedImage(imageCopy.getColorModel(),output,imageCopy.getColorModel().isAlphaPremultiplied(),null);
        return this;
    }

}