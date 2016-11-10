package pl.edu.uj.JImageStream.filters;

/**
 * Created by kuba on 09.11.16.
 */
public class BoxBlurFilter extends ConvolutionFilter {

    public BoxBlurFilter(int kernelSize) {
        setKernelSize(kernelSize);
        createKernel(kernelSize);
    }

    @Override
    protected void createKernel(int s) {
        this.kernel = new float[kernelSize * kernelSize];
        final float v = 1.f / kernel.length;
        for (int i = 0; i < kernel.length; ++i) {
            kernel[i] = v;
        }
    }

    @Override
    protected void setKernelSize(int s) {
        this.kernelSize = s;
    }
}
