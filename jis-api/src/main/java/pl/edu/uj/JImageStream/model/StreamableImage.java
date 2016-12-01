package pl.edu.uj.JImageStream.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import pl.edu.uj.JImageStream.api.ImageStream;
import pl.edu.uj.JImageStream.utils.ImageUtils;

public class StreamableImage {

    private BufferedImage bufferedImage;

    public StreamableImage(File file) throws IOException {
        bufferedImage = ImageUtils.convert(ImageIO.read(file), BufferedImage.TYPE_INT_ARGB);
    }

    public StreamableImage(BufferedImage bufferedImage) {
        this.bufferedImage = ImageUtils.convert(bufferedImage, BufferedImage.TYPE_INT_ARGB);
    }

    public ImageStream stream() {
        return new ImageStream(bufferedImage, false);
    }

    public ImageStream parallelStream() {
        return new ImageStream(bufferedImage, true);
    }

}
