package pl.edu.uj.JImageStream.tests.filters.statistical;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.statistical.MinFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class MinFilterTest extends AbstractBaseTest {

    @Test
    public void minTest() {
        // tag::minFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .setThreads(50)
                .apply(Filters.minFilter(9))
                .collect(Collectors.toBufferedImage());
        // end::minFilter[]

        save(streamableImage, bufferedImage, "minFilter.png");
    }

}
