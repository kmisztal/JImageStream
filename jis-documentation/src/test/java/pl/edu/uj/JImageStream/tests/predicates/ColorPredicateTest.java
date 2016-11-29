package pl.edu.uj.JImageStream.tests.predicates;

import org.junit.Test;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.RedFilter;
import pl.edu.uj.JImageStream.model.Pixel;
import pl.edu.uj.JImageStream.predicates.ColorPredicate;
import pl.edu.uj.JImageStream.tests.filters.AbstractBaseTest;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ColorPredicateTest extends AbstractBaseTest{

    private Filter fullRedFilter = new Filter() {
        @Override
        public void apply(int x, int y) {
            setPixel(x, y, new Pixel(255,0,0,255));
        }
    };

    @Test
    public void colorPredicateTest() {

        BufferedImage bufferedImage = streamableImage.stream()
                .bounds(p -> p.x < 200)
                .apply(fullRedFilter)
                .collect(new BufferedImageCollector());

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/HalfRedLena.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        bufferedImage = streamableImage.stream()
                .bounds(p -> p.x < 200)
                .apply(fullRedFilter)
                .bounds(new ColorPredicate(Color.red))
                .apply(new RedFilter())
                .collect(new BufferedImageCollector());


        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/ColorPredicate.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
