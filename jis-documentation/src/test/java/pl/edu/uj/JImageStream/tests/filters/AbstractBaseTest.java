package pl.edu.uj.JImageStream.tests.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import pl.edu.uj.JImageStream.collectors.FileCollector;
import pl.edu.uj.JImageStream.collectors.StreamableImageCollector;
import pl.edu.uj.JImageStream.model.StreamableImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractBaseTest {

    protected StreamableImage streamableImage;
    protected Logger logger = LogManager.getLogger("Test");

    @Before
    public void setUp() throws IOException {
        if (!Files.exists(Paths.get("target/docs/images"))) {
            Files.createDirectories(Paths.get("target/docs/images"));
        }
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File("");
        try {
            file = new File(classLoader.getResource("lena.png").toURI());
            logger.info("loading image");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("cannot load file from source");
        }

        streamableImage = new StreamableImage(file);

       streamableImage.stream().collect(new FileCollector("png", "target/docs/images/lena.png"));
    }

    protected void savingLogMessage(){
        logger.info("image successfully saved into file");
    }

}
