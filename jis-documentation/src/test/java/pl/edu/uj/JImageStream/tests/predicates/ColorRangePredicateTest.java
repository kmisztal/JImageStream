package pl.edu.uj.JImageStream.tests.predicates;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.predicates.ColorRangePredicate;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class ColorRangePredicateTest extends AbstractBaseTest {

    @Test
    public void rangePredicateTest() {
        // tag::ColorRangePredicate[]
        BufferedImage bufferedImage = streamableImage.stream()
                .bounds(new ColorRangePredicate(10, 200, ColorChannel.RED))
                .apply(new RedFilter())
                .collect(new BufferedImageCollector());
        // end::ColorRangePredicate[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/ColorRangePredicate.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
