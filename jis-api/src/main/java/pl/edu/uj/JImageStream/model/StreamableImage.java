package pl.edu.uj.JImageStream.model;

import pl.edu.uj.JImageStream.api.ImageStream;
import pl.edu.uj.JImageStream.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//todo needs some added functionality, etc
public class StreamableImage {

    private BufferedImage bufferedImage;

    public StreamableImage(File file) throws IOException {
        bufferedImage = ImageUtils.convert(ImageIO.read(file), BufferedImage.TYPE_INT_ARGB);
    }

    public StreamableImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    //todo refactor, move to another class
    public void save(String format, String file) throws IOException {
        ImageIO.write(bufferedImage, format, new File(file));
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public ImageStream stream() {
        return new ImageStream(bufferedImage, false);
    }

    public ImageStream parallelStream() {
        return new ImageStream(bufferedImage, true);
    }

}
