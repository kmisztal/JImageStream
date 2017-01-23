package pl.edu.uj.JImageStream.filters.edge;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

        import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class Sobel45Filter extends ConvolutionFilter {

    public Sobel45Filter() {
        setKernelSize(3);
        createKernel();
    }

    @Override
    protected void createKernel() {
        kernel = new float[]
                {0, 1, 2,
                        -1, 0, 1,
                        -2, -1, 0};
    }

    @Override
    protected void setKernelSize(int s) {
        kernelSize = s;
    }
}
