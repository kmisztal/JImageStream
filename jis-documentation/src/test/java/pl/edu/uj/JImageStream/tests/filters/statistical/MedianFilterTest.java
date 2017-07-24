package pl.edu.uj.JImageStream.tests.filters.statistical;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import java.awt.image.BufferedImage;

public class MedianFilterTest extends AbstractBaseTest {

    @Test
    public void medianTest() {
        // tag::medianFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .setThreads(50)
                .apply(Filters.medianFilter(9))
                .collect(Collectors.toBufferedImage());
        // end::medianFilter[]

        save(streamableImage, bufferedImage, "medianFilter.png");

    }

}
