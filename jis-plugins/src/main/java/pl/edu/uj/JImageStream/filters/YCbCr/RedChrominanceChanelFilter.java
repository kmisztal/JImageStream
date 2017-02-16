package pl.edu.uj.JImageStream.filters.YCbCr;
import pl.edu.uj.JImageStream.filters.YCbCrFilter;
import pl.edu.uj.JImageStream.model.Pixel;

public class RedChrominanceChanelFilter extends YCbCrFilter {
    @Override
    protected void calculateOutput(int x, int y) {
        Pixel p = getPixel(x,y);
        output = (int) (128 + 0.50000 * p.getRed() - 0.41869 * p.getGreen() - 0.08131 * p.getBlue());
    }
}
