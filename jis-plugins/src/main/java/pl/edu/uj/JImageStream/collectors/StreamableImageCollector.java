package pl.edu.uj.JImageStream.collectors;

import pl.edu.uj.JImageStream.api.core.Collector;
import pl.edu.uj.JImageStream.model.StreamableImage;

import java.awt.image.BufferedImage;


public class StreamableImageCollector implements Collector<StreamableImage> {
    @Override
    public StreamableImage collect(BufferedImage bufferedImage) {
        logger.info(this.getClass().getSimpleName() + " successfully collected image");
        return new StreamableImage(bufferedImage);
    }
}