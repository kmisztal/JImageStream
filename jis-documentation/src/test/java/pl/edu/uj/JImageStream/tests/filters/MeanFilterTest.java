package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.statistical.MeanFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MeanFilterTest extends AbstractBaseTest {

    @Test
    public void meanTest() {
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .setThreads(100).apply(new MeanFilter(15))
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/MeanFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
