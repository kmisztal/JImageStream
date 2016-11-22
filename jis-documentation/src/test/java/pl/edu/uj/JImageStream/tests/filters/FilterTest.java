package pl.edu.uj.JImageStream.tests.filters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.edu.uj.JImageStream.api.core.Filter;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


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

        //then
        testFilter.saveToImage(bufferedImageMock);
        verify(bufferedImageMock).setData(bufferedImageMockSecondary.copyData(null));

    }


}
