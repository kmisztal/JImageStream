package pl.edu.uj.JImageStream.tests.performance;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class AbstractTest {
    private static Map<String, BufferedImage> map = new HashMap();

    protected BufferedImage getBufferedImage(int x, int y) {
        if (map.containsKey(x + "x" + y)) {
            return map.get(x + "x" + y);
        } else {
            BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
            Random r = new Random();

            for (int i = 0; i < x; ++i) {
                for (int j = 0; j < y; ++j) {
                    bufferedImage.setRGB(i, j, r.nextInt());
                }
            }
            map.put(x + "x" + y, bufferedImage);
            return bufferedImage;
        }
    }
}
