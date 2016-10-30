package pl.uj.edu.JImageStream.api;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;


public class ByteListCollector implements Collector<List<Byte>> {
    @Override
    public List<Byte> collect(BufferedImage bufferedImage) {
        WritableRaster writableRaster = bufferedImage.getRaster();
        DataBufferByte dataBufferByte = (DataBufferByte) writableRaster.getDataBuffer();
        byte[] data = dataBufferByte.getData();
        List<Byte> list = new ArrayList<>();
        for (byte d : data) {
            list.add(d);
        }
        return list;
    }
}
