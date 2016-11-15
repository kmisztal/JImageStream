package pl.edu.uj.JImageStream.filters.edge;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class RobertsCrossXFilter extends ConvolutionFilter {

    public RobertsCrossXFilter() {
        setKernelSize(1);
        createKernel(1);
    }

    @Override
    protected void createKernel(int s) {
        kernel = new float[]{1, 0, 0, -1};
    }

    @Override
    protected void setKernelSize(int s) {
        kernelSize = 2;
    }
}
