package pl.edu.uj.JImageStream.filters.edge.sobel;

import pl.edu.uj.JImageStream.filters.abstractFilters.ConvolutionFilter;

public class Sobel135Filter extends ConvolutionFilter {

    public Sobel135Filter() {
        setKernelSize(3);
        createKernel();
    }

    @Override
    protected void createKernel() {
        kernel = new float[]
                {-2, -1, 0,
                 -1, 0,  1,
                0,   1,  2};
    }

    @Override
    protected void setKernelSize(int s) {
        kernelSize = s;
    }
}
