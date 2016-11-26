package pl.edu.uj.JImageStream.filters.noise;

import pl.edu.uj.JImageStream.filters.ConvolutionFilter;

public class GaussFilter extends ConvolutionFilter {

    private final double theta;

    public GaussFilter(int kernelSize, double theta) {
        this.theta = theta;
        createKernel(kernelSize);
    }

    @Override
    protected void createKernel(int kernelSize) {
        this.kernel = new float[kernelSize][kernelSize];
        double[][] gaussian2D = gaussian2D(theta, kernelSize);

        double sum = 0;
        for (int i = 0; i < kernelSize; ++i) {
            for (int j = 0; j < kernelSize; ++j) {
                sum += gaussian2D[i][j];
            }

        }

        for (int i = 0; i < kernelSize; ++i) {
            for (int j = 0; j < kernelSize; ++j) {
                gaussian2D[i][j] = gaussian2D[i][j] / sum;
                this.kernel[i][j] = (float) gaussian2D[i][j];
            }

        }
    }

    private double gaussianDiscrete2D(double theta, int x, int y) {
        double g = 0;
        for (double ySubPixel = y - 0.5; ySubPixel < y + 0.55; ySubPixel += 0.1) {
            for (double xSubPixel = x - 0.5; xSubPixel < x + 0.55; xSubPixel += 0.1) {
                g += ((1 / (2 * Math.PI * theta * theta)) *
                        Math.pow(Math.E, -(xSubPixel * xSubPixel + ySubPixel * ySubPixel) /
                                (2 * theta * theta)));
            }
        }
        return g;
    }

    private double[][] gaussian2D(double theta, int size) {
        double[][] kernel = new double[size][size];
        for (int j = 0; j < size; ++j) {
            for (int i = 0; i < size; ++i) {
                kernel[i][j] = gaussianDiscrete2D(theta, i - (size / 2), j - (size / 2));
            }
        }
        return kernel;
    }
}
