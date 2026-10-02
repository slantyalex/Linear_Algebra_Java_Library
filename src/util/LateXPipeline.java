package util;
import java.io.IOException;

public class LateXPipeline {

    public static void compile(String file) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(
                "pdflatex",
                "-interaction=nonstopmode",
                file
        );

        Process process = pb.start();
        process.waitFor();
    }
}