package pl.edu.uj.JImageStream.tests.performance;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.color.GreenFilter;
import pl.edu.uj.JImageStream.filters.convolve.BoxBlurFilter;
import pl.edu.uj.JImageStream.filters.convolve.EmbossFilter;
import pl.edu.uj.JImageStream.filters.noise.GaussFilter;
import pl.edu.uj.JImageStream.filters.noise.SaltAndPepperFilter;
import pl.edu.uj.JImageStream.filters.statistical.MeanFilter;
import pl.edu.uj.JImageStream.filters.statistical.MedianFilter;
import pl.edu.uj.JImageStream.model.StreamableImage;
import pl.edu.uj.JImageStream.tests.performance.Util.CsvCreator;

public class FilterTest extends AbstractTest {

    private static CsvCreator csvFilters;

    @BeforeClass
    public static void setUpClass() {
        csvFilters = new CsvCreator("filters.csv");
    }

    @Test
    public void linearFilters() {
        runFilter(new GrayScaleFilter(), 10);
        runFilter(new SaltAndPepperFilter(), 10);
        runFilter(new GreenFilter(), 10);
    }

    @Test
    public void smallMatrixFilters() {
        runPararellFilter(new EmbossFilter(), 10, Integer.MAX_VALUE);
        runPararellFilter(new GaussFilter(5, 0.1), 10, Integer.MAX_VALUE);
        runPararellFilter(new BoxBlurFilter(), 10, Integer.MAX_VALUE);
    }

    @Test
    public void BigMatrixFilters() {
        runPararellFilter(new MeanFilter(7), 10, Integer.MAX_VALUE);
        runPararellFilter(new MedianFilter(7), 10, Integer.MAX_VALUE);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        csvFilters.save("target/out/");
    }

    private void runFilter(Filter filter, int times) {
        BufferedImageCollector bufferedImageCollector = new BufferedImageCollector();
        StreamableImage small = new StreamableImage(getBufferedImage(100, 100));
        StreamableImage medium = new StreamableImage(getBufferedImage(1000, 1000));
        StreamableImage big = new StreamableImage(getBufferedImage(5000, 5000));

        long smallTime = System.currentTimeMillis();
        for (int i = 0; i < times; ++i) {
            small.stream().apply(filter).collect(bufferedImageCollector);
        }
        csvFilters.add(filter.getClass().getCanonicalName(), String.valueOf(times), "100x100", (System.currentTimeMillis() - smallTime) / times);

        long mediumTime = System.currentTimeMillis();
        for (int i = 0; i < times; ++i) {
            medium.stream().apply(filter).collect(bufferedImageCollector);
        }
        csvFilters.add(filter.getClass().getCanonicalName(), String.valueOf(times), "1000x1000", (System.currentTimeMillis() - mediumTime) / times);

//        long bigTime = System.currentTimeMillis();
//        for (int i = 0; i < times; ++i) {
//            big.stream().apply(filter).collect(bufferedImageCollector);
//        }
//        csvFilters.add(filter.getClass().getCanonicalName(), String.valueOf(times), "5000x5000", (System.currentTimeMillis() - bigTime) / times);
    }

    private void runPararellFilter(Filter filter, int times, int threads) {
        BufferedImageCollector bufferedImageCollector = new BufferedImageCollector();
        StreamableImage small = new StreamableImage(getBufferedImage(100, 100));
        StreamableImage medium = new StreamableImage(getBufferedImage(1000, 1000));
        StreamableImage big = new StreamableImage(getBufferedImage(5000, 5000));

        long smallTime = System.currentTimeMillis();
        for (int i = 0; i < times; ++i) {
            small.parallelStream().setThreads(threads).apply(filter).collect(bufferedImageCollector);
        }
        csvFilters.add(filter.getClass().getCanonicalName(), String.valueOf(times), "100x100", (System.currentTimeMillis() - smallTime) / times);

        long mediumTime = System.currentTimeMillis();
        for (int i = 0; i < times; ++i) {
            medium.parallelStream().setThreads(threads).apply(filter).collect(bufferedImageCollector);
        }
        csvFilters.add(filter.getClass().getCanonicalName(), String.valueOf(times), "1000x1000", (System.currentTimeMillis() - mediumTime) / times);

//        long bigTime = System.currentTimeMillis();
//        for (int i = 0; i < times; ++i) {
//            big.parallelStream().setThreads(threads).apply(filter).collect(bufferedImageCollector);
//        }
//        csvFilters.add(filter.getClass().getCanonicalName(), String.valueOf(times), "5000x5000", (System.currentTimeMillis() - bigTime) / times);
    }
}


