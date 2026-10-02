package operations;

import structures.*;

public class Norm {
    public static double L1Norm(Vector v){
        double sum = 0;
        int i;
        for(i = 0; i < v.dimension();i++){
            sum += Math.abs(v.get(i));
        }
        return sum;
    }

    public static double L2Norm(Vector v){
        double sum = 0;
        int i;

        for(i= 0; i< v.dimension();i++){
            sum += Math.pow(Math.abs(v.get(i)), 2);
        }

        return Math.sqrt(sum);
    }

    public static double InfNorm(Vector v){
        double max = 0;
        int i;

        for(i = 0; i< v.dimension(); i++){
            if(Math.abs(v.get(i)) > max){
                max = Math.abs(v.get(i));
            }
        }
        return max;
    }

    public static double InfNorm(Matrix m){
        double max = 0.0;
        double sum = 0.0;
        
        for(double[] row: m.getMatrix()){
           for(double val: row){
               sum += Math.abs(val);
           }
           if(sum > max){
               max = sum;
           }
           sum = 0.0;
        }
        return max;
    }

    public static Vector L2Normalize(Vector v){
        double norm = L2Norm(v);
        if(norm == 0.0){
            throw new IllegalArgumentException("Division by zero: Cannot normalize a zero vector.");
        }
        return new Vector(VectorOps.scalarMult(v, 1.0 / norm));
    }

}
