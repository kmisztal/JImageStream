package pl.edu.uj.JImageStream.api.filters;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

public class GreenFilter extends Filter {
    @Override
    public void apply(int x, int y) {
        Pixel pixel = getPixel(x, y);
        pixel.setRed(0);
        pixel.setBlue(0);
        setPixel(x, y, pixel);
    }
}
