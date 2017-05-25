package pl.edu.uj.JImageStream.tests.filters.morphology;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import java.awt.image.BufferedImage;

public class ErosionFilterTest extends AbstractBaseTest {

    @Test
    public void erosionFilterTest() {

        streamableImage = streamableImage.stream()
                .apply(Filters.otsuBinarizationFilter())
                .collect(Collectors.toStreamableImage());

        // tag::erosionFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(Filters.erosionFilter())
                .collect(Collectors.toBufferedImage());
        // end::erosionFilter[]

        save(streamableImage, bufferedImage, "erosionFilter.png");
    }
}
