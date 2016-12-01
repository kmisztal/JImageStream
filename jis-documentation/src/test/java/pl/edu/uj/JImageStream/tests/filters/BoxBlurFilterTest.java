package pl.edu.uj.JImageStream.tests.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.convolve.BoxBlurFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class BoxBlurFilterTest extends AbstractBaseTest {

    @Test
    public void boxBlurFilterTest() {

        BufferedImage bufferedImageBlur5 = streamableImage.stream()
                .apply(new BoxBlurFilter(5))
                .collect(new BufferedImageCollector());
        BufferedImage bufferedImageBlur7 = streamableImage.stream()
                .apply(new BoxBlurFilter(7))
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImageBlur5, "png", new File("target/docs/images/BoxBlurFilter5.png"));
            ImageIO.write(bufferedImageBlur7, "png", new File("target/docs/images/BoxBlurFilter7.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

