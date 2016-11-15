package pl.edu.uj.JImageStream.filters.statistical;

import java.util.List;
import java.util.stream.Collectors;

import pl.edu.uj.JImageStream.filters.StatisticalFilter;
import pl.edu.uj.JImageStream.model.Pixel;

public class MedianFilter extends StatisticalFilter {

    public MedianFilter() {
        maskSize = 3;
    }

    public MedianFilter(int maskSize) {
        this.maskSize = maskSize;
    }

    @Override
    public void apply(int x, int y) {
        List<Pixel> pixelList = getPixelList(x, y);

        List<Integer> red = pixelList.stream().map(Pixel::getRed).sorted().collect(Collectors.toList());
        List<Integer> green = pixelList.stream().map(Pixel::getGreen).sorted().collect(Collectors.toList());
        List<Integer> blue = pixelList.stream().map(Pixel::getBlue).sorted().collect(Collectors.toList());
        List<Integer> alpha = pixelList.stream().map(Pixel::getAlpha).sorted().collect(Collectors.toList());

        setPixel(x, y,
                new Pixel(red.get(red.size() / 2),
                        green.get(green.size() / 2),
                        blue.get(blue.size() / 2),
                        alpha.get(alpha.size() / 2)));
    }

}
