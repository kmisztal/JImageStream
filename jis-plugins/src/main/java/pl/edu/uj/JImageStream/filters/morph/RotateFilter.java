package pl.edu.uj.JImageStream.filters.morph;
import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;

public class RotateFilter extends Filter{
    private double alfa;
    private int xf;
    private int yf;

    public RotateFilter(double alfa){
        this.alfa = Math.toRadians(alfa);
    }

    @Override
    public void apply(int x, int y) {
        calculateNewCordinates(x,y);
        if(isPixelIndexOnEdge()){
            setPixel(x,y,getPixel(xf,yf));
        } else{
            setPixel(x,y,new Pixel(0,0,0,getPixel(x,y).getAlpha()));
        }
    }

    private void calculateNewCordinates(int x, int y) {
        double s = Math.sin(alfa);
        double c = Math.cos(alfa);
        int xs = (int) (Math.floor(getSourceWidth()/2));
        int ys = (int) (Math.floor(getSourceHeight()/2));
        xf = (int) ((x - xs) * c + (y - ys) * s + xs);
        yf = (int) ((y - ys) * c - (x - xs) * s + ys);
    }

    private boolean isPixelIndexOnEdge() {
        return xf >= 0 && xf < getSourceWidth() && yf >= 0 && yf < getSourceHeight();
    }


}
