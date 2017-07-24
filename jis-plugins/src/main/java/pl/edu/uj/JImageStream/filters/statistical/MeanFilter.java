package pl.edu.uj.JImageStream.filters.statistical;

import pl.edu.uj.JImageStream.filters.abstractFilters.StatisticalFilter;
import pl.edu.uj.JImageStream.model.Pixel;

import java.util.List;

public class MeanFilter extends StatisticalFilter {


    public MeanFilter() {
        maskSize = 3;
    }

    public MeanFilter(int maskSize) {
        this.maskSize = maskSize;
    }

    @Override
    protected Pixel getPixelResult(List<Pixel> list) {
        double red = list.stream().map(Pixel::getRed).mapToDouble(a -> a).average().getAsDouble();
        double green = list.stream().map(Pixel::getGreen).mapToDouble(a -> a).average().getAsDouble();
        double blue = list.stream().map(Pixel::getBlue).mapToDouble(a -> a).average().getAsDouble();
        double alpha = list.stream().map(Pixel::getAlpha).mapToDouble(a -> a).average().getAsDouble();

        return new Pixel((int)red, (int)green, (int)blue, (int) alpha);
    }

}
