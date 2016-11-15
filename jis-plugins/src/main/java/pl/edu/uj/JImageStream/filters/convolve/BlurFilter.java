package pl.edu.uj.JImageStream.filters.convolve;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class BlurFilter extends ConvolutionFilter {

    public BlurFilter() {
        setKernelSize(5);
        createKernel(5);
    }


    /* for 5x5 mask */
    @Override
    protected void createKernel(int s) {
        kernel = new float[kernelSize * kernelSize];
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
