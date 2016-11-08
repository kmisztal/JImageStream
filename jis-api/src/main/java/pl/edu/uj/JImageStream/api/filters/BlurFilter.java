package pl.edu.uj.JImageStream.api.filters;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

import java.util.ArrayList;
import java.util.List;

public class BlurFilter extends Filter {

    private float []kernel;
    private int height;
    private int width;
    private int kernelSize;

    public BlurFilter() {
        createKernel(5);
    }

    @Override
    public void apply(int x, int y) {
        setSourceDimension();

        if(!isEdgePixel(x, y)){
            List<Pixel> neighbours = getNeighboursArray(x,y);

            int outputRed = 0;
            int outputGreen = 0;
            int outputBlue = 0;

            for(int i = 0; i < kernel.length; ++i) {
                outputRed += (int)((neighbours.get(i).getRed())*kernel[i]);
                outputGreen += (int)((neighbours.get(i).getGreen())*kernel[i]);
                outputBlue += (int)((neighbours.get(i).getBlue())*kernel[i]);
            }
            setPixel(x,y, new Pixel(outputRed, outputGreen, outputBlue, 255));

        } else {
            /* pixels on edges - no changes */
            setPixel(x, y, getPixel(x,y));
        }
    }

    /* for 5x5 mask */
    private void createKernel(int s) {
        kernelSize = s;
        kernel = new float[kernelSize*kernelSize];
        final float v = 1.f / kernel.length;

        for(int i=0; i < kernel.length; ++i) {
            kernel[i] = v;
        }
    }
    private boolean isEdgePixel(int indX, int indY) {
        int halfSize = kernelSize/2;
        boolean isEdgePixel = false;
        for(int i=-halfSize; i <= halfSize; ++i) {
            if(isPixelIndexOnEdge(indX + i, width) || isPixelIndexOnEdge(indY + i, height)){
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

    private List<Pixel> getNeighboursArray(int x, int y){
        List<Pixel> neighbours = new ArrayList<>();
        int halfSize = kernelSize/2;

        for(int i = -halfSize; i <= halfSize; ++i) {
            for(int j = -halfSize; j <= halfSize; ++j) {
                neighbours.add(getPixel(x + j, y + i));
            }
        }
        return neighbours;
    }
}
