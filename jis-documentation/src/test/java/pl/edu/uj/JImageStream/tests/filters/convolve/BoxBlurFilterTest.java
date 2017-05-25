package pl.edu.uj.JImageStream.tests.filters.convolve;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.convolve.BoxBlurFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

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

