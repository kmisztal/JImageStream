package pl.edu.uj.JImageStream.filters.edge.roberts;

import pl.edu.uj.JImageStream.filters.abstractFilters.ConvolutionFilter;

public class RobertsCrossXFilter extends ConvolutionFilter {

    public RobertsCrossXFilter() {
        setKernelSize(2);
        createKernel();
    }

    @Override
    protected void createKernel() {
        kernel = new float[]{1, 0, 0, -1};
    }

    @Override
    protected void setKernelSize(int s) {
        kernelSize = s;
    }
}
