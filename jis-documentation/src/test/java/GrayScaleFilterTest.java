import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GrayScaleFilterTest extends AbstractBaseTest {
    @Test
    public void greyScaleFilterTest(){

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
