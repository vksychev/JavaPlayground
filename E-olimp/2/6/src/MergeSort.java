import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class MergeSort {


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

    static void swap(Data[] a, int i, int j) {
        Data t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static class Data implements Comparable<Data> {

        int first;
        int second;

        Data(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Data o) {
            if (first < o.first) {
                return -1;
            }
            if (first > o.first) {
                return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
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

