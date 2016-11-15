import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.statistical.MaxFilter;

public class MaxFilterTest extends AbstractBaseTest {

    @Test
    public void maxTest() {
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .setThreads(50).apply(new MaxFilter(9))
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/MaxFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
