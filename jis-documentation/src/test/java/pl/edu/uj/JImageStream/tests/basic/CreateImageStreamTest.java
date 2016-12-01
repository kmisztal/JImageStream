package pl.edu.uj.JImageStream.tests.basic;

import org.junit.Test;
import pl.edu.uj.JImageStream.tests.AbstractBaseTest;

public class CreateImageStreamTest extends AbstractBaseTest {

    @Test
    public void createImageStream() {
        // tag::createImageStream[]
        streamableImage.stream();
        // end::createImageStream[]
    }

    @Test
    public void createParallelImageStream() {
        // tag::createParallelImageStream[]
        streamableImage.parallelStream();
        // end::createParallelImageStream[]
    }

}
