import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

public class CLAHEFilter extends Filter {

    public static double SLOPE = 6.0;
    public static int BLOCK_RADIUS = 63;
    public static int BINS = 255;

    public CLAHEFilter() {
    }

    public CLAHEFilter(double slope, int blockRadius, int bins) {
        this.SLOPE = slope;
        this.BLOCK_RADIUS = blockRadius;
        this.BINS = bins;
    }

    public void apply(int i, int i1) {
    }

    public int roundPos(float a) {
        return (int) (a + 0.5f);
    }

    public int[] createHistogram(int blockRadius, int bins, int blockXCenter, int blockYCenter, int[][] src, int w, int h) {
        int[] hist = new int[bins + 1];
        int xMin = Math.max(0, blockXCenter - blockRadius);
        int yMin = Math.max(0, blockYCenter - blockRadius);
        int xMax = Math.min(w, blockXCenter + blockRadius + 1);
        int yMax = Math.min(h, blockYCenter + blockRadius + 1);
        for (int y = yMin; y < yMax; ++y) {
            for (int x = xMin; x < xMax; ++x) {
                ++hist[roundPos(src[x][y] / 255.0f * bins)];
            }
        }
        return hist;
    }

    float[] createTransfer(int[] hist, int limit) {
        int[] cdfs = new int[hist.length];
        clipHistogram(hist, cdfs, limit);
        int hMin = hist.length - 1;
        for (int i = 0; i < hMin; ++i)
            if (cdfs[i] != 0) hMin = i;
        int cdf = 0;
        for (int i = hMin; i < hist.length; ++i) {
            cdf += cdfs[i];
            cdfs[i] = cdf;
        }
        int cdfMin = cdfs[hMin];
        int cdfMax = cdfs[hist.length - 1];
        float[] transfer = new float[hist.length];
        for (int i = 0; i < transfer.length; ++i)
            transfer[i] = (cdfs[i] - cdfMin) / (float) (cdfMax - cdfMin);
        return transfer;
    }

    public void clipHistogram(int[] hist, int[] clippedHist, int limit) {
        System.arraycopy(hist, 0, clippedHist, 0, hist.length);
        int clippedEntries = 0, clippedEntriesBefore;
        do {
            clippedEntriesBefore = clippedEntries;
            clippedEntries = 0;
            for (int i = 0; i < hist.length; ++i) {
                final int d = clippedHist[i] - limit;
                if (d > 0) {
                    clippedEntries += d;
                    clippedHist[i] = limit;
                }
            }
            int d = clippedEntries / (hist.length);
            int m = clippedEntries % (hist.length);
            for (int i = 0; i < hist.length; ++i)
                clippedHist[i] += d;
            if (m != 0) {
                final int s = (hist.length - 1) / m;
                for (int i = s / 2; i < hist.length; i += s)
                    ++clippedHist[i];
            }
        } while (clippedEntries != clippedEntriesBefore);
    }

    @Override
    public void setUp() {
        int width = getSourceWidth();
        int height = getSourceHeight();
        int[][] src = new int[width][height];
        int[][] dst = new int[width][height];
        for (int y = 0, pix = 0; y < height; y++) {
            for (int x = 0; x < width; x++, pix++) {
                Pixel pixel = getPixel(x, y);
                double r = (pixel.getRed() * 0.21);
                double g = (pixel.getGreen()) * 0.72;
                double b = (pixel.getBlue()) * 0.07;
                src[x][y] = (int) (r + g + b + 0.5);
            }
        }

        int[] hist;
        float[] tl;
        float[] tr;
        float[] bl;
        float[] br;

        int blockSize = 2 * BLOCK_RADIUS + 1;
        int limit = (int) (SLOPE * blockSize * blockSize / BINS + 0.5f);

     /* div */
        int nc = width / blockSize;
        int nr = height / blockSize;

     /* % */
        int cm = width - nc * blockSize; //column modulus
        int[] cs;  //column sections
        switch (cm) {
            case 0:
                cs = new int[nc];
                for (int i = 0; i < nc; ++i)
                    cs[i] = i * blockSize + BLOCK_RADIUS + 1;
                break;
            case 1:
                cs = new int[nc + 1];
                for (int i = 0; i < nc; ++i)
                    cs[i] = i * blockSize + BLOCK_RADIUS + 1;
                cs[nc] = width - BLOCK_RADIUS - 1;
                break;
            default:
                cs = new int[nc + 2];
                cs[0] = BLOCK_RADIUS + 1;
                for (int i = 0; i < nc; ++i)
                    cs[i + 1] = i * blockSize + BLOCK_RADIUS + 1 + cm / 2;
                cs[nc + 1] = width - BLOCK_RADIUS - 1;
        }

        int rm = height - nr * blockSize; // row modulus
        int[] rs;  // row sections
        switch (rm) {
            case 0:
                rs = new int[nr];
                for (int i = 0; i < nr; ++i)
                    rs[i] = i * blockSize + BLOCK_RADIUS + 1;
                break;
            case 1:
                rs = new int[nr + 1];
                for (int i = 0; i < nr; ++i)
                    rs[i] = i * blockSize + BLOCK_RADIUS + 1;
                rs[nr] = height - BLOCK_RADIUS - 1;
                break;
            default:
                rs = new int[nr + 2];
                rs[0] = BLOCK_RADIUS + 1;
                for (int i = 0; i < nr; ++i)
                    rs[i + 1] = i * blockSize + BLOCK_RADIUS + 1 + rm / 2;
                rs[nr + 1] = height - BLOCK_RADIUS - 1;
        }

        for (int r = 0; r <= rs.length; ++r) {
            int r0 = Math.max(0, r - 1);
            int r1 = Math.min(rs.length - 1, r);
            int dr = rs[r1] - rs[r0];

            hist = createHistogram(BLOCK_RADIUS, BINS, cs[0], rs[r0], src, width, height);
            tr = createTransfer(hist, limit);
            if (r0 == r1)
                br = tr;
            else {
                hist = createHistogram(BLOCK_RADIUS, BINS, cs[0], rs[r1], src, width, height);
                br = createTransfer(hist, limit);
            }

            int yMin = (r == 0 ? 0 : rs[r0]);
            int yMax = (r < rs.length ? rs[r1] : height - 1);

            for (int c = 0; c <= cs.length; ++c) {
                int c0 = Math.max(0, c - 1);
                int c1 = Math.min(cs.length - 1, c);
                int dc = cs[c1] - cs[c0];

                tl = tr;
                bl = br;

                if (c0 != c1) {
                    hist = createHistogram(BLOCK_RADIUS, BINS, cs[c1], rs[r0], src, width, height);
                    tr = createTransfer(hist, limit);
                    if (r0 == r1)
                        br = tr;
                    else {
                        hist = createHistogram(BLOCK_RADIUS, BINS, cs[c1], rs[r1], src, width, height);
                        br = createTransfer(hist, limit);
                    }
                }

                int xMin = (c == 0 ? 0 : cs[c0]);
                int xMax = (c < cs.length ? cs[c1] : width - 1);

                for (int y = yMin; y < yMax; ++y) {
                    float wy = (float) (rs[r1] - y) / dr;

                    for (int x = xMin; x < xMax; ++x) {
                        float wx = (float) (cs[c1] - x) / dc;
                        int v = roundPos(src[x][y] / 255.0f * BINS);

                        float t00 = tl[v];
                        float t01 = tr[v];
                        float t10 = bl[v];
                        float t11 = br[v];
                        float t0, t1;
                        if (c0 == c1) {
                            t0 = t00;
                            t1 = t10;
                        } else {
                            t0 = wx * t00 + (1.0f - wx) * t01;
                            t1 = wx * t10 + (1.0f - wx) * t11;
                        }

                        float t;
                        if (r0 == r1)
                            t = t0;
                        else
                            t = wy * t0 + (1.0f - wy) * t1;

                        dst[x][y] = Math.max(0, Math.min(255, roundPos(t * 255.0f)));
                    }
                }

            }

        }
        for (int y = 0; y < height; y++) {
            int loc = y * width;
            for (int x = 0; x < width; ++x, loc++) {
                float a;
                Pixel pixel = getPixel(x, y);
                a = src[x][y] == 0 ? 0.0f : (float) dst[x][y] / src[x][y];
                int r = Math.max(0, Math.min(255, (int) (a * pixel.getRed() + 0.5)));
                int g = Math.max(0, Math.min(255, (int) (a * pixel.getGreen() + 0.5)));
                int b = Math.max(0, Math.min(255, (int) (a * pixel.getBlue() + 0.5)));
                setPixel(x, y, new Pixel(r, g, b, 255));
            }

        }
    }
}
