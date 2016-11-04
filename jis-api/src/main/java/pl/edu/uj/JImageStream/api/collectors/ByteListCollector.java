package pl.edu.uj.JImageStream.api.collectors;

import pl.edu.uj.JImageStream.api.Collector;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.List;


public class ByteListCollector implements Collector<List<Byte>> {
    @Override
    public List<Byte> collect(BufferedImage bufferedImage) {
        byte[] data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        List<Byte> list = new ArrayList<>();
        for (byte d : data) {
            list.add(d);
        }
        return list;
    }
}
