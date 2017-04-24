package pl.edu.uj.JImageStream.filters.convolve;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class BoxBlurFilter extends ConvolutionFilter {


    public BoxBlurFilter(int kernelSize) {
        createKernel(kernelSize);
    }

    @Override
    protected void createKernel(int kernelSize) {
        this.kernel = new float[kernelSize][kernelSize];
        final float v = 1.f / (kernelSize*kernelSize);
        for (int i = 0; i < kernelSize; ++i) {
            for(int j = 0; j < kernelSize; ++j) {
                kernel[i][j] = v;
            }
        }

    }
}
