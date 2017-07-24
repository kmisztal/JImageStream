package pl.edu.uj.JImageStream.collectors;

public class Collectors {

    public static BufferedImageCollector toBufferedImage() {
        return new BufferedImageCollector();
    }

    public static FileCollector toFile(FileCollector.Format format, String fileName) {
        return new FileCollector(fileName, format);
    }

    public static DisplayCollector display() {
        return new DisplayCollector();
    }

    public static DisplayCollector display(String title) {
        return new DisplayCollector(title);
    }

    public static StreamableImageCollector toStreamableImage() {
        return new StreamableImageCollector();
    }

}
