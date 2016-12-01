package pl.edu.uj.JImageStream.predicates;

import pl.edu.uj.JImageStream.model.Pixel;

import java.util.function.Predicate;

public class CirclePredicate implements Predicate<Pixel> {

    private int x;
    private int y;
    private double r;

    public CirclePredicate(int x, int y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public boolean test(Pixel pixel) {
        return pixel.distance(x, y) <= r;
    }
}
