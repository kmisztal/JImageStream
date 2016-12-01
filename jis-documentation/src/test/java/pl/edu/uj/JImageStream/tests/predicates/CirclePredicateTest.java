package pl.edu.uj.JImageStream.tests.predicates;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.RedFilter;
import pl.edu.uj.JImageStream.predicates.CirclePredicate;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class CirclePredicateTest extends AbstractBaseTest {


    @Test
    public void circlePredicateTest() {
        BufferedImage bufferedImage = streamableImage.stream()
                .bounds(new CirclePredicate(220, 145, 100))
                .apply(new RedFilter())
                .collect(new BufferedImageCollector());


        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/CirclePredicate.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
