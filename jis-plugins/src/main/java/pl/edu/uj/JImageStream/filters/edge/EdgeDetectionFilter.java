package pl.edu.uj.JImageStream.filters.edge;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class EdgeDetectionFilter extends Filter {

    protected WritableRaster imageX;
    protected WritableRaster imageY;

    public EdgeDetectionFilter(BufferedImage imageX, BufferedImage imageY) {
        this.imageX = imageX.getRaster();
        this.imageY = imageY.getRaster();
    }

    protected double dotProduct(double x, double y){
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public void apply(int x, int y) {

        int[] pixelX = imageX.getPixel(x, y, (int[]) null);
        int[] pixelY = imageY.getPixel(x, y, (int[]) null);

        int red = (int) this.dotProduct(pixelX[0], pixelY[0]);
        int green = (int) this.dotProduct(pixelX[1], pixelY[1]);
        int blue = (int) this.dotProduct(pixelX[2], pixelY[2]);
        int alpha = getPixel(x, y).getAlpha();

        setPixel(x, y, new Pixel(red, green, blue, alpha));
    }
}
