package pl.edu.uj.JImageStream.tests.performance.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvCreator {

    private String fileName;
    private List<String> data;

    public CsvCreator(String fileName) {
        this.fileName = fileName;
        data = new ArrayList<String>();
        data.add("test;times;imageSize;time");
    }

    public void add(String test, String times, String imageSize, long time) {
        data.add(Stream.of(test, times, imageSize, String.valueOf(time)).collect(Collectors.joining(";")));
    }

    public void save(String dirPath) throws Exception {

        if (!Files.exists(Paths.get(dirPath))) {
            Files.createDirectories(Paths.get(dirPath));
        }

        PrintWriter printWriter = new PrintWriter(new File(dirPath + fileName), "UTF-8");
        data.forEach(printWriter::println);
        printWriter.close();
    }
}
