import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class QuickSortKiller {


    static String printArray(int[] a) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            result.append(a[i]).append(" ");
        }
        return result.toString();
    }



    public static void main(String[] args) throws FileNotFoundException {


        int n = 0;

        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            n = Integer.parseInt(in.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        int[] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = i +1;
        }



        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", false));
            bw.write(printArray(a));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


