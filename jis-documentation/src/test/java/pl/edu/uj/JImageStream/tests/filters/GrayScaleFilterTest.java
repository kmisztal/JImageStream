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
    public void greyScaleFilterTest() {

        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/GrayScaleFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
