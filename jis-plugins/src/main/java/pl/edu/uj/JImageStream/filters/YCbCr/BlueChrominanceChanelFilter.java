package pl.edu.uj.JImageStream.filters.YCbCr;
import pl.edu.uj.JImageStream.filters.YCbCrFilter;
import pl.edu.uj.JImageStream.model.Pixel;

public class BlueChrominanceChanelFilter extends YCbCrFilter{
    @Override
    protected void calculateOutput(int x, int y) {
        Pixel p = getPixel(x,y);
        output = (int) (128 - 0.16874 * p.getRed() - 0.33126 * p.getGreen() + 0.50000 * p.getBlue());
    }
}
