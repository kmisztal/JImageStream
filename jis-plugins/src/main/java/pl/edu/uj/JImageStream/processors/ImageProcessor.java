package pl.edu.uj.JImageStream.processors;

import pl.edu.uj.JImageStream.model.StreamableImage;

public interface ImageProcessor {
    StreamableImage process(StreamableImage input);
}
