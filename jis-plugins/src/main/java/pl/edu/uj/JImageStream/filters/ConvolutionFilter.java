package pl.edu.uj.JImageStream.filters;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

import java.util.ArrayList;
import java.util.List;

public abstract class ConvolutionFilter extends Filter {
    protected float[] kernel;
    private int height;
    private int width;
    protected int kernelSize;

    @Override
    public void apply(int x, int y) {
        setSourceDimension();


            int outputRed = 0;
            int outputGreen = 0;
            int outputBlue = 0;
            int outputAlpha = getPixel(x, y).getAlpha();

            int halfSize = kernelSize / 2;

            for (int i = -halfSize; i <= halfSize; ++i) {
                for (int j = -halfSize; j <= halfSize; ++j) {
                  //  int indX =
            }
        }



            //setPixel(x, y, new Pixel(outputRed, outputGreen, outputBlue, outputAlpha));
    }

    protected abstract void createKernel();

    protected abstract void setKernelSize(int s);

    private boolean isEdgePixel(int indX, int indY) {
        int halfSize = kernelSize / 2;
        boolean isEdgePixel = false;
        for (int i = -halfSize; i <= halfSize; ++i) {
            if (isPixelIndexOnEdge(indX + i, width) || isPixelIndexOnEdge(indY + i, height)) {
                isEdgePixel = true;
                break;
            }
        }
        return isEdgePixel;
    }

    private boolean isPixelIndexOnEdge(int coord, int dimension) {
        return (coord < 0) || (coord >= dimension);
    }

    private void setSourceDimension() {
        height = getSourceHeight();
        width = getSourceWidth();
    }

    private List<Pixel> getNeighboursArray(int x, int y) {
        List<Pixel> neighbours = new ArrayList<>();
        int halfSize = kernelSize / 2;

        for (int i = -halfSize; i <= halfSize; ++i) {
            for (int j = -halfSize; j <= halfSize; ++j) {
                neighbours.add(getPixel(x + j, y + i));
            }
        }
        return neighbours;
    }
}
