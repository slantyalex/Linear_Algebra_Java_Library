import decomposition.*;
import structures.*;
import util.*;

public class Main{
    public static void main(String[] args) throws Exception {

        //build latex string
        Vector v1 = new Vector(new double[] {2, -1, 3});
        Vector v2 = new Vector(new double[]{5,7,-4});
        Matrix m1 = new Matrix(new double[][] {
                {2, -1, 0},
                {-1, 2, -1},
                {0, -1, 2}
        });

        Matrix m2 = Matrix.identity(3);
        Vector eigenvals = new Vector(EigenSolver.eigenvalues(m1));

        // Matrix m3 = new Matrix(MatrixOps.multiply(m1,m2).getMatrix());
        // Vector v3 = new Vector(VectorOps.crossProduct(v1, v2));

        // System.out.println("m1 eigenvalues: " + eigenvals);
        // System.out.println("Matrix m1: " + MatrixOps.det(m1));
        // System.out.println("Matrix m2: " + MatrixOps.det(m2));
        // System.out.println("Matrix product: " + MatrixOps.multiply(m1, m2));
        String latex = LateXRender.vector(eigenvals);
        // String latex2 = LateXRender.matrix(m3);


        // //Build document
        LateXDocument doc = new LateXDocument();
        doc.add(latex);
        // doc.add(latex2);

        String fullTex = doc.build();

        // //Write .tex
        LateXWriter.write(fullTex, "output.tex");

        // //Compile → PDF
        LateXPipeline.compile("output.tex");

        // //Open result
        PdfOpener.open("output.pdf");
    }
}
