package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.edge.EdgeDetection4XFilter;
import pl.edu.uj.JImageStream.filters.edge.EdgeDetectionFilter;
import pl.edu.uj.JImageStream.filters.edge.SobelXFilter;
import pl.edu.uj.JImageStream.filters.edge.SobelYFilter;
import pl.edu.uj.JImageStream.filters.edge.Sobel45Filter;
import pl.edu.uj.JImageStream.filters.edge.Sobel135Filter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class Sobel4XFilterTest extends AbstractBaseTest {

    @Test
    public void sobelFilterTest() {
        BufferedImage bufferedImageX = streamableImage.parallelStream()
                .apply(new GrayScaleFilter())
                .apply(new SobelXFilter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImageX, "png", new File("target/docs/images/SobelX.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage bufferedImageY = streamableImage.parallelStream()
                .apply(new GrayScaleFilter())
                .apply(new SobelYFilter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImageY, "png", new File("target/docs/images/SobelY.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage bufferedImage45 = streamableImage.parallelStream()
                .apply(new GrayScaleFilter())
                .apply(new Sobel45Filter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage45, "png", new File("target/docs/images/Sobel45.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage bufferedImage135 = streamableImage.parallelStream()
                .apply(new GrayScaleFilter())
                .apply(new Sobel135Filter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage135, "png", new File("target/docs/images/Sobel135.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage bufferedImage = streamableImage.parallelStream()
                .apply(new EdgeDetection4XFilter(bufferedImageX, bufferedImageY,bufferedImage45,bufferedImage135))
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/Sobel4X.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
