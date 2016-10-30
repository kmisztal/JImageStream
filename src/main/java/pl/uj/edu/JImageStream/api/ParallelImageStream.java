//package pl.uj.edu.JImageStream.api;
//
//import StreamableImage;
//
//import java.awt.Point;
//import java.awt.image.BufferedImage;
//import java.awt.image.ColorModel;
//import java.awt.image.WritableRaster;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.function.Predicate;
//
//todo fix this grebosz
//public class ParallelImageStream {
//
//    private BufferedImage originalImage;
//
//    private WritableRaster workingRaster;
//
//    private List<ImageTransform> filters;
//
//
//    public ParallelImageStream(BufferedImage originalImage) {
//        this.originalImage = originalImage;
//        this.workingRaster = originalImage.copyData(null);
//        filters = new LinkedList<>();
//    }
//
//    public BoundsFilterBuilder bounds(Predicate<Point> predicate) {
//        return new BoundsFilterBuilder(predicate);
//    }
//
//    public StreamableImage collect() {
//
//        if (!filters.isEmpty()) {
//            filters.forEach(ImageTransform::apply);
//        }
//
//        ColorModel cm = originalImage.getColorModel();
//        boolean isAlpha = cm.isAlphaPremultiplied();
//        return new StreamableImage(new BufferedImage(cm, workingRaster, isAlpha, null));
//    }
//
//    private WritableRaster getWorkingRaster() {
//        return workingRaster;
//    }
//
//    private void addImageTransform(ImageTransform imageTransform) {
//        filters.add(imageTransform);
//    }
//
//    public class BoundsFilterBuilder {
//
//        private Predicate<Point> predicate;
//
//        private BoundsFilterBuilder(Predicate<Point> predicate) {
//            this.predicate = predicate;
//        }
//
//        public ParallelImageStream apply(Filter filter) {
//            filter.setSource(ParallelImageStream.this.getWorkingRaster());
//
//            ParallelImageStream.this.addImageTransform(new ParallelBoundedImageTransform(ParallelImageStream.this.originalImage.getHeight(),
//                    ParallelImageStream.this.originalImage.getWidth(), predicate, filter));
//
//            return ParallelImageStream.this;
//        }
//    }
//}
