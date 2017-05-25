package pl.edu.uj.JImageStream.tests.filters.morphology;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.Filters;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.binarization.OtsuBinarization;
import pl.edu.uj.JImageStream.filters.morphology.DilationFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;
import pl.edu.uj.JImageStream.tests.basic.CollectImageStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DilationFilterTest extends AbstractBaseTest {

    @Test
    public void dilatationFilterTest() {

        streamableImage = streamableImage.stream()
                .apply(Filters.otsuBinarizationFilter())
                .collect(Collectors.toStreamableImage());

        // tag::dilationFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(Filters.dilatationFilter())
                .collect(Collectors.toBufferedImage());
        // end::dilationFilter[]

        save(streamableImage, bufferedImage, "dilatationFilter.png");
    }

}
