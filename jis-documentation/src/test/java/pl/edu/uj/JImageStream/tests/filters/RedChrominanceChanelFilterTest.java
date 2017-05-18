package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RedChrominanceChanelFilterTest extends AbstractBaseTest {
    @Test
    public void RedChrominanceChanelFilterTest() {

        // tag::RedChrominanceChanelFilterTest[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new RedChrominanceChanelFilter())
                .collect(new BufferedImageCollector());
        // end::RedChrominanceChanelFilterTest[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/RedChrominanceChanelFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
