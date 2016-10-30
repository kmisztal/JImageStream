package pl.uj.edu.JImageStream.api;

/**
 * Created by pPanek on 2016-10-28.
 */
public enum ColorChannel {
    RED {
        @Override
        public void process(int[] input, int[] output) {
            input[0] = output[0];
        }
    }, GREEN {
        @Override
        public void process(int[] input, int[] output) {
            input[1] = output[1];
        }
    }, BLUE {
        @Override
        public void process(int[] input, int[] output) {
            input[2] = output[2];
        }
    }, ALPHA {
        @Override
        public void process(int[] input, int[] output) {
            input[3] = output[3];
        }
    };

    public abstract void process(int[] input, int[] output);

}
