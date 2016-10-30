package pl.uj.edu.JImageStream.api;

/**
 * Created by pPanek on 2016-10-28.
 */
public enum ColorChannel {
    R {
        @Override
        public void process(int[] input, int[] output) {
            input[0] = output[0];
        }
    }, G {
        @Override
        public void process(int[] input, int[] output) {
            input[1] = output[1];
        }
    }, B {
        @Override
        public void process(int[] input, int[] output) {
            input[2] = output[2];
        }
    }, A {
        @Override
        public void process(int[] input, int[] output) {
            input[3] = output[3];
        }
    };

    public abstract void process(int[] input, int[] output);

}
