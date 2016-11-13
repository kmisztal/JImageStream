package pl.edu.uj.JImageStream.filters;

/**
 * Created by kuba on 09.11.16.
 */
public class SharpenFilter extends ConvolutionFilter {

    public SharpenFilter() {
        setKernelSize(3);
        createKernel(3);
    }

    @Override
    protected void createKernel(int s) {
        kernel = new float[]{
                -1.0f, -1.0f, -1.0f,
                -1.0f,  9.0f, -1.0f,
                -1.0f, -1.0f, -1.0f
        };
    }

    @Override
    protected void setKernelSize(int s) {
        kernelSize = 3;
    }
}
