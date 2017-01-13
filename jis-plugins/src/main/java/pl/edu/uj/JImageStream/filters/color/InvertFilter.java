package pl.edu.uj.JImageStream.filters.color;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

public class InvertFilter extends Filter {
    @Override
    public void apply(int x, int y) {
        Pixel pixel = getPixel(x, y);
        int red = 255 - pixel.getRed();
        int green = 255 - pixel.getGreen();
        int blue = 255 - pixel.getBlue();
        setPixel(x, y, new Pixel(red, green, blue, pixel.getAlpha()));
    }
}
