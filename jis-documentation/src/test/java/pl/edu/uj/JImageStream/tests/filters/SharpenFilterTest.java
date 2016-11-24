package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.convolve.SharpenFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SharpenFilterTest extends AbstractBaseTest {
    @Test
    public void sharpenFilterTest() {

        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new SharpenFilter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/SharpenFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
