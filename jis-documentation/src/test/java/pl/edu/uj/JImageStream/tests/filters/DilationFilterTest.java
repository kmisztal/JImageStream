package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.color.OtsuBinarization;
import pl.edu.uj.JImageStream.filters.morphology.DilationFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DilationFilterTest extends AbstractBaseTest {

    @Test
    public void dilationFilterGaryscaleImageTest() {

        // tag::dilationFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new DilationFilter())
                .collect(new BufferedImageCollector());
        // end::dilationFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/DilationFilterGraysclaeImage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dilationFilterTest() {

        // tag::dilationFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new OtsuBinarization())
                .apply(new DilationFilter())
                .collect(new BufferedImageCollector());
        // end::dilationFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/DilationFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dilationFilterWithBallKernelTest() {

        // tag::dilationFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new OtsuBinarization())
                .apply(new DilationFilter(5, DilationFilter.BALL_KERNEL))
                .collect(new BufferedImageCollector());
        // end::dilationFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/DilationFilterWithBallKernel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dilationFilterWithSquareKernelTest() {

        // tag::dilationFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new OtsuBinarization())
                .apply(new DilationFilter(5, DilationFilter.SQUARE_KERNEL))
                .collect(new BufferedImageCollector());
        // end::dilationFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/DilationFilterWithSquareKernel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dilationFilterWithVerticalKernelTest() {

        // tag::dilationFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new OtsuBinarization())
                .apply(new DilationFilter(5, DilationFilter.VERTICAL_LINE_KERNEL))
                .collect(new BufferedImageCollector());
        // end::dilationFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/DilationFilterWithVerticalKernel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dilationFilterWithHorizontalKernelTest() {

        // tag::dilationFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new OtsuBinarization())
                .apply(new DilationFilter(5, DilationFilter.HORIZONTAL_LINE_KERNEL))
                .collect(new BufferedImageCollector());
        // end::dilationFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/DilationFilterWithHorizontalKernel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
