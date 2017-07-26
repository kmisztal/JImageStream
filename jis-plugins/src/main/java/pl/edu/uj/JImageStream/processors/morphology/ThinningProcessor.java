package pl.edu.uj.JImageStream.processors.morphology;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.collectors.Collectors;
import pl.edu.uj.JImageStream.filters.abstractFilters.ArithmeticFilter;
import pl.edu.uj.JImageStream.filters.morphology.HitOrMissFilter;
import pl.edu.uj.JImageStream.model.Pixel;
import pl.edu.uj.JImageStream.model.StreamableImage;
import pl.edu.uj.JImageStream.model.UnpackedImage;
import pl.edu.uj.JImageStream.processors.ImageProcessor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ThinningProcessor implements ImageProcessor {


    private static int[][] side0 = new int[][]{
            {-1, -1, -1},
            {0, 1, 0},
            {1, 1, 1}
    };

    private static int[][] side90 = new int[][]{
            {1, 0, -1},
            {1, 1, -1},
            {1, 0, -1}
    };


    private static int[][] side180 = new int[][]{
            {1, 1, 1},
            {0, 1, 0},
            {-1, -1, -1}
    };


    private static int[][] side270 = new int[][]{
            {-1, 0, 1},
            {-1, 1, 1},
            {-1, 0, 1}
    };

    private static int[][] edge0 = new int[][]{
            {0, 1, 0},
            {-1, 1, 1},
            {-1, -1, 0}
    };

    private static int[][] edge90 = new int[][]{
            {-1, -1, 0},
            {-1, 1, 1},
            {0, 1, 0}
    };

    private static int[][] edge180 = new int[][]{
            {0, -1, -1},
            {1, 1, -1},
            {0, 1, 0}
    };

    private static int[][] edge270 = new int[][]{
            {0, 1, 0},
            {1, 1, -1},
            {0, -1, -1}
    };

    public StreamableImage process(StreamableImage input) {

//        BufferedImage side0 = input.stream().apply(new HitOrMissFilter(ThinningProcessor.side0)).collect(Collectors.toBufferedImage());
//        BufferedImage side90 = input.stream().apply(new HitOrMissFilter(ThinningProcessor.side90)).collect(Collectors.toBufferedImage());
//        BufferedImage side180 = input.stream().apply(new HitOrMissFilter(ThinningProcessor.side180)).collect(Collectors.toBufferedImage());
//        BufferedImage side270 = input.stream().apply(new HitOrMissFilter(ThinningProcessor.side270)).collect(Collectors.toBufferedImage());
//        BufferedImage edge0 = input.stream().apply(new HitOrMissFilter(ThinningProcessor.edge0)).collect(Collectors.toBufferedImage());
//        BufferedImage edge90 = input.stream().apply(new HitOrMissFilter(ThinningProcessor.edge90)).collect(Collectors.toBufferedImage());
//        BufferedImage edge180 = input.stream().apply(new HitOrMissFilter(ThinningProcessor.edge180)).collect(Collectors.toBufferedImage());
//        BufferedImage edge270 = input.stream().apply(new HitOrMissFilter(ThinningProcessor.edge270)).collect(Collectors.toBufferedImage());


//        BufferedImage sum = input.stream().apply(new SumFilter(side0, side90, side180, side270, edge0, edge90, edge180, edge270)).collect(Collectors.toBufferedImage());
//
//        new StreamableImage(sum).stream().collect(Collectors.display());
//
//        return input.stream().apply(new ArithmeticFilter(sum) {
//            @Override
//            protected Pixel calculate(Pixel x, Pixel y) {
//                boolean X = x.getRed() == 255;
//                boolean Y = y.getRed() == 255;
//                if (X && !Y) {
//                    return new Pixel(255, 255, 255, 255);
//                } else {
//                    return new Pixel(0, 0, 0, 255);
//                }
//            }
//        }).collect(Collectors.toStreamableImage());

        return input.stream()
                .apply(new SubstractFilter(side0))
                .apply(new SubstractFilter(side90))
                .apply(new SubstractFilter(side180))
                .apply(new SubstractFilter(side270))
                .apply(new SubstractFilter(edge0))
                .apply(new SubstractFilter(edge90))
                .apply(new SubstractFilter(edge180))
                .apply(new SubstractFilter(edge270))
                .collect(StreamableImage::new);

    }

    private class SumFilter extends Filter {

        private List<UnpackedImage> unpackedImageList = new ArrayList<>();

        public SumFilter(BufferedImage... bufferedImages) {
            for (BufferedImage bf : bufferedImages) {
                unpackedImageList.add(new UnpackedImage(bf));
            }
        }

        @Override
        public void apply(int x, int y) {
            int v = unpackedImageList.stream().map(unpackedImage -> unpackedImage.getPixel(x, y)[0]).reduce((v1, v2) -> v1 + v2).get();
            setPixel(x, y, new Pixel(v, v, v, 255));
        }
    }

    private class SubstractFilter extends ArithmeticFilter {

        public SubstractFilter(BufferedImage input) {
            super(input);
        }

        @Override
        protected Pixel calculate(Pixel x, Pixel y) {
            boolean X = x.getRed() == 255;
            boolean Y = y.getRed() == 255;
            if (X && !Y) {
                return new Pixel(255, 255, 255, 255);
            } else {
                return new Pixel(0, 0, 0, 255);
            }
        }
    }


}
