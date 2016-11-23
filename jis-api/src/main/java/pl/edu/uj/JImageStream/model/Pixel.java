package pl.edu.uj.JImageStream.model;

import java.awt.Point;

public class Pixel extends Point{

    private int red;
    private int green;
    private int blue;
    private int alpha;

    public Pixel(int x, int y) {
        super(x, y);
    }

    public Pixel(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public Pixel(int x, int y, int red, int green, int blue, int alpha) {
        super(x,y);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public int[] getColors() {
        return new int[]{red, green, blue, alpha};
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
}
