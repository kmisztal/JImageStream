package pl.edu.uj.JImageStream.tests.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.arithmetic.InvertFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class InvertFilterTest extends AbstractBaseTest {
    @Test
    public void invertFilterTest() {

        // tag::invertFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new InvertFilter())
                .collect(new BufferedImageCollector());
        // end::invertFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/InvertFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
