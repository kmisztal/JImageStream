package pl.uj.edu.JImageStream.api;

import pl.uj.edu.JImageStream.model.StreamableImage;

import java.awt.image.BufferedImage;


public class StreamableImageCollector implements Collector<StreamableImage> {
    @Override
    public StreamableImage collect(BufferedImage bufferedImage) {
        return new StreamableImage(bufferedImage);
    }
}
