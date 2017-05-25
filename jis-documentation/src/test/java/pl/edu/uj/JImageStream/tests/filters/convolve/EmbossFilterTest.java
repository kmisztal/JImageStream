package pl.edu.uj.JImageStream.tests.filters.convolve;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.convolve.EmbossFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

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
