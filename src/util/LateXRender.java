package util;

import structures.*;

public class LateXRender {

    public static String vector(Vector v) {
        StringBuilder sb = new StringBuilder();
        int i;
        sb.append("\\left(\\begin{matrix}\n");

        for(i = 0; i < v.dimension(); i++) {
            sb.append(v.get(i));
            if(i != v.dimension() - 1){
                sb.append(" \\\\");
            }
            sb.append("\n");
        }

        sb.append("\\end{matrix}\\right)");

        return sb.toString();
    }

    public static String matrix(Matrix m) {
        StringBuilder sb = new StringBuilder();
        int i, j;
        sb.append("\\left(\\begin{matrix}\n");

        for(i = 0; i < m.rows(); i++) {
            for(j = 0; j < m.cols(); j++) {
                sb.append(m.get(i, j));
                if(j != m.cols() - 1) {
                    sb.append(" & ");
                }
            }
            sb.append(" \\\\ \n");
        }

        sb.append("\\end{matrix}\\right)");

        return sb.toString();
    }
}
