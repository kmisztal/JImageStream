package pl.edu.uj.JImageStream.filters.convolve;

import pl.edu.uj.JImageStream.filters.abstractFilters.ConvolutionFilter;

public class EmbossFilter extends ConvolutionFilter {

    public EmbossFilter() {
        setKernelSize(3);
        createKernel();
    }

    @Override
    protected void createKernel() {
        this.kernel = new float[]{
                -2.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 2.0f
        };
    }

    @Override
    protected void setKernelSize(int s) {
        this.kernelSize = s;
    }
}