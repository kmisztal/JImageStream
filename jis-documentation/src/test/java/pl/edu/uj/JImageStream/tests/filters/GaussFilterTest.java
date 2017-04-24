package pl.edu.uj.JImageStream.tests.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.noise.GaussFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class GaussFilterTest extends AbstractBaseTest {

    @Test
    public void gaussFilterTest() {

        // tag::gaussFilter51[]
        BufferedImage bufferedImageGauss5 = streamableImage.stream()
                .apply(new GaussFilter(5, 1.0))
                .collect(new BufferedImageCollector());
        // end::gaussFilter51[]

        // tag::gaussFilter74[]
        BufferedImage bufferedImageGauss15 = streamableImage.stream()
                .apply(new GaussFilter(11, 4.0))
                .collect(new BufferedImageCollector());
        // end::gaussFilter74[]

        try {
            ImageIO.write(bufferedImageGauss5, "png", new File("target/docs/images/GaussFilter5.png"));
            ImageIO.write(bufferedImageGauss15, "png", new File("target/docs/images/GaussFilter7.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

