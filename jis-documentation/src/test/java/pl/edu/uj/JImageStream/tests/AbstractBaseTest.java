package pl.edu.uj.JImageStream.tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import org.junit.Before;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.collectors.FileCollector;
import pl.edu.uj.JImageStream.model.StreamableImage;

public abstract class AbstractBaseTest {

    protected StreamableImage streamableImage;

    @Before
    public void setUp() throws IOException {
        if (!Files.exists(Paths.get("target/docs/images"))) {
            Files.createDirectories(Paths.get("target/docs/images"));
        }
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File("");
        try {
            file = new File(classLoader.getResource("images").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();

        streamableImage = new StreamableImage(files[new Random().nextInt(files.length)]);

//        streamableImage.stream().collect(new FileCollector("target/docs/images/0.png", FileCollector.Format.PNG));
    }

    protected void save(StreamableImage before, BufferedImage after, String path) {
        before.stream()
                .collect(Collectors.toFile(FileCollector.Format.PNG, "target/docs/images/before_" + path));
        new StreamableImage(after).stream()
                .collect(Collectors.toFile(FileCollector.Format.PNG, "target/docs/images/after_" + path));
    }

}