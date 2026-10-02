package operations;

import structures.Vector;

public class VectorOps {
    
    public static double dotProduct(Vector v1, Vector v2){
        if(v1.dimension() != v2.dimension()){
            throw new IllegalArgumentException("Vectors do not have the same dimension.");
        }
        double sum = 0.0;
        double product = 0.0;
        long size = v1.dimension();
        long i;

        for(i = 0; i< size; i++){
            product = v1.get(i) * v2.get(i);
            sum += product;
        }
        return sum;
    }

    public static double[] scalarMult(Vector v, double scalar){
        long size = v.dimension();
        double[] scaledvector = new double[(int)size];
        long i; 
        for(i = 0; i < size; i++){
            scaledvector[(int) i] = v.get(i) * scalar;
        }
        return scaledvector;
    }

    public static double[] add(Vector v1, Vector v2){
        if(v1.dimension() != v2.dimension()){
            throw new IllegalArgumentException("Vectors do not have the same dimension.");
        }
        long size = v1.dimension();
        double[] sum = new double[(int)size];
        long i;
        for(i = 0; i < size; i++){
            sum[(int) i] = v1.get(i) + v2.get(i);
        }
        return sum;
    }

    public static double[] subtract(Vector v1, Vector v2){
        if(v1.dimension() != v2.dimension()){
            throw new IllegalArgumentException("Vectors do not have the same dimension.");
        }
        long size = v1.dimension();
        double[] difference = new double[(int)size];
        long i;
        for(i = 0; i < size; i++){
            difference[(int) i] = v1.get(i) - v2.get(i);
        }
        return difference;
    }

    public static double cosineSimilarity(Vector v1, Vector v2){
        if(v1.dimension() != v2.dimension()){
            throw new IllegalArgumentException("Vectors do not have the same dimension.");
        }
        
        double dotproduct = dotProduct(v1, v2);
        double v1norm = Norm.L2Norm(v1);
        double v2norm = Norm.L2Norm(v2);

        if(v1norm == 0.0 || v2norm == 0.0){
            throw new ArithmeticException("Cannot divide by zero (Check your norms).");
        }

        return dotproduct / (v1norm * v2norm);
    }

    public static double CosAngle(Vector v1, Vector v2){
        double cosSim = cosineSimilarity(v1, v2);
        return Math.acos(cosSim);
    }

    public static double inDegrees(double radians){
        return Math.toDegrees(radians);
    }
    
    public static double inRadians(double degrees){
        return Math.toRadians(degrees);
    }

    public boolean in(double value, Vector v){
        int i;
        for(i = 0; i< v.getVector().length; i++){
            if(v.getVector()[i] == value){
                return true;
            }
        }
        return false;
    }

    public static double[] crossProduct(Vector v1, Vector v2){
        if(v1.dimension() != 3 || v2.dimension() != 3){
            throw new IllegalArgumentException("3-D vectors only.");
        }
        double[] cross = new double[3];

        double i = v1.get(1) * v2.get(2) - v1.get(2) * v2.get(1);
        double j = v1.get(2) * v2.get(0) - v1.get(0) * v2.get(2);
        double k = v1.get(0) * v2.get(1) - v1.get(1) * v2.get(0); 
        
        cross[0] = i;
        cross[1] = j;
        cross[2] = k;
        return cross;
    }
}
