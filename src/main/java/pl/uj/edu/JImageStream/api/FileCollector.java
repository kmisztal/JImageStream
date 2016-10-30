package pl.uj.edu.JImageStream.api;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class FileCollector implements Collector<File> {

    private String format;
    private String name;

    public FileCollector(String format, String name) {
        this.format = format;
        this.name = name;
    }

    @Override
    public File collect(BufferedImage bufferedImage) {
        File file = new File(name);
        try {
            ImageIO.write(bufferedImage, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
