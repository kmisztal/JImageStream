package pl.edu.uj.JImageStream.tests.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.statistical.MeanFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class MeanFilterTest extends AbstractBaseTest {

    @Test
    public void meanTest() {
        // tag::meanFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .setThreads(50).apply(new MeanFilter(9))
                .collect(new BufferedImageCollector());
        // end::meanFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/MeanFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
