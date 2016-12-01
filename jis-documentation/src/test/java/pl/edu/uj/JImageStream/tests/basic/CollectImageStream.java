package pl.edu.uj.JImageStream.tests.basic;

import java.awt.image.BufferedImage;
import org.junit.Test;
import pl.edu.uj.JImageStream.api.core.Collector;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

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
