package pl.edu.uj.JImageStream.filters.morphology;

import pl.edu.uj.JImageStream.filters.MorphologyFilter;
import pl.edu.uj.JImageStream.model.Pixel;

import java.util.Comparator;
import java.util.List;

public class DilationFilter extends MorphologyFilter {

    public DilationFilter() {
        kernelRadius = 3;
    }

    public DilationFilter(int kernelRadius) {
        this.kernelRadius = kernelRadius;
    }

    public DilationFilter(int kernelRadius, int kernelShape) {
        this.kernelRadius = kernelRadius;
        this.kernelShape = kernelShape;
    }

    @Override
    protected Pixel getPixelResult(List<Pixel> list) {
        Integer red = list.stream().map(Pixel::getRed).max(Comparator.naturalOrder()).get();
        Integer green = list.stream().map(Pixel::getGreen).max(Comparator.naturalOrder()).get();
        Integer blue = list.stream().map(Pixel::getBlue).max(Comparator.naturalOrder()).get();
        Integer alpha = list.stream().map(Pixel::getAlpha).max(Comparator.naturalOrder()).get();

        return new Pixel(red, green, blue, alpha);
    }
}
