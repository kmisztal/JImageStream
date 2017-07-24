package pl.edu.uj.JImageStream.filters.abstractFilters;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

public abstract class YCbCrFilter extends Filter {
    protected int Y;
    protected int output;

    @Override
    public void apply(int x, int y) {
        calculateY(x,y);
        calculateOutput(x,y);
        setPixel(x, y, new Pixel(output, output, output, getPixel(x,y).getAlpha()));
    }

    protected void calculateY(int x, int y){
        Pixel p = getPixel(x, y);
        Y = (int) (0.299 * p.getRed() + 0.587 * p.getGreen() + 0.114 * p.getBlue());
    }

    protected abstract void calculateOutput(int x, int y);
}