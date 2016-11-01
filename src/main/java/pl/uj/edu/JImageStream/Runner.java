package pl.uj.edu.JImageStream;

import pl.uj.edu.JImageStream.api.Filter;
import pl.uj.edu.JImageStream.api.collectors.StreamableImageCollector;
import pl.uj.edu.JImageStream.api.filters.BlueFilter;
import pl.uj.edu.JImageStream.api.filters.GreenFilter;
import pl.uj.edu.JImageStream.api.filters.RedFilter;
import pl.uj.edu.JImageStream.api.filters.SaltAndPepperFilter;
import pl.uj.edu.JImageStream.api.filters.SepiaFilter;
import pl.uj.edu.JImageStream.model.StreamableImage;
import pl.uj.edu.JImageStream.api.ColorChannel;

import java.io.File;
import java.io.IOException;

public class Runner {

    public static void main(String args[]) throws IOException {

        StreamableImage streamableImage = new StreamableImage(new File("lena.png"));

        streamableImage.stream().bounds(point -> true)
                .apply(new SaltAndPepperFilter(0.1)).bounds(point -> true).apply(new SepiaFilter())
                .collect(new StreamableImageCollector()).save("png", "saltAndPepperWithSepia.png");

        streamableImage.stream().bounds(point -> true).apply(new RedFilter()).collect(new StreamableImageCollector()).save("jpg", "red.jpg");
        streamableImage.stream().bounds(point -> true).apply(new BlueFilter()).collect(new StreamableImageCollector()).save("jpg", "blue.jpg");
        streamableImage.stream().bounds(point -> true).apply(new GreenFilter()).collect(new StreamableImageCollector()).save("jpg", "green.jpg");
        streamableImage.stream().apply(new GreenFilter()).collect(new StreamableImageCollector()).save("jpg", "green.jpg");

//        channel() test
//        streamableImage.stream().channel(ColorChannel.BLUE).apply(new GreenFilter()).collect().save("jpg", "green.jpg");


        long millis = System.currentTimeMillis();
        try {
            streamableImage.parallelStream().bounds(point -> true)
                    .apply(new Filter() {
                        @Override
                        public void apply(int x, int y) {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    })
                    .collect(new StreamableImageCollector());
        } catch (Exception e) {
            System.out.println("kamil, if you see this, parallelStream doesn't work");
        }
        System.out.println(System.currentTimeMillis() - millis);


    }

}
