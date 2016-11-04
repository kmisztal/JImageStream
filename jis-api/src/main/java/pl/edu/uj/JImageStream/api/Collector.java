package pl.edu.uj.JImageStream.api;

import java.awt.image.BufferedImage;


public interface Collector<T> {
    T collect(BufferedImage bufferedImage);
}
