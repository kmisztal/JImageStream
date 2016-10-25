package pl.uj.edu.JImageStream.api.filters;

import pl.uj.edu.JImageStream.api.Filter;
import pl.uj.edu.JImageStream.model.Pixel;

public class BlueFilter extends Filter {
    @Override
    public void apply(int x, int y) {
        Pixel pixel = getPixel(x, y);
        pixel.setRed(0);
        pixel.setGreen(0);
        setPixel(x, y, pixel);
    }
}
