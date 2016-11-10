import org.junit.Before;
import pl.edu.uj.JImageStream.collectors.StreamableImageCollector;
import pl.edu.uj.JImageStream.model.StreamableImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractBaseTest {

    protected StreamableImage streamableImage;

    @Before
    public void setUp() throws IOException {
        if (!Files.exists(Paths.get("target/docs/images"))) {
            Files.createDirectories(Paths.get("target/docs/images"));
        }
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("lena.png").getFile());

        streamableImage = new StreamableImage(file);
        streamableImage.stream().collect(new StreamableImageCollector())
                .save("png", "target/docs/images/lena.png");
    }

}
