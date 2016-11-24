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


public class RobertsCrossFilterTest extends AbstractBaseTest {

    @Test
    public void medianTest() {
        BufferedImage bufferedImageX = streamableImage.parallelStream()
                .apply(new GrayScaleFilter())
                .apply(new RobertsCrossXFilter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImageX, "png", new File("target/docs/images/RobertsX.png"));
            savingLogMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage bufferedImageY = streamableImage.parallelStream()
                .apply(new GrayScaleFilter())
                .apply(new RobertsCrossYFilter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImageY, "png", new File("target/docs/images/RobertsY.png"));
            savingLogMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage bufferedImage = streamableImage.parallelStream()
//                .apply(new GrayScaleFilter())
                .apply(new EdgeDetectionFilter(bufferedImageX, bufferedImageY))
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/Roberts.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
