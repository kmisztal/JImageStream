package pl.edu.uj.JImageStream.tests.performance;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.uj.JImageStream.api.core.Collector;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.FileCollector;
import pl.edu.uj.JImageStream.model.StreamableImage;
import pl.edu.uj.JImageStream.tests.performance.Util.CsvCreator;

public class CollectImageTest extends AbstractTest {


    private static CsvCreator csvCollectors;

    @BeforeClass
    public static void setUpClass() {
        csvCollectors = new CsvCreator("collectors.csv");
    }

    @Test
    public void BufferedImageCollector() {
        runCollector(new BufferedImageCollector());
    }

    @Test
    public void FileCollector() {
        runCollector(new FileCollector("png", "target/temp.png"));
    }

    private void runCollector(Collector collector) {
        int times = 10;

        StreamableImage small = new StreamableImage(getBufferedImage(100, 100));
        StreamableImage medium = new StreamableImage(getBufferedImage(1000, 1000));
        StreamableImage big = new StreamableImage(getBufferedImage(10000, 10000));

        long smallTime = System.currentTimeMillis();
        for (int i = 0; i < times; ++i) {
            small.stream().collect(collector);
        }
        csvCollectors.add(collector.getClass().getSimpleName(), String.valueOf(times), "100x100", (System.currentTimeMillis() - smallTime) / times);

        long mediumTime = System.currentTimeMillis();
        for (int i = 0; i < times; ++i) {
            medium.stream().collect(collector);
        }
        csvCollectors.add(collector.getClass().getSimpleName(), String.valueOf(times), "1000x1000", (System.currentTimeMillis() - mediumTime) / times);

//        long bigTime = System.currentTimeMillis();
//        for (int i = 0; i < times; ++i) {
//            big.stream().collect(collector);
//        }
//        csvCollectors.add(collector.getClass().getSimpleName(), String.valueOf(times), "10000x10000", (System.currentTimeMillis() - bigTime) / times);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        csvCollectors.save("target/out/");
    }

}
