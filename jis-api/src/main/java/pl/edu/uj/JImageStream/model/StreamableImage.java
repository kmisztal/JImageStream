package pl.edu.uj.JImageStream.model;

import pl.edu.uj.JImageStream.api.ImageStream;
import pl.edu.uj.JImageStream.util.Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//todo needs some added functionality, return bufferedImage, etc
public class StreamableImage {

    private BufferedImage bufferedImage;

    //todo extract to IOImageUtilsClass, maybe
    public StreamableImage(File file) throws IOException {
        bufferedImage = Util.convert(ImageIO.read(file), BufferedImage.TYPE_4BYTE_ABGR_PRE);
    }

    public StreamableImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    //todo same, maybe
    public void save(String format, String file) throws IOException {
        ImageIO.write(bufferedImage, format, new File(file));
    }

    public int getHeight() {
        return bufferedImage.getHeight();
    }

    public int getWidth() {
        return bufferedImage.getWidth();
    }


    public ImageStream stream() {
        return new ImageStream(bufferedImage, false);
    }

    public ImageStream parallelStream() {
        return new ImageStream(bufferedImage, true);
    }

}
