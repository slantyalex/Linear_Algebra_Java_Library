package structures;
// import java.util.Arrays;
// import java.util.Objects;

public class Vector {
    private final double[] data;
    // private static final double EPSILON = 1e-10;
    

    public Vector(double[] data){
        this.data = data; 
    }

    public double[] getVector(){
        return data;
    }

    public long dimension(){
        return data.length;
    }

    public double get(long index){
        if(index < 0 || index >= data.length){
            throw new IndexOutOfBoundsException("Does not exist (index from zero)");
        }
        return data[(int)index];
    }

    public long NonZeroElements(){
        long count = 0;
        int i;
        for(i=0; i < data.length;i++){
            while(data[i] != 0.0){
                count++;
                break;
            }
        }
        return count;
    }

    // public void printNonZeroElements(){
    //     int i;
    //     for(i=0; i < data.length;i++){
    //         while(data[i] != 0.0){
    //             System.out.println("Element " + i + ": " + data[i]);
    //             break;
    //         }
    //     }
    // }

    // public void printVector(){
    //     int i;
    //     System.out.print("[");
    //     for(i = 0;i < data.length - 1; i++){
    //         System.out.print(data[i] + " ");
    //     }
    //     System.out.print(data[data.length - 1]);
    //     System.out.println("]");
    // }

    public double[] copy(){
        double[] copy = new double[data.length];
        int i;
        for(i = 0; i < data.length; i++){
            copy[i] = data[i];
        }
        return copy;
    }

    public double[] resize(int scale){
        int scaledLength = data.length * scale;
        double[] resized = new double[scaledLength];
        int i;
        for(i = 0;i < scaledLength;i++){
           while(i < data.length){
                resized[i] = data[i];
                break;
            }
            while(i >= data.length){
                resized[i] = 0.0;
                break;
            }
        }
        return resized;
    }
}
