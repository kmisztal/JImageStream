package pl.edu.uj.JImageStream.filters.statistical;

import pl.edu.uj.JImageStream.filters.StatisticalFilter;
import pl.edu.uj.JImageStream.model.Pixel;

import java.util.List;
import java.util.stream.Collectors;

public class MedianFilter extends StatisticalFilter {

    public MedianFilter() {
        maskSize = 3;
    }

    public MedianFilter(int maskSize) {
        this.maskSize = maskSize;
    }

    @Override
    protected Pixel getPixelResult(List<Pixel> list) {
        List<Integer> red = list.stream().map(Pixel::getRed).sorted().collect(Collectors.toList());
        List<Integer> green = list.stream().map(Pixel::getGreen).sorted().collect(Collectors.toList());
        List<Integer> blue = list.stream().map(Pixel::getBlue).sorted().collect(Collectors.toList());
        List<Integer> alpha = list.stream().map(Pixel::getAlpha).sorted().collect(Collectors.toList());

        return new Pixel(getMedian(red), getMedian(green), getMedian(blue), getMedian(alpha));
    }

    private int getMedian(List<Integer> list) {
        int output;
        if (list.size() % 2 == 0) {
            output = (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / 2;
        } else {
            output = list.get(list.size() / 2);
        }
        return output;
    }

}
