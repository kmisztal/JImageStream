package pl.edu.uj.JImageStream.api.collectors;

import pl.edu.uj.JImageStream.api.Collector;

import java.awt.image.BufferedImage;

public class BufferedImageCollector implements Collector<BufferedImage> {
    @Override
    public BufferedImage collect(BufferedImage bufferedImage) {
        return bufferedImage;
    }
}
