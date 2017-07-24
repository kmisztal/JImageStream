package pl.edu.uj.JImageStream.tests.filters.convolve;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import java.awt.image.BufferedImage;

public class EmbossFilterTest extends AbstractBaseTest {
    @Test
    public void embossFilterTest() {

        // tag::embossFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(Filters.embossFilter())
                .collect(Collectors.toBufferedImage());
        // end::embossFilter[]

        save(streamableImage, bufferedImage, "EmbossFilter.png");

    }
}
