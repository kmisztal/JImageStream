package pl.edu.uj.JImageStream.filters.statistical;

import java.util.Comparator;
import java.util.List;

import pl.edu.uj.JImageStream.filters.StatisticalFilter;
import pl.edu.uj.JImageStream.model.Pixel;

public class MaxFilter extends StatisticalFilter {

    public MaxFilter() {
        maskSize = 3;
    }

    public MaxFilter(int maskSize) {
        this.maskSize = maskSize;
    }

    @Override
    public void apply(int x, int y) {
        List<Pixel> pixelList = getPixelList(x, y);

        Integer red = pixelList.stream().map(Pixel::getRed).sorted().max(Comparator.naturalOrder()).get();
        Integer green = pixelList.stream().map(Pixel::getGreen).sorted().max(Comparator.naturalOrder()).get();
        Integer blue = pixelList.stream().map(Pixel::getBlue).sorted().max(Comparator.naturalOrder()).get();
        Integer alpha = pixelList.stream().map(Pixel::getAlpha).sorted().max(Comparator.naturalOrder()).get();

        setPixel(x, y, new Pixel(red, green, blue, alpha));
    }


}