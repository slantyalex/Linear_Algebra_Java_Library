package decomposition;

import operations.*;
import structures.*;

public class EigenSolver {
    private static final int MAX_ITERATIONS = 500;
    private static final double CONVERGENCE_TOLERANCE = 1e-9;

    public static double[] eigenvalues(Matrix m){
        if(!m.isSquare()){
            throw new IllegalArgumentException("Must be a square Matrix.");
        }

        double[] eigenvalues = new double[m.rows()];;

        if(m.isTriangular()){
            eigenvalues = new double[m.rows()];
            for(int i = 0;i < m.rows(); i++){
                eigenvalues[i] = m.get(i,i);
            }
            return eigenvalues;
        }

        Matrix triangularAppx = runQrAlgorithm(m);
        
        for(int i = 0; i < m.rows(); i++){
            eigenvalues[i] = triangularAppx.get(i, i);
        }
        return eigenvalues;
    }

    private static Matrix runQrAlgorithm(Matrix A) {
        int n = A.rows();

        Matrix Acopy = A.copy(); 

        for(int iterate = 0; iterate < MAX_ITERATIONS; iterate++){
            // Gram-Schmidt QR Decomposition
            double[][] Q = new double[n][n];
            double[][] R = new double[n][n];

            for(int j = 0; j < n; j++){
                double[] v = new double[n];
                for (int i = 0; i < n; i++){
                    v[i] = Acopy.get(i, j);
                }
                for(int i = 0; i < j; i++){
                    double[] qI = new double[n];
                    for(int k = 0; k < n; k++){
                        qI[k] = Q[k][i];
                    }
                    double[] akColJ = new double[n];
                    for(int k = 0; k < n; k++){
                        akColJ[k] = Acopy.get(k, j);
                    }
                    Vector qIVec = new Vector(qI);
                    Vector akColJVec = new Vector(akColJ);
                    R[i][j] = VectorOps.dotProduct(qIVec, akColJVec);
                    
                    for(int k = 0; k < n; k++){
                        v[k] -= R[i][j] * qI[k];
                    }
                }

                Vector vVec = new Vector(v);
                double normValue = Norm.L2Norm(vVec); 
                R[j][j] = normValue;

                // Normalize to construct column j of Q (if yk what that means)
                for(int k = 0; k < n; k++){
                    Q[k][j] = (normValue > 1e-12) ? v[k] / normValue : 0;
                }
            }

            double[][] nextData = new double[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    for(int k = 0; k < n; k++){
                        nextData[i][j] += R[i][k] * Q[k][j];
                    }
                }
            }
            Matrix NextA = new Matrix(nextData);

            boolean converged = true;
            for(int i = 1; i < n; i++){
                for(int j = 0; j < i; j++){
                    if(Math.abs(NextA.get(i, j)) > CONVERGENCE_TOLERANCE){
                        converged = false;
                        break;
                    }
                }
                if(!converged){
                    break;
                }
            }

            Acopy = NextA;
            if(converged){
                break;
            }
        }
        return Acopy; 
    }

    public static void characteristicPoly(Matrix m){
        if(!m.isSquare()){
            throw new IllegalArgumentException("Must be a square Matrix.");
        }
        
    }

}
