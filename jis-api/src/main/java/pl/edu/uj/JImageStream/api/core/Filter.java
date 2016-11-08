package pl.edu.uj.JImageStream.api.core;

import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public abstract class Filter {
    
    private WritableRaster source;
    private WritableRaster output;
    private ColorChannel[] colorRestrictions;

    public void setSource(BufferedImage bufferedImage) {
        this.source = bufferedImage.copyData(null);
        this.output = bufferedImage.copyData(null);
    }

    public void setRestrictions(ColorChannel[] colorChannels) {
        this.colorRestrictions = colorChannels;
    }

    public void saveToImage(BufferedImage bufferedImage) {
        bufferedImage.setData(output);
    }

    public abstract void apply(int x, int y);

    protected void setPixel(int x, int y, Pixel pixel) {
        int[] sourceColors = new int[4];
        source.getPixel(x, y, sourceColors);
        int[] outputColors = pixel.getColors();

        for (ColorChannel colorRestriction : colorRestrictions) {
            colorRestriction.process(sourceColors, outputColors);
        }

        output.setPixel(x, y, sourceColors);
    }

    protected Pixel getPixel(int x, int y) {
        //todo alpha support, investigate when and where alpha chanel is present
        int[] pixel = source.getPixel(x, y, (int[]) null);
        return new Pixel(pixel[0], pixel[1], pixel[2], 255);
    }

    protected int getSourceHeight(){
        return source.getHeight();
    }
    protected int getSourceWidth(){
        return source.getWidth();
    }


}
