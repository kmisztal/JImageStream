package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.YCbCr.LuminanceChanelFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LuminanceChanelFilterTest extends AbstractBaseTest {
    @Test
    public void LuminanceChanelFilterTest() {

        // tag::LuminanceChanelFilterTest[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new LuminanceChanelFilter())
                .collect(new BufferedImageCollector());
        // end::LuminanceChanelFilterTest[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/LuminanceChanelFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
