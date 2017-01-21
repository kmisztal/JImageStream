package pl.edu.uj.JImageStream.filters.edge;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class EdgeDetection4XFilter extends EdgeDetectionFilter {

    private WritableRaster imageZ;
    private WritableRaster imageT;

    public EdgeDetection4XFilter(BufferedImage imageX, BufferedImage imageY, BufferedImage imageZ, BufferedImage imageT) {
        super(imageX, imageY);
        this.imageZ = imageZ.getRaster();
        this.imageT = imageT.getRaster();
    }

    @Override
    public void apply(int x, int y) {

        int[] pixelX = imageX.getPixel(x, y, (int[]) null);
        int[] pixelY = imageY.getPixel(x, y, (int[]) null);
        int[] pixelZ = imageZ.getPixel(x, y, (int[]) null);
        int[] pixelT = imageT.getPixel(x, y, (int[]) null);

        int red = (int) this.dotProduct(this.dotProduct(pixelX[0], pixelY[0]), this.dotProduct(pixelZ[0], pixelT[0]));
        int green = (int) this.dotProduct(this.dotProduct(pixelX[1], pixelY[1]), this.dotProduct(pixelZ[1], pixelT[1]));
        int blue = (int) this.dotProduct(this.dotProduct(pixelX[2], pixelY[2]), this.dotProduct(pixelZ[2], pixelT[2]));
        int alpha = getPixel(x, y).getAlpha();

        setPixel(x, y, new Pixel(red, green, blue, alpha));
    }
}
