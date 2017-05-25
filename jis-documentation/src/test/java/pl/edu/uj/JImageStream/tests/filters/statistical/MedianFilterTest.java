package pl.edu.uj.JImageStream.tests.filters.statistical;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.statistical.MedianFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class MedianFilterTest extends AbstractBaseTest {

    @Test
    public void medianTest() {
        // tag::medianFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .setThreads(50)
                .apply(Filters.medianFilter(9))
                .collect(Collectors.toBufferedImage());
        // end::medianFilter[]

        save(streamableImage, bufferedImage, "MedianFilter.png");

    }

}
