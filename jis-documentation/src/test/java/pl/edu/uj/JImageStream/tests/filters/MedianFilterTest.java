package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.statistical.MedianFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class MedianFilterTest extends AbstractBaseTest {

    @Test
    public void medianTest() {
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .setThreads(50).apply(new MedianFilter(21))
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/MedianFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
