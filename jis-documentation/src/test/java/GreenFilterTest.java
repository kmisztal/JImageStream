import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.GreenFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GreenFilterTest extends AbstractBaseTest {
    @Test
    public void greenFilterTest(){

        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GreenFilter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/GreenFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
