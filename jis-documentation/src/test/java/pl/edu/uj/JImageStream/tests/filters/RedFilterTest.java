package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class RedFilterTest extends AbstractBaseTest {
    @Test
    public void redFilterTest() {

        // tag::redFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new RedFilter())
                .collect(new BufferedImageCollector());
        // end::redFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/RedFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
