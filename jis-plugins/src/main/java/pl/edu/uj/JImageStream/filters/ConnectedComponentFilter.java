package pl.edu.uj.JImageStream.filters;

import pl.edu.uj.JImageStream.api.core.Filter;
import pl.edu.uj.JImageStream.model.Pixel;
import java.util.ArrayList;
import java.util.List;

public class ConnectedComponentFilter extends Filter {

    private boolean isLabeled;
    private int currLabelCount;
    private int outputLabel;
    private Label[][] labels;
    private List<Integer> labelValues;

    public ConnectedComponentFilter(int x){
        super();
        outputLabel = x;
    }

    @Override
    public void apply(int x, int y) {
        if(!isLabeled) {
            labeling();
            labelValues = labelsUniqueValues();
            checkOutputIsOnBounds();
        }

        if(labels[x][y].value != labelValues.get(outputLabel)) {
            setPixel(x, y, new Pixel(255, 255, 255, 255));
        }
    }

    private void checkOutputIsOnBounds(){
        if(outputLabel >= labelValues.size()) {
            try {
                //Is that good or change it?
                throw new ArrayIndexOutOfBoundsException("Picture has only " + labelValues.size() + " objects with background");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void labeling(){
        setUpLabels();
        firstRun();
        secondRun();
        isLabeled = true;
    }

    private void setUpLabels(){
        labels = new Label[getSourceWidth()][getSourceHeight()];
        for(int i = 0; i < getSourceWidth(); i++) {
            for(int j = 0; j < getSourceHeight(); j++) {
                labels[i][j] = new Label(0, null);
            }
        }
    }

    private void firstRun(){
        for(int j = 0; j < getSourceHeight(); j++) {
            for(int i = 0; i < getSourceWidth(); i++) {
                if(!isBackground(getPixel(i, j))) {
                    Label neighboursLabel = getNeighboursMinLabel(i, j);
                    labels[i][j] = neighboursLabel;
                }
            }
        }
    }

    private void secondRun(){
        for(int j = 0; j < getSourceHeight(); j++) {
            for(int i = 0; i < getSourceWidth(); i++) {
                if(isNotBackgroundLabel(i, j)) {
                    labels[i][j].value = labels[i][j].getRoot().value;
                }
            }
        }
    }

    private boolean isBackground(Pixel pixel){
        return inBounds(pixel.getAlpha(), 255, 0) && inBounds(pixel.getRed(), 255, 0) &&
                inBounds(pixel.getGreen(), 255, 0) && inBounds(pixel.getBlue(), 255, 0);
    }

    private boolean inBounds(int src, int value, int epsilon){
        return Math.abs(src - value) <= epsilon;
    }

    private void setNeighboursLabelsParent(Label parent, int x, int y) {
        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                if(!(i == x && j ==y) && isOnBounds(i ,j) && isNotBackgroundLabel(i, j)) {
                    if (labels[i][j].value != parent.value) {
                        labels[i][j].parent = parent;
                    }
                }
            }
        }
    }

    private List<Label> getNeighboursLabels(int x, int y){
        List<Label> neighboursLabels = new ArrayList<>();
        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                if(isOnBounds(i, j) && isNotBackgroundLabel(i, j)) {
                    neighboursLabels.add(labels[i][j]);
                }
            }
        }
        return neighboursLabels;
    }

    private Label getNeighboursMinLabel(int x, int y) {
        List<Label> neighboursLabels = getNeighboursLabels(x, y);

        if(isNoNeighboursLabels(neighboursLabels)) {
            currLabelCount++;
            return new Label(currLabelCount, null);
        } else {
            Label value = getLowestNeighboursLabel(neighboursLabels);
            setNeighboursLabelsParent(value, x, y);
            return value;
        }
    }

    private boolean isOnBounds(int x, int y) {
        return x >= 0 && x < getSourceWidth() && y >= 0 && y < getSourceHeight();
    }

    private boolean isNotBackgroundLabel(int x, int y) {
        return labels[x][y].value != 0;
    }

    private boolean isNoNeighboursLabels(List<Label> neighboursLabels){
        return neighboursLabels.size() == 0;
    }

    private Label getLowestNeighboursLabel(List<Label> neighboursLabels) {
        return neighboursLabels.stream().min((o1, o2) -> o1.value - o2.value).get();
    }

    private List<Integer> labelsUniqueValues(){
        List<Integer> values = new ArrayList<>();
        for(int i = 0; i < getSourceWidth(); i++) {
            for(int j = 0; j < getSourceHeight(); j++) {
                if(!values.contains(labels[i][j].value)){
                    values.add(labels[i][j].value);
                }
            }
        }
        return values;
    }

    private class Label{
        Label parent;
        int value;

        Label(int value, Label parent){
            this.value = value;
            this.parent = parent;
        }

        Label getRoot(){
            Label tmp = this;
            while(tmp.parent != null){
                tmp = tmp.parent;
            }
            return tmp;
        }
    }
}
