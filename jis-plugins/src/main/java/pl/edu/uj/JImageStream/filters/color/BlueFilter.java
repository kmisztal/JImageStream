package pl.edu.uj.JImageStream.filters.color;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

public class BlueFilter extends Filter {
    @Override
    public void apply(int x, int y) {
        Pixel pixel = getPixel(x, y);
        pixel.setRed(0);
        pixel.setGreen(0);
        setPixel(x, y, pixel);
    }
}
