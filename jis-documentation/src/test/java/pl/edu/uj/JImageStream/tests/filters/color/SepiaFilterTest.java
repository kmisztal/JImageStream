package pl.edu.uj.JImageStream.tests.filters.color;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.color.SepiaFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class SepiaFilterTest extends AbstractBaseTest {
    @Test
    public void sepiaFilterTest() {
        // tag::sepiaFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(Filters.sepiaFilter())
                .collect(Collectors.toBufferedImage());
        // end::sepiaFilter[]

        save(streamableImage, bufferedImage, "SepiaFilter.png");
    }

}
