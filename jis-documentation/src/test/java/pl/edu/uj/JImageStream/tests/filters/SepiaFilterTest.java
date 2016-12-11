package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.SepiaFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class SepiaFilterTest extends AbstractBaseTest {
    @Test
    public void sepiaFilterTest() {
        // tag::sepiaFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new SepiaFilter())
                .collect(new BufferedImageCollector());
        // end::sepiaFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/SepiaFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
