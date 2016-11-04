package pl.edu.uj.JImageStream.model;

/**
 * Created by pPanek on 2016-10-28.
 */
public enum ColorChannel {
    RED {
        @Override
        public void process(int[] colorsToBeOverwritten, int[] desiredColors) {
            colorsToBeOverwritten[0] = desiredColors[0];
        }
    }, GREEN {
        @Override
        public void process(int[] colorsToBeOverwritten, int[] desiredColors) {
            colorsToBeOverwritten[1] = desiredColors[1];
        }
    }, BLUE {
        @Override
        public void process(int[] colorsToBeOverwritten, int[] desiredColors) {
            colorsToBeOverwritten[2] = desiredColors[2];
        }
    }, ALPHA {
        @Override
        public void process(int[] colorsToBeOverwritten, int[] desiredColors) {
            colorsToBeOverwritten[3] = desiredColors[3];
        }
    };

    public abstract void process(int[] colorsToBeOverwritten, int[] desiredColors);

}
