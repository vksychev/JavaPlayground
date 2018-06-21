import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class CountingSort {


    static String printArray(int[] a) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            result.append(a[i]).append(" ");
        }
        return result.toString();
    }

    static int findMax(int[] a) {
        int max = 0;
        for (int x : a) {
            max = Math.max(max, x);
        }
        return max;
    }

    public static void countingSort(int[] a) {
        int max = findMax(a);
        int[] count = new int[max + 1];
        for (int x : a) {
            count[x]++;
        }
        int pos = 0;
        for (int k = 0; k <= max; k++) {
            for (int i = 0; i < count[k]; i++) {
                a[pos++] = k;
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {

        int n = 0;
        int[] a = new int [n];
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            n = Integer.parseInt(in.readLine());
            a = new int[n];
            for (int i = 0; i < n; i++) {
                StringBuilder r = new StringBuilder();
                int k = in.read();
                while (k-48>=0 && k-57<=0){
                    r.append(k-48);
                    k = in.read();
                }
                a[i]= Integer.parseInt(r.toString());
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        countingSort(a);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", false));
            bw.write(printArray(a));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

