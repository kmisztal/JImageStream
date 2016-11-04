package pl.edu.uj.JImageStream.api.transforms;

import pl.edu.uj.JImageStream.api.Filter;
import pl.edu.uj.JImageStream.api.ImageTransform;
import pl.edu.uj.JImageStream.model.ColorChannel;

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

    public BoundedImageTransform(BufferedImage bufferedImage, Predicate<Point> predicate, Filter filter, ColorChannel[] colorChannels){
        this.height = bufferedImage.getHeight();
        this.width = bufferedImage.getWidth();
        this.predicate = predicate;
        this.filter = filter;
        this.image = bufferedImage;
        this.colorChannels = colorChannels;
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
