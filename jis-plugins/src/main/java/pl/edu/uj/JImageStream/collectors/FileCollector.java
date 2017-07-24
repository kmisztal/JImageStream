package pl.edu.uj.JImageStream.collectors;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import pl.edu.uj.JImageStream.api.core.Collector;


public class FileCollector implements Collector<File> {


    private String format;
    private String name;

    public FileCollector(String name, Format format) {
        this.format = format.getFormat();
        this.name = name;
    }

    @Override
    public File collect(BufferedImage bufferedImage) {
        File file = new File(name);
        try {
            ImageIO.write(bufferedImage, format, file);
            logger.info(this.getClass() + " successfully collected image");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public enum Format {

        JPEG("jpg"), PNG("png"), BMP("bmp"), GIF("gif");

        private String format;

        Format(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }
}
