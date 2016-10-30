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
    private ColorChannel[] colorChannels;

    public BoundedImageTransform(BufferedImage bufferedImage, Predicate<Point> predicate, Filter filter, ColorChannel[] cr){
        this.height = bufferedImage.getHeight();
        this.width = bufferedImage.getWidth();
        this.predicate = predicate;
        this.filter = filter;
        this.image = bufferedImage;
        this.colorChannels = cr;
    }

    @Override
    public void apply() {
        filter.setSource(image);
        filter.setRestrictions(colorChannels);
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                //todo create class pixel with height and width instead of x y for clarity
                if (predicate.test(new Point(i, j))) {
                    filter.apply(i, j);
                }
            }
        }
        filter.saveToImage(image);
    }
}
