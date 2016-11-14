package pl.edu.uj.JImageStream.filters;

/**
 * Created by kuba on 14.11.16.
 */
public class SobelYFilter extends ConvolutionFilter {

    public SobelYFilter() {
        setKernelSize(1);
        createKernel(1);
    }

    @Override
    protected void createKernel(int s) {
        kernel = new float[]
                {
                        -1, -2, -1,
                        0, 0, 0,
                        1, 2, 1
                };
    }

    @Override
    protected void setKernelSize(int s) {
        kernelSize = 3;
    }
}
