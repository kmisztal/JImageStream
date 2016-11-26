package pl.edu.uj.JImageStream.filters.edge;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class RobertsCrossXFilter extends ConvolutionFilter {

    public RobertsCrossXFilter() {
        createKernel(2);
    }

    @Override
    protected void createKernel(int kernelSize) {
        kernel = new float[][]{
                {1, 0},
                {0, -1}
        };
    }
}
