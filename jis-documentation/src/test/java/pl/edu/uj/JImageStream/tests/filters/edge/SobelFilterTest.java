package pl.edu.uj.JImageStream.tests.filters.edge;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.edge.EdgeDetectionFilter;
import pl.edu.uj.JImageStream.filters.edge.sobel.SobelXFilter;
import pl.edu.uj.JImageStream.filters.edge.sobel.SobelYFilter;

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
                .apply(Filters.grayScaleFilter())
                .apply(Filters.sobelXFilter())
                .collect(Collectors.toBufferedImage());
        // end::sobelXFilter[]

        save(streamableImage, bufferedImageX, "SobelX.png");

        // tag::sobelYFilter[]
        BufferedImage bufferedImageY = streamableImage.parallelStream()
                .apply(Filters.grayScaleFilter())
                .apply(Filters.sobelYFilter())
                .collect(Collectors.toBufferedImage());
        // end::sobelYFilter[]

        save(streamableImage, bufferedImageY, "SobelY.png");

        // tag::edgeDetectionSobelFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .apply(Filters.edgeDetection(bufferedImageX, bufferedImageY))
                .collect(Collectors.toBufferedImage());
        // end::edgeDetectionSobelFilter[]

        save(streamableImage, bufferedImage, "Sobel.png");
    }

}
