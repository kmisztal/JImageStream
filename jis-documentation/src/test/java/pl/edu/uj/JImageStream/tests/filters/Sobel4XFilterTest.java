package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.edge.EdgeDetection4XFilter;
import pl.edu.uj.JImageStream.filters.edge.sobel.SobelXFilter;
import pl.edu.uj.JImageStream.filters.edge.sobel.SobelYFilter;
import pl.edu.uj.JImageStream.filters.edge.sobel.Sobel45Filter;
import pl.edu.uj.JImageStream.filters.edge.sobel.Sobel135Filter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class Sobel4XFilterTest extends AbstractBaseTest {

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
        // tag::sobel45Filter[]
        BufferedImage bufferedImage45 = streamableImage.parallelStream()
                .apply(new GrayScaleFilter())
                .apply(new Sobel45Filter())
                .collect(new BufferedImageCollector());
        // end::sobel45Filter[]
        try {
            ImageIO.write(bufferedImage45, "png", new File("target/docs/images/Sobel45.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // tag::sobel135Filter[]
        BufferedImage bufferedImage135 = streamableImage.parallelStream()
                .apply(new GrayScaleFilter())
                .apply(new Sobel135Filter())
                .collect(new BufferedImageCollector());
        // end::sobel135Filter[]
        try {
            ImageIO.write(bufferedImage135, "png", new File("target/docs/images/Sobel135.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // tag::edgeDetectionSobel4XFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .apply(new EdgeDetection4XFilter(bufferedImageX, bufferedImageY,bufferedImage45,bufferedImage135))
                .collect(new BufferedImageCollector());
        // end::edgeDetectionSobel4XFilter[]
        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/Sobel4X.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
