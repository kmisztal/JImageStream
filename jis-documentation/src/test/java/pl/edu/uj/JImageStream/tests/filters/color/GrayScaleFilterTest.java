package pl.edu.uj.JImageStream.tests.filters.color;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import java.awt.image.BufferedImage;

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
