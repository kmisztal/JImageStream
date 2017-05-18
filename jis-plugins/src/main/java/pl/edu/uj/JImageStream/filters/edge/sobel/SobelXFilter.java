package pl.edu.uj.JImageStream.filters.edge.sobel;

import pl.edu.uj.JImageStream.filters.abstractFilters.ConvolutionFilter;

public class SobelXFilter extends ConvolutionFilter {

    public SobelXFilter() {
        setKernelSize(3);
        createKernel();
    }

    @Override
    protected void createKernel() {
        kernel = new float[]
                {-1, 0, 1,
                        -2, 0, 2,
                        -1, 0, 1};
    }

    @Override
    protected void setKernelSize(int s) {
        kernelSize = s;
    }
}
