package pl.edu.uj.JImageStream.collectors;

import pl.edu.uj.JImageStream.api.core.Collector;

import java.awt.image.BufferedImage;

public class BufferedImageCollector implements Collector<BufferedImage> {
    @Override
    public BufferedImage collect(BufferedImage bufferedImage) {
        logger.info("collecting image into BufferedImage");
        return bufferedImage;
    }
}
