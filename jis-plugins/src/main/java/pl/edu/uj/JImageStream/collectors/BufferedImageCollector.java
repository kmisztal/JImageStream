package pl.edu.uj.JImageStream.collectors;

import java.awt.image.BufferedImage;
import pl.edu.uj.JImageStream.api.core.Collector;

public class BufferedImageCollector implements Collector<BufferedImage> {
    @Override
    public BufferedImage collect(BufferedImage bufferedImage) {
        logger.info(this.getClass() + " successfully collected image");
        return bufferedImage;
    }
}
