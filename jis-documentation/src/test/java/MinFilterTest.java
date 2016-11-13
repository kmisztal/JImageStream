import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.MaxFilter;
import pl.edu.uj.JImageStream.filters.MinFilter;

/**
 * Created by kuba on 2016-11-13.
 */
public class MinFilterTest extends AbstractBaseTest {

    @Test
    public void minTest() {
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .setThreads(50).apply(new MinFilter(9))
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/MinFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
