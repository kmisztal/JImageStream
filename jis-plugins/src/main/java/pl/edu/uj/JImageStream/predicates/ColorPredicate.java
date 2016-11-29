package pl.edu.uj.JImageStream.predicates;

import pl.edu.uj.JImageStream.model.Pixel;

import java.awt.Color;
import java.util.function.Predicate;

/**
 * Created by pPanek on 2016-11-28.
 */
public class ColorPredicate implements Predicate<Pixel> {

    Color color;

    public ColorPredicate(Color color) {
        this.color = color;
    }

    public ColorPredicate(int[] colors) {
        color = new Color(colors[0], colors[1], colors[2], colors[3]);
    }

    @Override
    public boolean test(Pixel pixel) {
        int[] colors = pixel.getColors();
        return color.equals(new Color(colors[0], colors[1], colors[2], colors[3]));
    }
}
