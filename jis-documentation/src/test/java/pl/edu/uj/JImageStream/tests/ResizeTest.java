package pl.edu.uj.JImageStream.tests;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.RotateFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizeTest extends AbstractBaseTest {

    @Test
    public void ResizeTest() {
        // tag::resize[]
        BufferedImage bufferedImage = streamableImage.stream()
                .resize(600,600)
                .collect(new BufferedImageCollector());
        // end::resize[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/Resize.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
