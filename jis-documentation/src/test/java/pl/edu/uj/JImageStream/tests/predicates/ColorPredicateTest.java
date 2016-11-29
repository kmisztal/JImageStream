package pl.edu.uj.JImageStream.tests.predicates;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.edge.RobertsCrossXFilter;
import pl.edu.uj.JImageStream.filters.noise.SaltAndPepperFilter;
import pl.edu.uj.JImageStream.predicates.ColorPredicate;
import pl.edu.uj.JImageStream.predicates.ThresholdPredicate;
import pl.edu.uj.JImageStream.tests.filters.AbstractBaseTest;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by pPanek on 2016-11-29.
 */
public class ColorPredicateTest extends AbstractBaseTest{

    @Test
    public void colorPredicateTest() {
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .setThreads(50)
                .bounds(new ThresholdPredicate(155))
                .apply(new RobertsCrossXFilter())
                .bounds(new ColorPredicate(Color.black))
                .apply(new SaltAndPepperFilter())
                .collect(new BufferedImageCollector());


        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/ColorPredicate.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
