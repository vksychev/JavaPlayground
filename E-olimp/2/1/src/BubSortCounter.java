import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class BubSortCounter {
    static int bubleSort(int[] a) {
        int i = 0;
        int n = a.length;
        boolean wasSwap=true;
        int result=0;
        while (wasSwap){
            wasSwap = false;
            for (int j = 0; j <= n-i-2;j++){
                if(a[j]>a[j+1]){
                    int c = a[j];
                    a[j] = a[j+1];
                    a[j+1] = c;
                    result++;
                    wasSwap=true;
                }
            }
            i++;
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        int k = in.nextInt();
        int[] m = new int[k];

        for (int i = 0;i<k;i++){
            m[i] = in.nextInt();
            System.out.println(m[i]);
        }


        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", false));
            bw.write(String.valueOf(bubleSort(m)));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
