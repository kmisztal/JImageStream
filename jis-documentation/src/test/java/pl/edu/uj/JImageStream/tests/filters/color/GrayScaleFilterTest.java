package pl.edu.uj.JImageStream.tests.filters.color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class GrayScaleFilterTest extends AbstractBaseTest {
    @Test
    public void grayScaleFilterTest() {

        // tag::grayscaleFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(Filters.grayScaleFilter())
                .collect(Collectors.toBufferedImage());
        // end::grayscaleFilter[]

        save(streamableImage, bufferedImage, "GrayScaleFilter.png");
    }
}
