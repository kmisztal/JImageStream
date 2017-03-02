package pl.edu.uj.JImageStream.tests.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class GrayScaleFilterTest extends AbstractBaseTest {
    @Test
    public void grayScaleFilterTest() {

        // tag::grayscaleFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .resize(2000, 1000)
                .apply(new GrayScaleFilter())
                .collect(new BufferedImageCollector());
        // end::grayscaleFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/GrayScaleFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
