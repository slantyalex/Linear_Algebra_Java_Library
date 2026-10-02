package util;

public class PdfOpener {
    public static void open(String file) throws Exception {
        new ProcessBuilder("explorer.exe", file).start();
    }
}
