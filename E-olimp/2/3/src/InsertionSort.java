import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class InsertionSort {
    static void insertionSortIteration(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                int c = a[j];
                a[j] = a[j - 1];
                a[j - 1] = c;
            }
        }
    }

    static String printArray(int[] a) {
        StringBuilder result = new StringBuilder();
        for (int c : a) {
            result.append(c).append(" ");
        }
        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        int k = in.nextInt();
        int[] m = new int[k];

        for (int i = 0; i < k; i++) {
            m[i] = in.nextInt();
            System.out.println(m[i]);
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", false));
            int n = m.length;
            boolean swapped = false;
            for (int i = 1; i < n; i++) {
                for (int j = i; j > 0 && m[j] < m[j - 1]; j--) {
                    int c = m[j];
                    m[j] = m[j - 1];
                    m[j - 1] = c;
                    swapped = true;
                }
                if (swapped) {
                    bw.write(String.valueOf(printArray(m)));
                    bw.write(String.valueOf("\n"));
                    swapped = false;
                }
            }


            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
