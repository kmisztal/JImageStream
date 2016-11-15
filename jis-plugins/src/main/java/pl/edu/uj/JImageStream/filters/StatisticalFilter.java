package pl.edu.uj.JImageStream.filters;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

import java.util.ArrayList;
import java.util.List;

public abstract class StatisticalFilter extends Filter {
    protected int maskSize;

    protected List<Pixel> getPixelList(int x, int y) {
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
        return pixelList;
    }
}
