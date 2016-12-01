package pl.edu.uj.JImageStream.collectors;

import java.awt.image.BufferedImage;
import pl.edu.uj.JImageStream.api.core.Collector;
import pl.edu.uj.JImageStream.model.StreamableImage;


public class StreamableImageCollector implements Collector<StreamableImage> {
    @Override
    public StreamableImage collect(BufferedImage bufferedImage) {
        logger.info(this.getClass() + " successfully collected image");
        return new StreamableImage(bufferedImage);
    }
}