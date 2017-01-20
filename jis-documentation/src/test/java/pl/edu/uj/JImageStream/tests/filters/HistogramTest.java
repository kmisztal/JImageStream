package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import java.awt.image.BufferedImage;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.FileCollector;
import pl.edu.uj.JImageStream.filters.Histogram;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.StreamableImage;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class HistogramTest extends AbstractBaseTest{
    @Test
    public void histogramFilterTest() {
        BufferedImage hBase = new BufferedImage(512, 180, BufferedImage.TYPE_INT_ARGB);
        BufferedImage lenaImage = streamableImage.stream().collect(new BufferedImageCollector());
        
        // tag::histogramFilter[]
        new StreamableImage(hBase).stream()
                .apply(new Histogram(lenaImage, ColorChannel.RED, ColorChannel.GREEN, ColorChannel.BLUE))
                .collect(new FileCollector("png", "target/docs/images/RGBAhistogram.png"));
        // end::histogramFilter[]
    }
}
