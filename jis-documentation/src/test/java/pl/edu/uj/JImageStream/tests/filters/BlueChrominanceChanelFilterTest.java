package pl.edu.uj.JImageStream.tests.filters;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.YCbCr.BlueChrominanceChanelFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlueChrominanceChanelFilterTest extends AbstractBaseTest {
    @Test
    public void BlueChrominanceChanelFilterTest() {

        // tag::BlueChrominanceChanelFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new BlueChrominanceChanelFilter())
                .collect(new BufferedImageCollector());
        // end::BlueChrominanceChanelFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/BlueChrominanceChanelFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
