import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class HeapSort {

    static String printArray(ArrayPriorityQueue<Integer> a) {
        StringBuilder result = new StringBuilder();
        while (!a.isEmpty()){
            result.append(a.extractMin()).append(" ");
        }
        return result.toString();
    }

    public static class ArrayPriorityQueue<Key extends Comparable<Key>> {
        private  int DEFAULT_CAPACITY = 10;
        private Key[] elementData;
        private int size;

        public ArrayPriorityQueue() {
            this.elementData =(Key[]) new Comparable[DEFAULT_CAPACITY];
            size = 0;
        }



        public void add(Key key) {
            elementData[size] = key;
            siftUp();
            size++;
            if (size == elementData.length) grow();
        }



        void swap(int  i, int j) {
            Key t = elementData[i];
            elementData[i] = elementData[j];
            elementData[j] = t;
        }

        public Key extractMin() {
            if (isEmpty()) {
                throw new ArrayIndexOutOfBoundsException("extract min from empty queue");
            }
            Key removed = elementData[0];
            elementData[0] = elementData[--size];
            siftDown();
            return removed;
        }

        public boolean isEmpty() {
            return size == 0;
        }



        private void siftUp(){
            int pos = size;
            while (pos > 0) {
                int parent = (pos - 1) / 2;
                if (elementData[pos].compareTo(elementData[parent]) >= 0)
                    break;
                swap(pos, parent);
                pos = parent;
            }
        }

        private void siftDown() {
            int pos = 0;
            while (true) {
                int child = 2 * pos + 1;
                if (child >= size)
                    break;
                if (child + 1 < size && elementData[child + 1].compareTo(elementData[child]) < 0)
                    ++child;
                if (elementData[pos].compareTo(elementData[child]) <=0)
                    break;
                swap(pos, child);
                pos = child;
            }
        }
        private void grow() {
            this.changeCapacity((int)Math.abs(size*1.5));
        }



        private void changeCapacity(int newCapacity) {
            elementData = Arrays.copyOf(elementData, newCapacity);
        }




    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        ArrayPriorityQueue<Integer> a = new ArrayPriorityQueue<>();

        while (in.hasNext()) {
            int s = in.nextInt();
            a.add(s);
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
