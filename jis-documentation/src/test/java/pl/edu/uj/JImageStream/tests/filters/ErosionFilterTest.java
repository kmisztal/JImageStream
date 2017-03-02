package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Test;
import pl.edu.uj.JImageStream.collectors.BufferedImageCollector;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.color.OtsuBinarization;
import pl.edu.uj.JImageStream.filters.morphology.ErosionFilter;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ErosionFilterTest extends AbstractBaseTest {

    @Test
    public void erosionFilterGrayscaleImageTest() {

        // tag::erosionFilter[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new OtsuBinarization())
                .apply(new ErosionFilter())
                .collect(new BufferedImageCollector());
        // end::erosionFilter[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/ErosionFilterGrayscaleImage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void erosionFilterTest() {

        // tag::erosionFilterBinary[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new OtsuBinarization())
                .apply(new ErosionFilter())
                .collect(new BufferedImageCollector());
        // end::erosionFilterBinary[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/ErosionFilter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void erosionFilterWithBallKernelTest() {

        // tag::erosionFilterBinaryBall[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new OtsuBinarization())
                .apply(new ErosionFilter(5, ErosionFilter.BALL_KERNEL))
                .collect(new BufferedImageCollector());
        // end::erosionFilterBinaryBall[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/ErosionFilterWithBallKernel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void erosionFilterWithSquareKernelTest() {

        // tag::erosionFilterBinarySquare[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new OtsuBinarization())
                .apply(new ErosionFilter(5, ErosionFilter.SQUARE_KERNEL))
                .collect(new BufferedImageCollector());
        // end::erosionFilterBinarySquare[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/ErosionFilterWithSquareKernel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void erosionFilterWithVerticalKernelTest() {

        // tag::erosionFilterBinaryVertical[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new OtsuBinarization())
                .apply(new ErosionFilter(5, ErosionFilter.VERTICAL_LINE_KERNEL))
                .collect(new BufferedImageCollector());
        // end::erosionFilterBinaryVertical[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/ErosionFilterWithVerticalKernel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void erosionFilterWithHorizontalKernelTest() {

        // tag::erosionFilterBinaryHorizontal[]
        BufferedImage bufferedImage = streamableImage.stream()
                .apply(new GrayScaleFilter())
                .apply(new OtsuBinarization())
                .apply(new ErosionFilter(5, ErosionFilter.HORIZONTAL_LINE_KERNEL))
                .collect(new BufferedImageCollector());
        // end::erosionFilterBinaryHorizontal[]

        try {
            ImageIO.write(bufferedImage, "png", new File("target/docs/images/ErosionFilterWithHorizontalKernel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
