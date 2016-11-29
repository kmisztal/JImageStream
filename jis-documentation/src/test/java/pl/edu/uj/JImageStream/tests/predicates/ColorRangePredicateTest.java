package pl.edu.uj.JImageStream.tests.predicates;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.RedFilter;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.predicates.ColorRangePredicate;
import pl.edu.uj.JImageStream.tests.filters.AbstractBaseTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by pPanek on 2016-11-29.
 */
public class ColorRangePredicateTest extends AbstractBaseTest {

    @Test
    public void rangePredicateTest() {
        BufferedImage bufferedImage = streamableImage.parallelStream()
                .setThreads(50)
                .bounds(new ColorRangePredicate(10, 200, ColorChannel.RED))
                .apply(new RedFilter())
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/ColorRangePredicate.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
