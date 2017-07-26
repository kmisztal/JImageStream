package pl.edu.uj.JImageStream.filters.abstractFilters;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;
import pl.edu.uj.JImageStream.model.UnpackedImage;

import java.awt.image.BufferedImage;

public abstract class ArithmeticFilter extends Filter {

    private UnpackedImage image;

    public ArithmeticFilter(BufferedImage input) {
        this.image = new UnpackedImage(input);
    }

    @Override
    public final void apply(int x, int y) {
        Pixel calculate = calculate(getPixel(x, y), new Pixel(image.getPixel(x, y)));
        setPixel(x, y, calculate);
    }

    protected abstract Pixel calculate(Pixel x, Pixel y);
}
