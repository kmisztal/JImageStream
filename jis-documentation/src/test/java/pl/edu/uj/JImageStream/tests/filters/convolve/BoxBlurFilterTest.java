package pl.edu.uj.JImageStream.tests.filters.convolve;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import java.awt.image.BufferedImage;

public class BoxBlurFilterTest extends AbstractBaseTest {

    @Test
    public void boxBlurFilterTest() {

        // tag::boxBlurFilter5[]
        BufferedImage bufferedImageBlur5 = streamableImage.stream()
                .apply(Filters.boxBlurFilter(5))
                .collect(Collectors.toBufferedImage());
        // end::boxBlurFilter5[]

        save(streamableImage, bufferedImageBlur5, "BoxBlurFilter5.png");
    }
}

