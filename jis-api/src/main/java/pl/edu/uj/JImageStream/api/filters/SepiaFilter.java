package pl.edu.uj.JImageStream.api.filters;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

public class SepiaFilter extends Filter {
    @Override
    public void apply(int x, int y) {
        Pixel input = getPixel(x, y);
        int outputRed = (int) ((input.getRed() * .393) + (input.getGreen() * .769) + (input.getBlue() * .189));
        int outputGreen = (int) ((input.getRed() * .349) + (input.getGreen() * .686) + (input.getBlue() * .168));
        int outputBlue = (int) ((input.getRed() * .272) + (input.getGreen() * .534) + (input.getBlue() * .131));
        setPixel(x, y, new Pixel(Math.min(outputRed, 255), Math.min(outputGreen, 255), Math.min(outputBlue, 255), 255));

    }
}
