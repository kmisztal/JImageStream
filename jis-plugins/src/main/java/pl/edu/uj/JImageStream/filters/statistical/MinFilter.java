package pl.edu.uj.JImageStream.filters.statistical;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

public class MinFilter extends Filter {

    public MinFilter() {
        maskSize = 3;
    }

    public MinFilter(int maskSize) {
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

        Integer red = pixelList.stream().map(Pixel::getRed).sorted().min(Comparator.naturalOrder()).get();
        Integer green = pixelList.stream().map(Pixel::getGreen).sorted().min(Comparator.naturalOrder()).get();
        Integer blue = pixelList.stream().map(Pixel::getBlue).sorted().min(Comparator.naturalOrder()).get();
        Integer alpha = pixelList.stream().map(Pixel::getAlpha).sorted().min(Comparator.naturalOrder()).get();

        setPixel(x, y, new Pixel(red, green, blue, alpha));
    }


}
