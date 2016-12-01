package pl.edu.uj.JImageStream.tests.predicates;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.RedFilter;
import pl.edu.uj.JImageStream.predicates.ThresholdPredicate;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class ThresholdPredicateTest extends AbstractBaseTest {

    @Test
    public void thresholdTest() {
        BufferedImage bufferedImage = streamableImage.stream()
                .bounds(new ThresholdPredicate(140))
                .apply(new RedFilter())
                .collect(new BufferedImageCollector());


        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/ThresholdPredicate.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
