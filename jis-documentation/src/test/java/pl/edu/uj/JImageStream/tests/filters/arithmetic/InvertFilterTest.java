package pl.edu.uj.JImageStream.tests.filters.arithmetic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.arithmetic.InvertFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class InvertFilterTest extends AbstractBaseTest {
    @Test
    public void invertFilterTest() {

        // tag::invertFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(Filters.invertFilter())
                .collect(Collectors.toBufferedImage());
        // end::invertFilter[]

        save(streamableImage, bufferedImage, "InvertFilter.png");
    }

}
