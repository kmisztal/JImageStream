package pl.edu.uj.JImageStream.filters.convolve;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class SharpenFilter extends ConvolutionFilter {

    public SharpenFilter() {
        setKernelSize(3);
        createKernel(3);
    }

    @Override
    protected void createKernel(int s) {
        this.kernel = new float[]{
                -1.0f, -1.0f, -1.0f,
                -1.0f,  9.0f, -1.0f,
                -1.0f, -1.0f, -1.0f
        };
    }

    @Override
    protected void setKernelSize(int s) {
        this.kernelSize = s;
    }
}
