package pl.edu.uj.JImageStream.tests.filters.convolve;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import java.awt.image.BufferedImage;

public class SharpenFilterTest extends AbstractBaseTest {
    @Test
    public void sharpenFilterTest() {
        // tag::sharpenFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(Filters.sharpenFilter())
                .collect(Collectors.toBufferedImage());
        // end::sharpenFilter[]

        save(streamableImage, bufferedImage, "SharpenFilter.png");

    }

}
