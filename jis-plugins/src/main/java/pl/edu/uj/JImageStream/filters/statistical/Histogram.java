package pl.edu.uj.JImageStream.filters.statistical;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

public class Histogram extends Filter {

    private File output;
    private int[][] rgb;

    public Histogram(File output) {
        this.output = output;
        this.rgb = new int[3][256];
    }

    @Override
    public void apply(int x, int y) {
        Pixel pixel = getPixel(x, y);
        ++rgb[0][pixel.getRed()];
        ++rgb[1][pixel.getGreen()];
        ++rgb[2][pixel.getBlue()];
    }

    @Override
    public void tearDown() {
        try {
            PrintWriter pw = new PrintWriter(output);
            pw.println("RED;GREEN;BLUE");
            for (int i = 0; i < 256; ++i) {
                pw.println(rgb[0][i] + ";" + rgb[1][i] + ";" + rgb[2][i]);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
        super.tearDown();
    }
}
