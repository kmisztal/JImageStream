package pl.edu.uj.JImageStream.filters.convolve;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class BoxBlurFilter extends ConvolutionFilter {

    public BoxBlurFilter() {
        setKernelSize(5);
        createKernel();
    }

    public BoxBlurFilter(int kernelSize) {
        setKernelSize(kernelSize);
        createKernel();
    }

    @Override
    protected void createKernel() {
        this.kernel = new float[kernelSize][kernelSize];
        final float v = 1.f / (kernelSize*kernelSize);
        for (int i = 0; i < kernelSize; ++i) {
            for(int j = 0; j < kernelSize; ++j) {
                kernel[i][j] = v;
            }
        }

    }

    @Override
    protected void setKernelSize(int s) {
        this.kernelSize = s;
    }
}
