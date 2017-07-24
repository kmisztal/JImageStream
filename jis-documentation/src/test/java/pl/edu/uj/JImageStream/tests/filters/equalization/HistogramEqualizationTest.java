package pl.edu.uj.JImageStream.tests.filters.equalization;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import java.awt.image.BufferedImage;

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
