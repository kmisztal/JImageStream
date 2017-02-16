package pl.edu.uj.JImageStream.tests.filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.morphology.DilationFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class DilationFilterTest extends AbstractBaseTest {
    @Test
    public void dilationFilterTest() {

        // tag::dilationFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new DilationFilter(7))
                .collect(new BufferedImageCollector());
        // end::dilationFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/DilationFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
