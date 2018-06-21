import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class QuickSort {

    static String printArray(int[] a,int size) {
        StringBuilder result = new StringBuilder();
        for (int i = 0 ; i < size;i++){
            result.append(a[i]).append(" ");
        }
        return result.toString();
    }

    static void swap(int[]a ,int  i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void quickSort(int[] a,int left, int right){
        if (left >= right) return;
        int idx = partition(a,left,right);
        quickSort(a,left,idx);
        quickSort(a,idx+1,right);
    }
    static int partition(int[]a , int l, int r){
        int p = a[l+(r-l+1)/2];
        int i = l ; int j = r;
        while (i<=j){
            while (a[i] < p) i++;
            while (a[j] > p) j--;
            if (i<=j) {
                swap(a,i++,j--);
            }
        }
        return j;
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        int[] a = new int[10];
        int size = 0;
        while (in.hasNext()) {
            int s = in.nextInt();
            a[size] = s;
            size++;
            if(size == a.length) a = Arrays.copyOf(a, a.length*2);
        }
        quickSort(a,0,size-1);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", false));
            bw.write(printArray(a,size));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
