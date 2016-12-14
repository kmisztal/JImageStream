package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.edge.EdgeDetectionFilter;
import pl.edu.uj.JImageStream.filters.edge.RobertsCrossXFilter;
import pl.edu.uj.JImageStream.filters.edge.RobertsCrossYFilter;

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
                .apply(new GrayScaleFilter())
                .apply(new RobertsCrossXFilter())
                .collect(new BufferedImageCollector());
        // end::robertsCrossXFilter[]

        try {
            ImageIO.write(bufferedImageX, "png", new File("target/docs/images/RobertsX.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // tag::robertsCrossYFilter[]
        BufferedImage bufferedImageY = streamableImage.parallelStream()
                .apply(new GrayScaleFilter())
                .apply(new RobertsCrossYFilter())
                .collect(new BufferedImageCollector());
        // end::robertsCrossYFilter[]

        try {
            ImageIO.write(bufferedImageY, "png", new File("target/docs/images/RobertsY.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // tag::edgeDetectionRobertsFilter[]
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .apply(new EdgeDetectionFilter(bufferedImageX, bufferedImageY))
                .collect(new BufferedImageCollector());
        // end::edgeDetectionRobertsFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/Roberts.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
