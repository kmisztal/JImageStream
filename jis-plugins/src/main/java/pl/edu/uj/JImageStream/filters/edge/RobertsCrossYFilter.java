package pl.edu.uj.JImageStream.filters.edge;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class RobertsCrossYFilter extends ConvolutionFilter {

    public RobertsCrossYFilter() {
        setKernelSize(2);
        createKernel();
    }

    @Override
    protected void createKernel() {
        this.kernel = new float[][]{
                        {0, 1},
                        {-1, 0}
        };
    }

    @Override
    protected void setKernelSize(int s) {
        kernelSize = s;
    }
}
