package pl.uj.edu.JImageStream.api;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.function.Predicate;

public class BoundedImageTransform implements ImageTransform {

    private int height;
    private int width;
    private BufferedImage image;
    private Predicate<Point> predicate;
    private Filter filter;

    public BoundedImageTransform(BufferedImage bufferedImage, Predicate<Point> predicate, Filter filter){
        this.height = bufferedImage.getHeight();
        this.width = bufferedImage.getWidth();
        this.predicate = predicate;
        this.filter = filter;
        this.image = bufferedImage;
    }

    @Override
    public void apply() {
        filter.setSource(image);
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                //todo create class pixel with height and width instead of x y for clarity, can wait
                if (predicate.test(new Point(i, j))) {
                    filter.apply(i, j);
                }
            }
        }
        filter.saveToImage(image);
    }
}
