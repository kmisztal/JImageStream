package pl.edu.uj.JImageStream.filters.statistical;

import pl.edu.uj.JImageStream.filters.StatisticalFilter;
import pl.edu.uj.JImageStream.model.Pixel;

import java.util.Comparator;
import java.util.List;

public class MinFilter extends StatisticalFilter {

    public MinFilter() {
        maskSize = 3;
    }

    public MinFilter(int maskSize) {
        this.maskSize = maskSize;
    }

    @Override
    protected Pixel getPixelResult(List<Pixel> list) {
        Integer red = list.stream().map(Pixel::getRed).sorted().min(Comparator.naturalOrder()).get();
        Integer green = list.stream().map(Pixel::getGreen).sorted().min(Comparator.naturalOrder()).get();
        Integer blue = list.stream().map(Pixel::getBlue).sorted().min(Comparator.naturalOrder()).get();
        Integer alpha = list.stream().map(Pixel::getAlpha).sorted().min(Comparator.naturalOrder()).get();

        return new Pixel(red, green, blue, alpha);
    }


}