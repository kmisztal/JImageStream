package pl.edu.uj.JImageStream.tests.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.filters.OtsuBinarization;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class OtsuBinarizationTest extends AbstractBaseTest {

    @Test
    public void otsuTest() {
        // tag::otsuFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new OtsuBinarization())
                .collect(new BufferedImageCollector());
        // end::otsuFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/OtsuFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}