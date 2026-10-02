package util;
import java.io.FileWriter;

public class LateXWriter {
    public static void write(String content, String file) throws Exception {
        FileWriter w = new FileWriter(file);
        w.write(content);
        w.close();
    }
}
