//package pl.edu.uj.JImageStream.api.transforms;
//
//import java.awt.image.BufferedImage;
//import java.awt.image.WritableRaster;
//import java.util.function.Predicate;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//import pl.edu.uj.JImageStream.api.core.Filter;
//import pl.edu.uj.JImageStream.model.ColorChannel;
//import pl.edu.uj.JImageStream.model.Pixel;
//import static org.mockito.AdditionalMatchers.geq;
//import static org.mockito.AdditionalMatchers.lt;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.anyInt;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static pl.edu.uj.JImageStream.model.ColorChannel.ALPHA;
//import static pl.edu.uj.JImageStream.model.ColorChannel.BLUE;
//import static pl.edu.uj.JImageStream.model.ColorChannel.GREEN;
//import static pl.edu.uj.JImageStream.model.ColorChannel.RED;
//
//public class BoundedImageTransformTest {
//
//    private BufferedImage bufferedImageMock;
//    private Filter filterMock;
//    private WritableRaster writableRasterMock;
//
//    private Predicate<Pixel> predicateTest;
//    private ColorChannel[] colorChannelsTest;
//    private BoundedImageTransform boundedImageTransformTest;
//
//    @Before
//    public void setUp() {
//        bufferedImageMock = Mockito.mock(BufferedImage.class);
//        filterMock = Mockito.mock(Filter.class);
//        writableRasterMock = Mockito.mock(WritableRaster.class);
//
//        colorChannelsTest = new ColorChannel[]{RED, GREEN, BLUE, ALPHA};
//        predicateTest = pixel -> true;
//
//        given(bufferedImageMock.getHeight()).willReturn(1);
//        given(bufferedImageMock.getWidth()).willReturn(1);
//        given(bufferedImageMock.getRaster()).willReturn(writableRasterMock);
//        given(writableRasterMock.getPixel(0, 0, (int[]) null)).willReturn(new int[]{0, 0, 0, 0});
//
//        boundedImageTransformTest = new BoundedImageTransform(
//                bufferedImageMock,
//                filterMock,
//                colorChannelsTest,
//                predicateTest
//        );
//
//
//    }
//
//    @Test
//    public void constructorShouldSetFields() {
//        //given
//
//        //when
//        boundedImageTransformTest.apply();
//
//        //then
//        verify(filterMock, times(1)).setSource(bufferedImageMock);
//        verify(filterMock, times(1)).setRestrictions(colorChannelsTest);
//        verify(filterMock, times(1)).setUp();
//        verify(filterMock, times(1)).saveToImage(bufferedImageMock);
//        verify(filterMock, times(1)).tearDown();
//    }
//
//    @Test
//    public void shouldApplyToPixelWithoutPredicateAndColorRestrictions() {
//        //given
//
//        //when
//        boundedImageTransformTest.applyToPixels();
//
//        //then
//        verify(filterMock, times(1)).apply(0, 0);
//    }
//
//    @Test
//    public void shouldApplyToPixelsWithCoordinatePredicate() {
//        //given
//        predicateTest = pixel -> pixel.getX() < 10;
//
//        given(bufferedImageMock.getHeight()).willReturn(20);
//        given(bufferedImageMock.getWidth()).willReturn(20);
//        given(writableRasterMock.getPixel(anyInt(), anyInt(), any(int[].class))).willReturn(new int[]{0, 0, 0, 0});
//
//        boundedImageTransformTest = new BoundedImageTransform(
//                bufferedImageMock,
//                filterMock,
//                colorChannelsTest,
//                predicateTest
//        );
//
//        //when
//        boundedImageTransformTest.applyToPixels();
//
//        //then
//        verify(filterMock, times(200)).apply(anyInt(), anyInt());
//    }
//
//    @Test
//    public void shouldApplyToPixelsWithColorPredicate() {
//        //given
//        predicateTest = pixel -> pixel.getRed() < 100;
//
//        given(bufferedImageMock.getHeight()).willReturn(10);
//        given(bufferedImageMock.getWidth()).willReturn(10);
//        given(writableRasterMock.getPixel(anyInt(), lt(6), any(int[].class))).willReturn(new int[]{200, 0, 0, 0});
//        given(writableRasterMock.getPixel(anyInt(), geq(6), any(int[].class))).willReturn(new int[]{50, 0, 0, 0});
//
//        boundedImageTransformTest = new BoundedImageTransform(
//                bufferedImageMock,
//                filterMock,
//                colorChannelsTest,
//                predicateTest
//        );
//
//        //when
//        boundedImageTransformTest.applyToPixels();
//
//        //then
//        verify(filterMock, times(40)).apply(anyInt(), anyInt());
//    }
//}
