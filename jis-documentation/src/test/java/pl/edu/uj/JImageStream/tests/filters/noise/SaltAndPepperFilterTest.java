package pl.edu.uj.JImageStream.tests.filters.noise;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import java.awt.image.BufferedImage;

public class SaltAndPepperFilterTest extends AbstractBaseTest {

    @Test
    public void saltAndPepperFilterTest() {
        // tag::saltAndPepperFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(Filters.saltAndPepperFilter())
                .collect(Collectors.toBufferedImage());
        // end::saltAndPepperFilter[]

        save(streamableImage, bufferedImage, "saltAndPepperFilter.png");

    }
}

