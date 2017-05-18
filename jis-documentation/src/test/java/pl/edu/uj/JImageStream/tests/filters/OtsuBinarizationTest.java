package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.binarization.OtsuBinarization;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OtsuBinarizationTest extends AbstractBaseTest {

    @Test
    public void otsuTest() {
        // tag::otsuFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
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