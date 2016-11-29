package pl.edu.uj.JImageStream.predicates;

import pl.edu.uj.JImageStream.model.Pixel;

import java.util.function.Predicate;

/**
 * Created by pPanek on 2016-11-29.
 */
public class ThresholdPredicate implements Predicate<Pixel> {

    private int threshold = 100;

    public ThresholdPredicate() { }

    public ThresholdPredicate(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean test(Pixel pixel) {
        int[] colors = pixel.getColors();
        int gray = (colors[0] + colors[1] + colors[2]) / 3;
        return gray < threshold ? false : true;
    }
}
