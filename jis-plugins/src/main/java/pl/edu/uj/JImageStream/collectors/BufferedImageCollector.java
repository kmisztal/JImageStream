package pl.edu.uj.JImageStream.collectors;

import pl.edu.uj.JImageStream.api.core.Collector;

import java.awt.image.BufferedImage;

public class BufferedImageCollector implements Collector<BufferedImage> {
    @Override
    public BufferedImage collect(BufferedImage bufferedImage) {
        logger.info(this.getClass().getSimpleName() + " successfully collected image");
        return bufferedImage;
    }
}
