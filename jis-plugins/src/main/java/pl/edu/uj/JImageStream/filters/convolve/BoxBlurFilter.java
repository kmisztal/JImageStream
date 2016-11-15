package pl.edu.uj.JImageStream.filters.convolve;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class BoxBlurFilter extends ConvolutionFilter {

    public BoxBlurFilter(int kernelSize) {
        setKernelSize(kernelSize);
        createKernel(kernelSize);
    }

    @Override
    protected void createKernel(int s) {
        this.kernel = new float[kernelSize * kernelSize];
        final float v = 1.f / kernel.length;
        for (int i = 0; i < kernel.length; ++i) {
            kernel[i] = v;
        }
    }

    @Override
    protected void setKernelSize(int s) {
        this.kernelSize = s;
    }
}
