package pl.edu.uj.JImageStream.tests.filters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import pl.edu.uj.JImageStream.collectors.FileCollector;
import pl.edu.uj.JImageStream.filters.Histogram;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.StreamableImage;

public class HistogramTest{
    public static void main(String[] args) {
        try{
            String directoryPath = "jis-documentation/target/docs/images/";
            if (!Files.exists(Paths.get(directoryPath))) {
                Files.createDirectories(Paths.get(directoryPath));

            }
            File fileLenaImage = new File(directoryPath + "lena.png");
            File fileInput = new File(directoryPath + "histogram.png");

            BufferedImage lenaImage = ImageIO.read(fileLenaImage);
            StreamableImage streamableImage = new StreamableImage(fileInput);

            // tag::histogramFilter[]
            streamableImage.stream()
                    .apply(new Histogram(lenaImage, ColorChannel.RED, ColorChannel.GREEN, ColorChannel.BLUE, ColorChannel.ALPHA))
                    .collect(new FileCollector("png", directoryPath + "RGBAhistogram.png"));
            // end::histogramFilter[]

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
