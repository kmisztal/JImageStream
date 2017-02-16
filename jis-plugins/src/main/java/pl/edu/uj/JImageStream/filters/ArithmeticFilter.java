package pl.edu.uj.JImageStream.filters;

import java.awt.image.BufferedImage;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;
import pl.edu.uj.JImageStream.model.UnpackedImage;

public abstract class ArithmeticFilter extends Filter {

    private UnpackedImage image;

    public ArithmeticFilter(BufferedImage input) {
        this.image = new UnpackedImage(input);
    }

    @Override
    public final void apply(int x, int y) {
        calculate(getPixel(x, y), new Pixel(image.getPixel(x, y)));
    }

    protected abstract Pixel calculate(Pixel x, Pixel y);
}
