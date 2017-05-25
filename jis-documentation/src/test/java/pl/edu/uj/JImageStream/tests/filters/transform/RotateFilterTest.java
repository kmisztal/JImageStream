package pl.edu.uj.JImageStream.tests.filters.transform;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.transform.RotateFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RotateFilterTest extends AbstractBaseTest {

    @Test
    public void RotateFilterTest() {
        // tag::RotateFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(Filters.rotateFilter(45))
                .collect(Collectors.toBufferedImage());
        // end::RotateFilter[]

        save(streamableImage, bufferedImage, "RotateFilter.png");
    }
}
