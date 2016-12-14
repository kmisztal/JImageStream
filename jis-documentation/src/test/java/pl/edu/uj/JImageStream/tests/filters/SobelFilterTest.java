package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.edge.EdgeDetectionFilter;
import pl.edu.uj.JImageStream.filters.edge.SobelXFilter;
import pl.edu.uj.JImageStream.filters.edge.SobelYFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class SobelFilterTest extends AbstractBaseTest {

    @Test
    public void sobelFilterTest() {
        // tag::sobelXFilter[]
        BufferedImage bufferedImageX = streamableImage.parallelStream()
                .apply(new GrayScaleFilter())
                .apply(new SobelXFilter())
                .collect(new BufferedImageCollector());
        // end::sobelXFilter[]

        try {
            ImageIO.write(bufferedImageX, "png", new File("target/docs/images/SobelX.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // tag::sobelYFilter[]
        BufferedImage bufferedImageY = streamableImage.parallelStream()
                .apply(new GrayScaleFilter())
                .apply(new SobelYFilter())
                .collect(new BufferedImageCollector());
        // end::sobelYFilter[]

        try {
            ImageIO.write(bufferedImageY, "png", new File("target/docs/images/SobelY.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // tag::edgeDetectionSobelFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .apply(new EdgeDetectionFilter(bufferedImageX, bufferedImageY))
                .collect(new BufferedImageCollector());
        // end::edgeDetectionSobelFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/Sobel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
