package pl.edu.uj.JImageStream.tests.filters.noise;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.noise.SaltAndPepperFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class SaltAndPepperFilterTest extends AbstractBaseTest {

    @Test
    public void saltAndPepperFilterTest() {
        // tag::saltAndPepperFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(Filters.saltAndPepperFilter())
                .collect(Collectors.toBufferedImage());
        // end::saltAndPepperFilter[]

        save(streamableImage, bufferedImage, "SaltAndPepperFilter.png");

    }
}

