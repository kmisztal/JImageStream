package pl.edu.uj.JImageStream.filters.edge;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class SobelYFilter extends ConvolutionFilter {

    public SobelYFilter() {
        createKernel(3);
    }

    @Override
    protected void createKernel(int kernelSize) {
        kernel = new float[][]{
                {-1, -2, -1},
                {0, 0, 0},
                {1, 2, 1}
        };
    }
}
