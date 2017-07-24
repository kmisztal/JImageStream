package pl.edu.uj.JImageStream.tests.filters.morphology;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import java.awt.image.BufferedImage;

public class DilationFilterTest extends AbstractBaseTest {

    @Test
    public void dilatationFilterTest() {

        streamableImage = streamableImage.stream()
                .apply(Filters.otsuBinarizationFilter())
                .collect(Collectors.toStreamableImage());

        // tag::dilationFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(Filters.dilatationFilter())
                .collect(Collectors.toBufferedImage());
        // end::dilationFilter[]

        save(streamableImage, bufferedImage, "dilatationFilter.png");
    }

}
