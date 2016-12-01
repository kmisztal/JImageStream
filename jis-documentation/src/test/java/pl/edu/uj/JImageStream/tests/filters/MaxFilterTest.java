package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.statistical.MaxFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class MaxFilterTest extends AbstractBaseTest {

    @Test
    public void maxTest() {
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .setThreads(50).apply(new MaxFilter(9))
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/MaxFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
