package pl.edu.uj.JImageStream.tests.predicates;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.RedFilter;
import pl.edu.uj.JImageStream.filters.edge.RobertsCrossXFilter;
import pl.edu.uj.JImageStream.tests.filters.AbstractBaseTest;
import pl.edu.uj.JImageStream.predicates.ThresholdPredicate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by pPanek on 2016-11-29.
 */
public class ThresholdPredicateTest extends AbstractBaseTest{

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
