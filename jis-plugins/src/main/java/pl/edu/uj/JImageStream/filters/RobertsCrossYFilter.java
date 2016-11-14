package pl.edu.uj.JImageStream.filters;

/**
 * Created by kuba on 14.11.16.
 */
public class RobertsCrossYFilter extends ConvolutionFilter {

    public RobertsCrossYFilter() {
        setKernelSize(1);
        createKernel(1);
    }

    @Override
    protected void createKernel(int s) {
        kernel = new float[]{0, 1, -1, 0};
    }

    @Override
    protected void setKernelSize(int s) {
        kernelSize = 2;
    }
}
