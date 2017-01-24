package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.RotateFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RotateFilterTest extends AbstractBaseTest {

    @Test
    public void RotateFilterTest() {
        // tag::rotateFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new RotateFilter(45))
                .collect(new BufferedImageCollector());
        // end::rotateFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/RotateFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
