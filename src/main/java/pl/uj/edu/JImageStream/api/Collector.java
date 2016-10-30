package pl.uj.edu.JImageStream.api;

import java.awt.image.BufferedImage;


public interface Collector<T> {
    T collect(BufferedImage bufferedImage);
}
