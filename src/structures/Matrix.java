package structures;

public class Matrix {
    private final double[][] data;

    public Matrix(double[][] data) {
        if (data.length == 0) {
            throw new IllegalArgumentException("Matrix cannot have zero rows.");
        }
        long cols = data[0].length;
        for (double[] row : data) {
            if(row.length != cols){
                throw new IllegalArgumentException("All rows must have the same number of columns.");
            }
        }
        this.data = data;
    }

    public double[][] getMatrix() {
        return data;
    }

    public int rows(){
        return data.length;
    }

    public int cols(){
        return data[0].length;
    }

    public long dimension(){
        return rows() * cols();
    }
    
    public double get(int row, int col){
        if(row < 0 || row >= rows() || col < 0 || col >= cols()){
            throw new IndexOutOfBoundsException("Row or column index out of bounds (index from zero)");
        }
        return data[row][col];
    }

    public int NonZeroElements(){
        int count = 0;
        int i;
        int j;
        for(i=0; i < data.length;i++){
            for(j = 0; j < data[i].length; j++){
                if(data[i][j] != 0.0){
                    count++;
                }
            }
        }
        return count;
    }

    public Matrix copy(){
        double[][] copy = new double[data.length][data[0].length];
        int i;
        int j;
        for(i = 0; i < data.length; i++){
            for(j = 0; j < data[i].length; j++){
                copy[i][j] = data[i][j];
            }
        }
        return new Matrix(copy);
    }

    public boolean isTriangular(){
        int i;
        int j;
        boolean upper = true;
        boolean lower = true;

        for(i = 0; i < data.length; i++){
            for(j = 0;j<data[i].length;j++){
                if(data[i][j] != 0.0){
                    if(i < j){
                        lower = false;
                    }
                    else if(i > j){
                        upper = false;
                    }
                }
            }
        }
        return upper || lower;
    }

    public boolean isSquare(){
        return rows() == cols();
    }

    public static Matrix identity(int size){
        double[][] identity = new double[size][size];
        int i;
        
        for(i = 0; i < size; i++){
            identity[i][i] = 1.0;
        }
        return new Matrix(identity);
    }
}
