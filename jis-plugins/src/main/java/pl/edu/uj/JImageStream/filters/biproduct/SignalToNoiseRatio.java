package pl.edu.uj.JImageStream.filters.biproduct;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.IntStream;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.UnpackedImage;

public class SignalToNoiseRatio extends Filter {

    private List<Double> output;
    private UnpackedImage unpackedOriginal;
    private double noiseSignal = 0;
    private double originalMinusNoise = 0;

    public SignalToNoiseRatio(BufferedImage original, List<Double> output) {
        this.output = output;
        unpackedOriginal = new UnpackedImage(original);
    }

    @Override
    public void apply(int x, int y) {
        int noise = IntStream.of(getPixel(x, y).getColors()).sum();
        int original = IntStream.of(unpackedOriginal.getPixel(x, y)).sum();

        noiseSignal += Math.pow(noise, 2);
        originalMinusNoise += Math.pow(original - noise, 2);

    }

    @Override
    public void tearDown() {
        output.add(noiseSignal / originalMinusNoise);
        super.tearDown();
    }
}
