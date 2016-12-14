package pl.edu.uj.JImageStream.tests.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.GreenFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class GreenFilterTest extends AbstractBaseTest {
    @Test
    public void greenFilterTest() {

        // tag::greenFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GreenFilter())
                .collect(new BufferedImageCollector());
        // end::greenFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/GreenFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
