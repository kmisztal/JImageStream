package pl.edu.uj.JImageStream.filters.convolve;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class EmbossFilter extends ConvolutionFilter {

    public EmbossFilter() {
        createKernel(3);
    }

    @Override
    protected void createKernel(int kernelSize) {
        this.kernel = new float[][]{
                { -2.0f, 0.0f, 0.0f },
                { 0.0f, 1.0f, 0.0f },
                { 0.0f, 0.0f, 2.0f }
        };
    }
}