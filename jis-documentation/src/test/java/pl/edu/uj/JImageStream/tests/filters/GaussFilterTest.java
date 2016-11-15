package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.noise.GaussFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GaussFilterTest extends AbstractBaseTest {

    @Test
    public void gaussFilterTest() {

        BufferedImage bufferedImageGauss5 = streamableImage.stream()
                .apply(new GaussFilter(5, 1.0))
                .collect(new BufferedImageCollector());
        BufferedImage bufferedImageGauss15 = streamableImage.stream()
                .apply(new GaussFilter(7, 4.0))
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImageGauss5, "png", new File("target/docs/images/GaussFilter5.png"));
            ImageIO.write(bufferedImageGauss15, "png", new File("target/docs/images/GaussFilter7.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

