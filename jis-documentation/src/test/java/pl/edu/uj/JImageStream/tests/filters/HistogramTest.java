package pl.edu.uj.JImageStream.tests.filters;

import javax.imageio.ImageIO;

import org.junit.Test;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.FileCollector;
import pl.edu.uj.JImageStream.filters.statistical.Histogram;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.StreamableImage;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class HistogramTest extends AbstractBaseTest{
    @Test
    public void histogramFilterTest() {
        BufferedImage hBase = new BufferedImage(512, 180, BufferedImage.TYPE_INT_ARGB);
        BufferedImage lenaImage = streamableImage.stream().collect(new BufferedImageCollector());
        streamableImage = new StreamableImage(hBase);

        // tag::histogramFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new Histogram(lenaImage, ColorChannel.RED, ColorChannel.GREEN, ColorChannel.BLUE))
                .collect(new BufferedImageCollector());
        // end::histogramFilter[]
        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/RGBAhistogram.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
