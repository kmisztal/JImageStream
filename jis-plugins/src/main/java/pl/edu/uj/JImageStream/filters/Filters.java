package pl.edu.uj.JImageStream.filters;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.filters.arithmetic.InvertFilter;
import pl.edu.uj.JImageStream.filters.binarization.OtsuBinarization;
import pl.edu.uj.JImageStream.filters.color.GrayScaleFilter;
import pl.edu.uj.JImageStream.filters.color.SepiaFilter;
import pl.edu.uj.JImageStream.filters.convolve.BoxBlurFilter;
import pl.edu.uj.JImageStream.filters.convolve.EmbossFilter;
import pl.edu.uj.JImageStream.filters.convolve.SharpenFilter;
import pl.edu.uj.JImageStream.filters.edge.EdgeDetection4XFilter;
import pl.edu.uj.JImageStream.filters.edge.EdgeDetectionFilter;
import pl.edu.uj.JImageStream.filters.edge.roberts.RobertsCrossXFilter;
import pl.edu.uj.JImageStream.filters.edge.roberts.RobertsCrossYFilter;
import pl.edu.uj.JImageStream.filters.edge.sobel.Sobel135Filter;
import pl.edu.uj.JImageStream.filters.edge.sobel.Sobel45Filter;
import pl.edu.uj.JImageStream.filters.edge.sobel.SobelXFilter;
import pl.edu.uj.JImageStream.filters.edge.sobel.SobelYFilter;
import pl.edu.uj.JImageStream.filters.equalization.CLAHEFilter;
import pl.edu.uj.JImageStream.filters.equalization.HistogramEqualization;
import pl.edu.uj.JImageStream.filters.morphology.DilationFilter;
import pl.edu.uj.JImageStream.filters.morphology.ErosionFilter;
import pl.edu.uj.JImageStream.filters.noise.SaltAndPepperFilter;
import pl.edu.uj.JImageStream.filters.statistical.MaxFilter;
import pl.edu.uj.JImageStream.filters.statistical.MeanFilter;
import pl.edu.uj.JImageStream.filters.statistical.MedianFilter;
import pl.edu.uj.JImageStream.filters.statistical.MinFilter;
import pl.edu.uj.JImageStream.filters.transform.RotateFilter;

import java.awt.image.BufferedImage;

public class Filters {

    public static Filter invertFilter() {
        return new InvertFilter();
    }

    public static Filter otsuBinarizationFilter() {
        return new OtsuBinarization();
    }

    public static Filter otsuBinarizationFilter(boolean weight) {
        return new OtsuBinarization();
    }

    public static Filter grayScaleFilter() {
        return new GrayScaleFilter();
    }

    public static Filter sepiaFilter() {
        return new SepiaFilter();
    }

    public static Filter boxBlurFilter() {
        return new BoxBlurFilter();
    }

    public static Filter boxBlurFilter(int kernelSie) {
        return new BoxBlurFilter(kernelSie);
    }

    public static Filter embossFilter() {
        return new EmbossFilter();
    }

    public static Filter sharpenFilter() {
        return new SharpenFilter();
    }


    public static Filter robertsCrossXFilter() {
        return new RobertsCrossXFilter();
    }

    public static Filter robertsCrossYFilter() {
        return new RobertsCrossYFilter();
    }

    public static Filter sobelXFilter() {
        return new SobelXFilter();
    }

    public static Filter sobelYFilter() {
        return new SobelYFilter();
    }

    public static Filter sobel45Filter() {
        return new Sobel45Filter();
    }

    public static Filter sobel135Filter() {
        return new Sobel135Filter();
    }

    public static Filter edgeDetection(BufferedImage bufferedImageX, BufferedImage bufferedImageY) {
        return new EdgeDetectionFilter(bufferedImageX, bufferedImageY);
    }

    public static Filter edgeDetection(BufferedImage bufferedImage45, BufferedImage bufferedImageX, BufferedImage bufferedImage135, BufferedImage bufferedImageY) {
        return new EdgeDetection4XFilter(bufferedImage45, bufferedImageX, bufferedImage135, bufferedImageY);
    }

    public static Filter clahEqualizationFilter() {
        return new CLAHEFilter();
    }

    public static Filter clahEqualizationFilter(double slope, int blockRadius, int bins) {
        return new CLAHEFilter(slope, blockRadius, bins);
    }

    public static Filter histogramEqualizationFilter() {
        return new HistogramEqualization();
    }

    public static Filter dilatationFilter() {
        return new DilationFilter();
    }

    public static Filter erosionFilter() {
        return new ErosionFilter();
    }

    public static Filter saltAndPepperFilter() {
        return new SaltAndPepperFilter();
    }

    public static Filter saltAndPepperFilter(double p) {
        return new SaltAndPepperFilter(p);
    }

    public static Filter meanFilter() {
        return new MeanFilter();
    }

    public static Filter meanFilter(int mask) {
        return new MeanFilter(mask);
    }

    public static Filter maxFilter() {
        return new MaxFilter();
    }

    public static Filter maxFilter(int mask) {
        return new MaxFilter(mask);
    }

    public static Filter minFilter() {
        return new MinFilter();
    }

    public static Filter minFilter(int mask) {
        return new MinFilter(mask);
    }

    public static Filter medianFilter() {
        return new MedianFilter();
    }

    public static Filter medianFilter(int mask) {
        return new MedianFilter(mask);
    }

    public static Filter rotateFilter(double alfa) {
        return new RotateFilter(alfa);
    }
}
