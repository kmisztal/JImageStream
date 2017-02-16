package pl.edu.uj.JImageStream.filters.YCbCr;
import pl.edu.uj.JImageStream.filters.YCbCrFilter;

public class LuminanceChanelFilter extends YCbCrFilter{
    @Override
    protected void calculateOutput(int x, int y) {
        output = Y;
    }
}