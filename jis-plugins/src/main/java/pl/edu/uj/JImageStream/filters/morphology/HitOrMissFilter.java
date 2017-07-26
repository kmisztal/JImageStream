package pl.edu.uj.JImageStream.filters.morphology;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

public class HitOrMissFilter extends Filter {

    private int[][] hitOrMissMatrix;

    public HitOrMissFilter(int[][] hitOrMissMatrix) {
        if (hitOrMissMatrix == null) {
            throw new RuntimeException("Hit or miss matrix can't be null");
        }
        this.hitOrMissMatrix = hitOrMissMatrix;
    }

    @Override
    public void apply(int x, int y) {

        int widthMiddle = hitOrMissMatrix[0].length / 2;
        int heightMiddle = hitOrMissMatrix.length / 2;

        boolean hit = true;
        for (int i = x - widthMiddle; i <= x + widthMiddle; ++i) {
            for (int j = y - heightMiddle; j <= y + heightMiddle; ++j) {
                try {
                    int value = getPixel(i, j).getRed();
                    switch (hitOrMissMatrix[j - (y - heightMiddle)][i - (x - widthMiddle)]) {
                        case -1:
                            if (value == 255) {
                                hit = false;
                            }
                            break;
                        case 1:
                            if (value == 0) {
                                hit = false;
                            }
                        default:
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    // expected on corners
                }
            }
        }

        if (hit) {
            setPixel(x, y, new Pixel(255, 255, 255, 255));
        } else {
            setPixel(x, y, new Pixel(0, 0, 0, 255));
        }

    }
}
