package pl.edu.uj.JImageStream.filters;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

public class SaltAndPepperFilter extends Filter {

    private double p;

    public SaltAndPepperFilter() {
        this.p = .2;
    }

    public SaltAndPepperFilter(double p) {
        this.p = p;
    }

    @Override
    public void apply(int x, int y) {
        int r;
        if (Math.random() < p) {
            if (Math.random() < 0.5) {
                r = 255;
            } else {
                r = 0;
            }
            setPixel(x, y, new Pixel(r, r, r, 255));

        }
    }
}
