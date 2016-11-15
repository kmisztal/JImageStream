package pl.edu.uj.JImageStream.filters.edge;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

public class EdgeDetectionFilter extends Filter {

    private WritableRaster imageX;
    private WritableRaster imageY;

    public EdgeDetectionFilter(BufferedImage imageX, BufferedImage imageY) {
        this.imageX = imageX.getRaster();
        this.imageY = imageY.getRaster();
    }

    @Override
    public void apply(int x, int y) {

        int[] pixelX = imageX.getPixel(x, y, (int[]) null);
        int[] pixelY = imageY.getPixel(x, y, (int[]) null);

        int red = (int) Math.sqrt(pixelX[0] * pixelX[0] + pixelY[0] * pixelY[0]);
        int green = (int) Math.sqrt(pixelX[1] * pixelX[1] + pixelY[1] * pixelY[1]);
        int blue = (int) Math.sqrt(pixelX[2] * pixelX[2] + pixelY[2] * pixelY[2]);
        int alpha = getPixel(x, y).getAlpha();

        setPixel(x, y, new Pixel(red, green, blue, alpha));
    }
}
