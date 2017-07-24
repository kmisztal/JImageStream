package pl.edu.uj.JImageStream.tests.filters.statistical;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import java.awt.image.BufferedImage;

public class MaxFilterTest extends AbstractBaseTest {

    @Test
    public void maxTest() {
        // tag::maxFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .setThreads(50)
                .apply(Filters.maxFilter(9))
                .collect(Collectors.toBufferedImage());
        // end::maxFilter[]

        save(streamableImage, bufferedImage, "maxFilter.png");
    }

}
