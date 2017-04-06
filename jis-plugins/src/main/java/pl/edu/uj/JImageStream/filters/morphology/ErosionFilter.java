package pl.edu.uj.JImageStream.filters.morphology;

import pl.edu.uj.JImageStream.filters.MorphologyFilter;
import pl.edu.uj.JImageStream.model.Pixel;

import java.util.Comparator;
import java.util.List;

public class ErosionFilter extends MorphologyFilter {

    public ErosionFilter() {
        maskSize = 3;
    }

    public ErosionFilter(int maskSize) {
        this.maskSize = maskSize;
    }

    public ErosionFilter(int maskSize, int kernelShape) {
        this.maskSize = maskSize;
        this.kernelShape = kernelShape;
    }

    @Override
    protected Pixel getPixelResult(List<Pixel> list) {
        Integer red = list.stream().map(Pixel::getRed).min(Comparator.naturalOrder()).get();
        Integer green = list.stream().map(Pixel::getGreen).min(Comparator.naturalOrder()).get();
        Integer blue = list.stream().map(Pixel::getBlue).min(Comparator.naturalOrder()).get();
        Integer alpha = list.stream().map(Pixel::getAlpha).min(Comparator.naturalOrder()).get();

        return new Pixel(red, green, blue, alpha);
    }

}
