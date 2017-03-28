package pl.edu.uj.JImageStream.filters.color;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

public class HistogramEqualization extends Filter {
    private int[][] cdf;
    private int numberOfPixels;
    private int minRed;
    private int minGreen;
    private int minBlue;

    @Override
    public void setUp() {
        super.setUp();

        int[][] hist = getColorHistogram();

        // Cumulative Distribution Function
        cdf = new int[3][256];
        cdf[0][0] = hist[0][0];
        cdf[1][0] = hist[1][0];
        cdf[2][0] = hist[2][0];
        for (int i = 1; i < hist[0].length; i++) {
            for (int k = 0; k < 3; ++k)
                cdf[k][i] = cdf[k][i - 1] + hist[k][i];
        }

        numberOfPixels = getSourceWidth() * getSourceHeight();
        minRed = min(cdf[0]);
        minGreen = min(cdf[1]);
        minBlue = min(cdf[2]);
    }

    private int min(int[] arr) {
        int min = -1;
        for (int anArr : arr) {
            if (min == -1 || anArr < min) {
                min = anArr;
            }
        }
        return min;
    }

    @Override
    public void apply(int x, int y) {
        Pixel input = getPixel(x, y);

        final int red = (int) ((((double) cdf[0][input.getRed()] - minRed) / (numberOfPixels - minRed)) * 255);
        final int green = (int) ((((double) cdf[1][input.getBlue()] - minGreen) / (numberOfPixels - minGreen)) * 255);
        final int blue = (int) ((((double) cdf[2][input.getGreen()] - minBlue) / (numberOfPixels - minBlue)) * 255);

        setPixel(x, y, new Pixel(red, green, blue, input.getAlpha()));
    }
}
