package pl.edu.uj.JImageStream.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

/**
 * Created by kuba on 2016-11-13.
 */
public class MeanFilter extends Filter {

    public MeanFilter() {
        maskSize = 3;
    }

    public MeanFilter(int maskSize) {
        this.maskSize = maskSize;
    }

    private int maskSize;

    @Override
    public void apply(int x, int y) {
        List<Pixel> pixelList = new ArrayList<>();

        for (int i = -maskSize / 2; i <= maskSize / 2; ++i) {
            for (int j = -maskSize / 2; j <= maskSize / 2; ++j) {
                try {
                    pixelList.add(getPixel(x + i, y + j));
                } catch (Exception e) {
                    //corner case, no need to handle
                }
            }
        }

        double red = pixelList.stream().map(Pixel::getRed).mapToDouble(a -> a).average().getAsDouble();
        double green = pixelList.stream().map(Pixel::getGreen).mapToDouble(a -> a).average().getAsDouble();
        double blue = pixelList.stream().map(Pixel::getBlue).mapToDouble(a -> a).average().getAsDouble();
        double alpha = pixelList.stream().map(Pixel::getAlpha).mapToDouble(a -> a).average().getAsDouble();

        setPixel(x, y, new Pixel((int) red, (int) green, (int) blue, (int) alpha));
    }


}
