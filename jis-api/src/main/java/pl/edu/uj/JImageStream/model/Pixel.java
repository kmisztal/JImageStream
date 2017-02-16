package pl.edu.uj.JImageStream.model;

import java.awt.Point;

public class Pixel extends Point {

    private int colors[]; // 0 = RED, 1 = GREEN, 2 = BLUE, 3 = ALPHA

    public Pixel(int x, int y) {
        super(x, y);
    }

    public Pixel(int red, int green, int blue, int alpha) {
        this.colors = new int[]{red, green, blue, alpha};
    }

    public Pixel(int[] colors) {
        this.colors = colors;
    }

    public Pixel(int x, int y, int red, int green, int blue, int alpha) {
        super(x, y);
        colors = new int[]{red, green, blue, alpha};
    }

    public int[] getColors() {
        return colors;
    }

    public int getRed() {
        return colors[0];
    }

    public void setRed(int red) {
        colors[0] = red;
    }

    public int getGreen() {
        return colors[1];
    }

    public void setGreen(int green) {
        colors[1] = green;
    }

    public int getBlue() {
        return colors[2];
    }

    public void setBlue(int blue) {
        colors[2] = blue;
    }

    public int getAlpha() {
        return colors[3];
    }

    public void setAlpha(int alpha) {
        colors[3] = alpha;
    }
}
