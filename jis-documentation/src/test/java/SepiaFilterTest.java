import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.SepiaFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SepiaFilterTest extends AbstractBaseTest {
    @Test
    public void sepiaFilterTest(){

        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new SepiaFilter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/SepiaFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}