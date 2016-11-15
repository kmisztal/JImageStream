import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.noise.SaltAndPepperFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaltAndPepperFilterTest extends AbstractBaseTest {

    @Test
    public void saltAndPepperFilterTest() {
        // tag::saltAndPepperFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new SaltAndPepperFilter(0.1))
                .collect(new BufferedImageCollector());
        // end::saltAndPepperFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/SaltAndPepperFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

