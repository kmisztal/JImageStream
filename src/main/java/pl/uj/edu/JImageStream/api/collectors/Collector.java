package pl.uj.edu.JImageStream.api.collectors;

import java.awt.image.BufferedImage;


public interface Collector<T> {
    T collect(BufferedImage bufferedImage);
}
