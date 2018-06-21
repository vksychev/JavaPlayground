import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SelectionSort {
    static int selectionSort(int[] a) {
        int s = 0;
        int n = a.length;
        int result = 0;
        for (int i = 0; i< n - 1;i++){
            int min = i;
            for (int j = i+1 ; j < n;j++){
                if (a[j] < a[min]) min = j;
            }

            int c = a[i];
            a[i] = a[min];
            a[min] = c;
            if (min == s && min!=i){ s =i;result ++;}
            else if (i == s && min!=i){ s =min;result ++;}

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
            bw.write(String.valueOf(selectionSort(m)));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
