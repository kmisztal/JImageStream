import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.convolve.BlurFilter;
import pl.edu.uj.JImageStream.filters.convolve.BoxBlurFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlurFilterTest extends AbstractBaseTest {

    @Test
    public void blurFilterTest() {
        BufferedImage bufferedImageBlur = streamableImage.stream()
                .apply(new BlurFilter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImageBlur, "png", new File("target/docs/images/BlurFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

