package pl.edu.uj.JImageStream.filters.convolve;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class SharpenFilter extends ConvolutionFilter {

    public SharpenFilter() {
        createKernel(3);
    }

    @Override
    protected void createKernel(int kernelSize) {
        this.kernel = new float[][]{
                { -1.0f, -1.0f, -1.0f },
                { -1.0f, 9.0f, -1.0f },
                { -1.0f, -1.0f, -1.0f }
        };
    }
}
