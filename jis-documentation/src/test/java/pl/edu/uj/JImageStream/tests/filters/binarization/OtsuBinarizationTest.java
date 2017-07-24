package pl.edu.uj.JImageStream.tests.filters.binarization;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import java.awt.image.BufferedImage;

public class OtsuBinarizationTest extends AbstractBaseTest {

    @Test
    public void otsuTest() {

        // tag::otsuFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .apply(Filters.otsuBinarizationFilter())
                .collect(Collectors.toBufferedImage());
        // end::otsuFilter[]

        save(streamableImage, bufferedImage, "OtsuFilter.png");
    }
}