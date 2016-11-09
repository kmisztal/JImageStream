package pl.edu.uj.JImageStream;

import pl.edu.uj.JImageStream.api.collectors.FileCollector;
import pl.edu.uj.JImageStream.api.filters.BoxBlurFilter;
import pl.edu.uj.JImageStream.api.filters.EmbossFilter;
import pl.edu.uj.JImageStream.api.filters.GaussFilter;
import pl.edu.uj.JImageStream.api.filters.GrayScaleFilter;
import pl.edu.uj.JImageStream.api.filters.SharpenFilter;
import pl.edu.uj.JImageStream.model.StreamableImage;

import java.io.File;
import java.io.IOException;

public class Runner {

    public static void main(String args[]) throws IOException {

        StreamableImage streamableImage = new StreamableImage(new File("lena.png"));

        streamableImage.parallelStream().apply(new BoxBlurFilter(5)).collect(new FileCollector("png", "boxBlur.png"));
        streamableImage.parallelStream().apply(new GrayScaleFilter()).times(2).apply(new EmbossFilter()).collect(new FileCollector("png", "emboss.png"));
        streamableImage.parallelStream().apply(new SharpenFilter()).collect(new FileCollector("png", "sharpen.png"));
        streamableImage.parallelStream().times(5).apply(new GaussFilter(3, 1)).collect(new FileCollector("png", "gauss.png"));

    }
}
