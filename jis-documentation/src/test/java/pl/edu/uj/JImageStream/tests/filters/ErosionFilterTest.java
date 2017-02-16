package pl.edu.uj.JImageStream.tests.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.morphology.ErosionFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class ErosionFilterTest extends AbstractBaseTest {
    @Test
    public void erosionFilterTest() {

        // tag::erosionFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new ErosionFilter())
                .collect(new BufferedImageCollector());
        // end::erosionFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/ErosionFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
