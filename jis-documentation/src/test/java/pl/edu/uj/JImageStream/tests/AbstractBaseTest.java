package pl.edu.uj.JImageStream.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Before;
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
            file = new File(classLoader.getResource("lena.png").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }

        streamableImage = new StreamableImage(file);

        streamableImage.stream().collect(new FileCollector("target/docs/images/lena.png", FileCollector.Format.PNG));
    }

}