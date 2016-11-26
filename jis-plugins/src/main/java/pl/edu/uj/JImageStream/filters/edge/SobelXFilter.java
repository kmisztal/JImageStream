package pl.edu.uj.JImageStream.filters.edge;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class SobelXFilter extends ConvolutionFilter {

    public SobelXFilter() {
        createKernel(3);
    }

    @Override
    protected void createKernel(int kernelSize) {
        kernel = new float[][]{
                {-1, 0, 1},
                {-2, 0, 2},
                {-1, 0, 1}
        };
    }
}
