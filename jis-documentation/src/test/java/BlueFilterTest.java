import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.BlueFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlueFilterTest extends AbstractBaseTest {
    @Test
    public void blueFilterTest() {
        // tag::blueFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new BlueFilter())
                .collect(new BufferedImageCollector());
        // end::blueFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/BlueFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
