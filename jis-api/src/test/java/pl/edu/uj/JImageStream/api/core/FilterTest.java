package pl.edu.uj.JImageStream.api.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static pl.edu.uj.JImageStream.model.ColorChannel.ALPHA;
import static pl.edu.uj.JImageStream.model.ColorChannel.BLUE;
import static pl.edu.uj.JImageStream.model.ColorChannel.GREEN;
import static pl.edu.uj.JImageStream.model.ColorChannel.RED;


public class FilterTest {

    private BufferedImage bufferedImageMock;
    private BufferedImage bufferedImageMockSecondary;
    private WritableRaster writableRasterMock;
    private WritableRaster writableRasterMockSecondary;

    private Filter testFilter;

    @Before
    public void setUp() {
        bufferedImageMock = Mockito.mock(BufferedImage.class);
        bufferedImageMockSecondary = Mockito.mock(BufferedImage.class);
        writableRasterMock = Mockito.mock(WritableRaster.class);
        writableRasterMockSecondary = Mockito.mock(WritableRaster.class);

        given(bufferedImageMock.copyData(null)).willReturn(writableRasterMock);
        given(bufferedImageMockSecondary.copyData(null)).willReturn(writableRasterMockSecondary);

        testFilter = new Filter() {
            @Override
            public void apply(int x, int y) {
            }
        };
    }

    @Test
    public void shouldChangeSourceAfterSecondCall() {
        //given

        //when
        testFilter.setSource(bufferedImageMock);
        testFilter.setSource(bufferedImageMockSecondary);
        testFilter.saveToImage(bufferedImageMock);

        //then
        verify(bufferedImageMock, times(1)).setData(writableRasterMockSecondary);
    }

    @Test
    public void shouldSetPixelWithChannelRestriction() {
        //given
        given(writableRasterMock.getPixel(0, 0, (int[]) null)).willReturn(new int[]{1, 1, 1, 1});

        testFilter = new Filter() {
            @Override
            public void apply(int x, int y) {
                setPixel(x, y, new Pixel(0, 0, 0, 0));
            }
        };

        //when
        testFilter.setRestrictions(new ColorChannel[]{GREEN, BLUE, ALPHA});
        testFilter.setSource(bufferedImageMock);
        testFilter.apply(0, 0);

        //then
        verify(writableRasterMock, times(1)).setPixel(0, 0, new int[]{1, 0, 0, 0});
    }

    @Test
    public void shouldCorrectValueOfOutOfRangePixel() {
        //given
        given(writableRasterMock.getPixel(0, 0, (int[]) null)).willReturn(new int[]{1, 1, 1, 1});

        testFilter = new Filter() {
            @Override
            public void apply(int x, int y) {
                setPixel(x, y, new Pixel(-100, 1, 300, 2));
            }
        };

        //when
        testFilter.setRestrictions(new ColorChannel[]{RED, GREEN, BLUE, ALPHA});
        testFilter.setSource(bufferedImageMock);
        testFilter.apply(0, 0);

        //then
        verify(writableRasterMock, times(1)).setPixel(0, 0, new int[]{0, 1, 255, 2});
    }
}
