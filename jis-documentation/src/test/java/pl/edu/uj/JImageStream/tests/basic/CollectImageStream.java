package pl.edu.uj.JImageStream.tests.basic;

import org.junit.Test;
import pl.edu.uj.JImageStream.api.core.Collector;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import java.awt.image.BufferedImage;

public class CollectImageStream extends AbstractBaseTest {

    @Test
    public void collectImageStream() {

        // tag::collectImageStream[]
        streamableImage.stream().collect(new Collector<BufferedImage>() {
            @Override
            public BufferedImage collect(BufferedImage bufferedImage) {
                return bufferedImage;
            }
        });
        // end::collectImageStream[]
    }

}
