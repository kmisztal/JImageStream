package pl.edu.uj.JImageStream.filters;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;


public abstract class ConvolutionFilter extends Filter {
    protected float[][] kernel;
    private int height;
    private int width;

    @Override
    public void apply(int x, int y) {
        setSourceDimension();

            int outputRed = 0;
            int outputGreen = 0;
            int outputBlue = 0;
            int outputAlpha = getPixel(x, y).getAlpha();

            int halfSize = kernel.length / 2;

            for (int cooy = -halfSize; cooy < halfSize; ++cooy) {
                int indY = y + cooy;
                if (indY < 0 || indY >= height) {
                    indY = y;
                }
                for (int coox = -halfSize; coox < halfSize; ++coox) {
                    int indX = x + coox;
                    if (indX < 0 || indX >= width) {
                        indX = x;
                    }

                    Pixel neigh = getPixel(indX, indY);
                    int xKern = coox + halfSize;
                    int yKern = cooy + halfSize;
                    outputRed += neigh.getRed()*kernel[yKern][xKern];
                    outputBlue += neigh.getBlue()*kernel[yKern][xKern];
                    outputGreen += neigh.getGreen()*kernel[yKern][xKern];
                }
            }

            setPixel(x, y, new Pixel(outputRed, outputGreen, outputBlue, outputAlpha));
    }

    protected abstract void createKernel(int kernelSize);

    protected void setSourceDimension() {
        height = getSourceHeight();
        width = getSourceWidth();
    }
}
