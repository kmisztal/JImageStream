package pl.edu.uj.JImageStream.tests.filters.statistical;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.statistical.MeanFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class MeanFilterTest extends AbstractBaseTest {

    @Test
    public void meanTest() {
        // tag::meanFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .setThreads(50)
                .apply(Filters.meanFilter(9))
                .collect(Collectors.toBufferedImage());
        // end::meanFilter[]

        save(streamableImage, bufferedImage, "meanFilter.png");

    }

}
