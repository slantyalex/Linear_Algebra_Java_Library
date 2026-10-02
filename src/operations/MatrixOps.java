package operations;

import structures.Matrix;

public class MatrixOps {
    public static Matrix add(Matrix m1, Matrix m2){
        if(m1.rows() != m2.rows() || m1.cols() != m2.cols()){
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }

        double[][] sum = new double[m1.rows()][m1.cols()];
        int i, j;
        for(i =0; i< m1.rows();i++){
            for(j = 0;j< m1.cols(); j++){
                sum[i][j] = m1.get(i,j) + m2.get(i,j);
            }
        }
        return new Matrix(sum)
        ;
    }

    public static Matrix scalarMult(Matrix m, double scalar){
        double[][] scaledMatrix = new double[m.rows()][m.cols()];
        int i,j;
        for(i = 0; i< m.rows(); i++){
            for(j = 0; j < m.cols(); j++){
                scaledMatrix[i][j] = m.get(i,j) * scalar;
            }
        }
        return new Matrix(scaledMatrix);
    }

    public static Matrix transpose(Matrix m){
        double[][] transposed = new double[m.cols()][m.rows()];
        int i,j;

        for(i = 0; i < m.rows();i++){
            for(j = 0; j< m.cols(); j++){
                transposed[j][i] = m.get(i, j);
            }
        }
        return new Matrix(transposed);
    }

    public static double det(Matrix m){
        if(m.rows() != m.cols()){
            throw new IllegalArgumentException("Matrix must be square.");
        }

        // Technically, the first entry as its triangular
        if(m.rows() == 1){
            return m.get(0,0);
        }
        double determinant = 0.0;
        int sign = 1;
        // if(m.rows() == 2){
        //     return m.get(0,0) * m.get(1,1) - m.get(0,1) * m.get(1,0);
        // }

        for(int i = 0; i < m.rows(); i++){
            double[][] minor = new double[m.rows()-1][m.cols()-1];
            for(int j = 1; j < m.rows(); j++){
                for(int k = 0, minorCol = 0; k < m.cols(); k++){
                    if(k != i){
                        minor[j-1][minorCol++] = m.get(j,k);
                    }
                }
            }

        determinant += sign * m.get(0,i) * det(new structures.Matrix(minor));
        sign *= -1;
        }
        return determinant;
    }

    public static boolean isInvertible(Matrix m){
        return det(m) != 0.0 || !m.isSquare(); 
    }

    public static Matrix adjugate(Matrix m){
        if(!m.isSquare()){
        throw new IllegalArgumentException("Matrix must be square.");
        }

        int n = m.rows();
        double[][] cofactors = new double[n][n];

        // Getting the cofactor matrix
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                double[][] minor = new double[n - 1][n - 1];
                for(int row = 0, minorRow = 0; row < n; row++){
                    if(row == i){
                        continue;
                    }
                    for(int col = 0, minorCol = 0; col < n; col++){
                        if(col == j){
                            continue;
                        }
                        minor[minorRow][minorCol++] = m.get(row, col);
                    }
                    minorRow++;
                }
                int sign = ((i + j) % 2 == 0) ? 1 : -1;
                cofactors[i][j] = sign * det(new structures.Matrix(minor));
            }
        }
        // Adjugate is the transpose of the cofactor matrix
        Matrix cofactorsMatrix = new Matrix(cofactors);
        Matrix adj = transpose(cofactorsMatrix);

        return adj;
    }

    public static Matrix inverse(Matrix m){
        if(!isInvertible(m)){
            throw new IllegalArgumentException("Matrix is not invertible.");
        }

        double determinant = det(m);
        Matrix adj = adjugate(m);
        return scalarMult(adj, 1.0 / determinant);
    }

    public static Matrix multiply(Matrix m1, Matrix m2){
        if(m1.cols() != m2.rows()){
            throw new IllegalArgumentException("Number of columns of the first matrix must equal the number of rows of the second matrix.");
        }

        double[][] product = new double[m1.rows()][m2.cols()];
        double sum = 0.0;

        for(int i = 0; i< m1.rows(); i++){
            for(int j = 0; j < m2.cols(); j++){
                for(int k = 0; k< m1.cols(); k++){
                    sum += m1.get(i,k) * m2.get(k,j);
                }
                product[i][j] = sum;
                sum = 0.0;
            }
        }
        return new Matrix(product);
    }
}
