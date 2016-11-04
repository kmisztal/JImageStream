package pl.edu.uj.JImageStream.api.core;

import java.awt.image.BufferedImage;


public interface Collector<T> {
    T collect(BufferedImage bufferedImage);
}
