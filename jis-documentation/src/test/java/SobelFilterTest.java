import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.EdgeDetectionFilter;
import pl.edu.uj.JImageStream.filters.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.MeanFilter;
import pl.edu.uj.JImageStream.filters.SobelXFilter;
import pl.edu.uj.JImageStream.filters.SobelYFilter;

/**
 * Created by kuba on 2016-11-13.
 */
public class SobelFilterTest extends AbstractBaseTest {

    @Test
    public void medianTest() {
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

        BufferedImage bufferedImage = streamableImage.parallelStream()
//                .apply(new GrayScaleFilter())
                .apply(new EdgeDetectionFilter(bufferedImageX, bufferedImageY))
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/Sobel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
