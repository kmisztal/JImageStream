package pl.edu.uj.JImageStream.tests.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class BlueFilterTest extends AbstractBaseTest {
    @Test
    public void blueFilterTest() {
        // tag::blueFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new BlueFilter())
                .collect(new BufferedImageCollector());
        // end::blueFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/BlueFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
