package pl.edu.uj.JImageStream.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.ColorChannel;
import pl.edu.uj.JImageStream.model.Pixel;

public class Histogram extends Filter{

    private int[][] histogram = new int[4][256]; // 0=R, 1=G, 2=B, 3=A
    private int[] blackAndWhiteHistogram = new int[256];
    private boolean[] channel = new boolean[4]; // 0=R, 1=G, 2=B, 3=A
    private ColorChannel[] channels;
    private final int TOPMARGIN = 0;
    private BufferedImage image = null;
    private BufferedImage output = null;
    private int outputWidth;
    private int outputHeight;
    private boolean readyToApply;
    private boolean blackAndWhite;

    public Histogram(BufferedImage image, ColorChannel ... channels){
        readyToApply = false;
        blackAndWhite = false;
        this.image = image;
        this.channels = channels;
    }

    private void init(){
        readyToApply = true;
        for(int j = 0 ; j < 256 ; ++j){
            blackAndWhiteHistogram[ j ] = 0;
            for(int i = 0 ; i < 4 ; ++i){
                histogram[ i ][ j ] = 0;
            }
        }
        for(int i = 0 ; i < 4 ; ++i){
            channel[ i ] = false;
        }

        output = new BufferedImage(getSourceWidth(), getSourceHeight(), BufferedImage.TYPE_INT_ARGB);
        outputHeight = output.getHeight();
        outputWidth = output.getWidth();

        if( channels.length > 0 ){
            setChannels(channels);
        } else {
            blackAndWhite = true;
        }
        if(( outputWidth >= 256 )&&( image != null )){
            calculateHistogram();
            setScale();
            drawHistogram();
        } else {
            Logger logger = LogManager.getLogger(this.getClass());
            logger.warn("Streamable Image Width should be at least 256 pixels");
        }
    }

    private void calculateHistogram(){
        for(int i = 0 ; i < image.getWidth() ; ++i){
            for(int j = 0 ; j < image.getHeight() ; ++j){
                Color color = new Color(image.getRGB(i,j), true);
                histogram[3][color.getAlpha()]++;
                if( color.getAlpha() != 0 ){
                    if( !blackAndWhite ){
                        histogram[0][color.getRed()]++;
                        histogram[1][color.getGreen()]++;
                        histogram[2][color.getBlue()]++;
                    } else {
                        blackAndWhiteHistogram[color.getRed()]++;
                        blackAndWhiteHistogram[color.getGreen()]++;
                        blackAndWhiteHistogram[color.getBlue()]++;
                    }
                }
            }
        }

        if( blackAndWhite ){
            for(int i = 0 ; i < 256 ; ++i){
                blackAndWhiteHistogram[i] /= 3;
            }
        } else {
            if( !channel[0] ){
                for(int i = 0 ; i < 256 ; ++i){
                    histogram[0][i] = 0;
                }
            }
            if( !channel[1] ){
                for(int i = 0 ; i < 256 ; ++i){
                    histogram[1][i] = 0;
                }
            }
            if( !channel[2] ){
                for(int i = 0 ; i < 256 ; ++i){
                    histogram[2][i] = 0;
                }
            }
        }
        if( !channel[3] || isAlphaIdentical()){
            for(int i = 0 ; i < 256 ; ++i){
                histogram[3][i] = 0;
            }
        }
    }

    private void setScale(){
        double yScale;
        int maxV;
        if( blackAndWhite ){
            maxV = blackAndWhiteHistogram[ 0 ];
            for(int i = 1 ; i < 256 ; ++i){
                if( blackAndWhiteHistogram[ i ] > maxV ){
                    maxV = blackAndWhiteHistogram[ i ];
                }
            }
            yScale = maxV;
            yScale /= (outputHeight - 2*TOPMARGIN);
            if( yScale != 0 ){
                for(int i = 0 ; i < 256 ; ++i){
                    blackAndWhiteHistogram[ i ] /= yScale;
                }
            }

        } else {

            maxV = 0;
            for (int k = 0; k < 3; ++k) {
                if (channel[k]) {
                    for (int i = 0; i < 256; ++i) {
                        if (histogram[k][i] > maxV) {
                            maxV = histogram[k][i];
                        }
                    }
                }
            }
            yScale = maxV;
            yScale /= (outputHeight - 2 * TOPMARGIN);
            if( yScale != 0 ){
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 256; ++j) {
                        histogram[i][j] /= yScale;
                    }
                }
            }
        }

        maxV = histogram[3][0];
        for (int i = 1; i < 256; ++i) {
            if (histogram[3][i] > maxV) {
                maxV = histogram[3][i];
            }
        }
        double alphaScale = maxV;
        alphaScale /= (outputHeight - 2 * TOPMARGIN);
        if( alphaScale != 0 ){
            for (int i = 0; i < 256; ++i) {
                histogram[3][i] /= alphaScale;
            }
        }
    }


    private void drawHistogram(){

        int barWidth = outputWidth / 256;
        int leftmargin = outputWidth - 256*barWidth;
        leftmargin /= 2;
        int rightmargin = leftmargin;
        if(( leftmargin * 2 ) != ( outputWidth - 256*barWidth )){
            leftmargin++;
        }

        int index = 0;
        for(int i = leftmargin ; i < outputWidth - rightmargin ; ++i){
            for(int j = TOPMARGIN ; j < outputHeight - TOPMARGIN ; ++j){
                int color = 0xFF000000;
                if( blackAndWhite ){
                    if( blackAndWhiteHistogram[index/barWidth] >= outputHeight - TOPMARGIN - j ){
                        color += 0xb0b0b0;
                    }
                } else {
                    if( histogram[0][index/barWidth] >= outputHeight - TOPMARGIN - j ){
                        color += 0xc80000;
                    }
                    if( histogram[1][index/barWidth] >= outputHeight - TOPMARGIN - j ){
                        color += 0x00c800;
                    }
                    if( histogram[2][index/barWidth] >= outputHeight - TOPMARGIN - j ){
                        color += 0x0000c8;
                    }
                    if( color == 0xFFc8c8c8 ){
                        color = 0xFFb0b0b0;
                    }
                    if( histogram[3][index/barWidth] >= outputHeight - TOPMARGIN - j ){
                        color += 0x323232;
                    }
                }
                output.setRGB(i, j, color);
            }
            index++;
        }
    }

    private void setChannels(ColorChannel[] channels){
        for(ColorChannel c : channels){
            if( c.equals(ColorChannel.RED) ){
                channel[0] = true;
            }
            if( c.equals(ColorChannel.GREEN) ){
                channel[1] = true;
            }
            if( c.equals(ColorChannel.BLUE) ){
                channel[2] = true;
            }
            if( c.equals(ColorChannel.ALPHA) ){
                channel[3] = true;
            }
        }
    }

    private boolean isAlphaIdentical(){
        int moreThanZero = 0;
        for(int i = 0 ; i < 256 ; ++i){
            if( histogram[ 3 ][ i ] > 0 ){
                moreThanZero++;
            }
        }
        return moreThanZero <= 1;
    }


    @Override
    public void apply(int x, int y) {
        if( !readyToApply ){
            init();
        }
        Color color = new Color(output.getRGB(x,y), true);
        setPixel(x, y, new Pixel(x, y, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
    }
}
