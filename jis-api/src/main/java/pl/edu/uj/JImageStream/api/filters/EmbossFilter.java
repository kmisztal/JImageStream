package pl.edu.uj.JImageStream.api.filters;

/**
 * Created by kuba on 09.11.16.
 */
public class EmbossFilter extends ConvolutionFilter {

    public EmbossFilter() {
        setKernelSize(3);
        createKernel(3);
    }

    @Override
    protected void createKernel(int s) {
        this.kernel = new float[]{
                -2.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 2.0f
        };
    }

    @Override
    protected void setKernelSize(int s) {
        this.kernelSize = s;
    }
}
