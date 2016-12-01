package pl.edu.uj.JImageStream.tests.basic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.model.StreamableImage;

public class CreateStreamableImageTest {

    @Test
    public void createFromFile() {

        try {
            // tag::createFromImage[]
            BufferedImage bufferedImage = ImageIO.read(new File("path/to/file"));
            StreamableImage streamableImage = new StreamableImage(bufferedImage);
            // end::createFromImage[]
        } catch (IOException e) {

        }

    }

    @Test
    public void createFromBufferedImage() {
        try {
            // tag::createFromFile[]
            StreamableImage streamableImage = new StreamableImage(new File("path/to/file"));
            // end::createFromFile[]
        } catch (IOException e) {
        }
    }

}
