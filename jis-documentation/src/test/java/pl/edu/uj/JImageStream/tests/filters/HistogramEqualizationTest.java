package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.HistogramEqualization;
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
                .apply(new HistogramEqualization())
                .collect(new BufferedImageCollector());
        // end::histogramEqualizationFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/HistogramEqualizationFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
