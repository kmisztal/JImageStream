package pl.edu.uj.JImageStream.tests.filters.equalization;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.equalization.HistogramEqualization;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HistogramEqualizationTest extends AbstractBaseTest {

    @Test
    public void histogramEqualizationTest() {
        // tag::histogramEqualizationFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .apply(Filters.histogramEqualizationFilter())
                .collect(Collectors.toBufferedImage());
        // end::histogramEqualizationFilter[]

        save(streamableImage, bufferedImage, "HistogramEqualizationFilter.png");

    }
}
