package pl.edu.uj.JImageStream.filters.edge;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class RobertsCrossYFilter extends ConvolutionFilter {

    public RobertsCrossYFilter() {
        createKernel(2);
    }

    @Override
    protected void createKernel(int kernelSize) {
        this.kernel = new float[][]{
                        {0, 1},
                        {-1, 0}
        };
    }
}
