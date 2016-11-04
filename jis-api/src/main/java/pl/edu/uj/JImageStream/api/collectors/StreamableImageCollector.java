package pl.edu.uj.JImageStream.api.collectors;

import pl.edu.uj.JImageStream.api.Collector;
import pl.edu.uj.JImageStream.model.StreamableImage;

import java.awt.image.BufferedImage;


public class StreamableImageCollector implements Collector<StreamableImage> {
    @Override
    public StreamableImage collect(BufferedImage bufferedImage) {
        return new StreamableImage(bufferedImage);
    }
}
