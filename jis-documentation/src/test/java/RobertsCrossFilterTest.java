import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.EdgeDetectionFilter;
import pl.edu.uj.JImageStream.filters.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.MedianFilter;
import pl.edu.uj.JImageStream.filters.RobertsCrossXFilter;
import pl.edu.uj.JImageStream.filters.RobertsCrossYFilter;

/**
 * Created by kuba on 2016-11-13.
 */
public class RobertsCrossFilterTest extends AbstractBaseTest {

    @Test
    public void medianTest() {
        BufferedImage bufferedImageX = streamableImage.parallelStream()
                .apply(new GrayScaleFilter())
                .apply(new RobertsCrossXFilter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImageX, "png", new File("target/docs/images/RobertsX.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage bufferedImageY = streamableImage.parallelStream()
                .apply(new GrayScaleFilter())
                .apply(new RobertsCrossYFilter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImageY, "png", new File("target/docs/images/RobertsY.png"));
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
