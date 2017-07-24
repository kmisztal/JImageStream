package pl.edu.uj.JImageStream.tests.filters.arithmetic;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import java.awt.image.BufferedImage;

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
