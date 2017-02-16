package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.FileCollector;
import pl.edu.uj.JImageStream.filters.ConnectedComponentFilter;
import pl.edu.uj.JImageStream.model.StreamableImage;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConnectedComponentFilterTest extends AbstractBaseTest {
    @Test
    public void ConnectedComponentTest() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File("");
        try {
            file = new File(classLoader.getResource("logo.png").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }

        streamableImage = new StreamableImage(file);
        streamableImage.stream().collect(new FileCollector("png", "target/docs/images/logo.png"));

        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new ConnectedComponentFilter(1))
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/ConnectedComponentFilter1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}