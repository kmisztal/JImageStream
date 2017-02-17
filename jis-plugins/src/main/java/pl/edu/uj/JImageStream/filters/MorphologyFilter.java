package pl.edu.uj.JImageStream.filters;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

import java.util.ArrayList;
import java.util.List;

public abstract class MorphologyFilter extends Filter {
    public static final int SQUARE_KERNEL = 0;
    public static final int BALL_KERNEL = 1;
    public static final int VERTICAL_LINE_KERNEL = 2;
    public static final int HORIZONTAL_LINE_KERNEL = 3;
    protected int maskSize;
    protected int kernelShape;
    protected boolean[][] kernel;

    protected List<Pixel> getPixelList(int x, int y) {
        if (kernel == null) {
            createKernel();
        }
        List<Pixel> pixelList = new ArrayList<>();
        final int v = maskSize / 2;
        for (int i = -v; i <= v; ++i) {
            for (int j = -v; j <= v; ++j) {
                try {
                    if (kernel[v + i][v + j]) {
                        pixelList.add(getPixel(x + i, y + j));
                    }
                } catch (Exception e) {
                    //corner case, no need to handle
                }
            }
        }
        return pixelList;
    }

    private void createKernel() {
        this.kernel = new boolean[maskSize][maskSize];
        final int v = maskSize / 2;
        switch (kernelShape) {
            case BALL_KERNEL:
                for (int i = -v; i <= v; ++i) {
                    for (int j = -v; j <= v; ++j) {
                        if (i * i + j * j < v * v)
                            this.kernel[v + i][v + j] = true;
                    }
                }
                break;
            case VERTICAL_LINE_KERNEL:
                for (int i = -v; i <= v; ++i) {
                    this.kernel[v][v + i] = true;
                }
                break;
            case HORIZONTAL_LINE_KERNEL:
                for (int i = -v; i <= v; ++i) {
                    this.kernel[v + i][v] = true;
                }
                break;
            case SQUARE_KERNEL:
            default:
                for (int i = -v; i <= v; ++i) {
                    for (int j = -v; j <= v; ++j) {
                        this.kernel[v + i][v + j] = true;
                    }
                }
        }
    }

    protected abstract Pixel getPixelResult(List<Pixel> list);

    public void apply(int x, int y) {
        Pixel p = getPixelResult(getPixelList(x, y));

        setPixel(x, y, p);
    }
}

