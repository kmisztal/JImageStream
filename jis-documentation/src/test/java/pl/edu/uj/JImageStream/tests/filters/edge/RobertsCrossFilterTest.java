package pl.edu.uj.JImageStream.tests.filters.edge;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.edge.EdgeDetectionFilter;
import pl.edu.uj.JImageStream.filters.edge.roberts.RobertsCrossXFilter;
import pl.edu.uj.JImageStream.filters.edge.roberts.RobertsCrossYFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;


public class RobertsCrossFilterTest extends AbstractBaseTest {

    @Test
    public void robertsCrossFilterTest() {
        // tag::robertsCrossXFilter[]
        BufferedImage bufferedImageX = streamableImage.parallelStream()
                .apply(Filters.grayScaleFilter())
                .apply(Filters.robertsCrossXFilter())
                .collect(Collectors.toBufferedImage());
        // end::robertsCrossXFilter[]

        save(streamableImage, bufferedImageX, "RobertsX.png");

        // tag::robertsCrossYFilter[]
        BufferedImage bufferedImageY = streamableImage.parallelStream()
                .apply(Filters.grayScaleFilter())
                .apply(Filters.robertsCrossYFilter())
                .collect(Collectors.toBufferedImage());
        // end::robertsCrossYFilter[]

        save(streamableImage, bufferedImageY, "RobertsY.png");

        // tag::edgeDetectionRobertsFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .apply(Filters.edgeDetection(bufferedImageX, bufferedImageY))
                .collect(Collectors.toBufferedImage());
        // end::edgeDetectionRobertsFilter[]

        save(streamableImage, bufferedImage, "Roberts.png");

    }

}
