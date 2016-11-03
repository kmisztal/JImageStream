package pl.uj.edu.JImageStream;

import pl.uj.edu.JImageStream.api.Filter;
import pl.uj.edu.JImageStream.api.collectors.StreamableImageCollector;
import pl.uj.edu.JImageStream.api.filters.*;
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

//      channel() test
        streamableImage.stream().bounds(point -> true)
                .channel(ColorChannel.BLUE, ColorChannel.RED).apply(new SepiaFilter())
                .collect(new StreamableImageCollector()).save("jpg", "sepiaBlueRedChannel.jpg");



//      parallelStream() test
            streamableImage.parallelStream().bounds(point -> true)
                    .apply(new GreenFilter())
                    .collect(new StreamableImageCollector()).save("jpg", "parallelStream-green.jpg");


        try {
            streamableImage.parallelStream()
                    .setThreads(50)
                    .apply(new Filter() {
                        @Override
                        public void apply(int x, int y) {

                        }
                    })
                    .setThreads(1)
                    .apply(new SaltAndPepperFilter())
                    .collect(new StreamableImageCollector())
                    .save("jpg", "parallelTest.jpg");

        } catch(Exception e){
            e.printStackTrace();
        }

    }

}
