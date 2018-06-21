import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class CyclicArrayQueue<Item> implements IQueue<Item> {
    int size;
    int head;
    int tail;
    private final int DEFAULT_CAPACITY = 10;
    private Item[] elementData;

    CyclicArrayQueue() {
        this.elementData = (Item[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        head = 0;
        tail = 0;
    }

    @Override
    public void enqueue(Item item) {
        if (size() == elementData.length) {
            grow();
        }
        elementData[tail] = item;

        tail = (tail + 1) % elementData.length;

        size++;
    }

    @Override
    public Item dequeue() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("queue");
        }

        Item result = elementData[head];
        elementData[head] = null;

        head = (head + 1) % elementData.length;

        size--;
        if (size<=elementData.length/4){
            shrink();
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void grow() {
        this.changeCapacity(Math.abs(size * 3 / 2));
    }

    private void shrink() {
        int n = Math.abs(size / 2);
        if (n < DEFAULT_CAPACITY) n = DEFAULT_CAPACITY;
        this.changeCapacity(n);
    }

    private void changeCapacity(int newCapacity) {
        Item[] l = (Item[])(new Object[newCapacity]);
        for (int scan=0;scan <size;scan++){
            l[scan] = elementData[head];
            head = (head +1)%elementData.length;
        }
        head = 0;
        tail = size;
        elementData = l;
    }


    @Override
    public Iterator<Item> iterator() {
        return new CyclicArrayQueueIterator();
    }

    private class CyclicArrayQueueIterator implements Iterator<Item> {

        private int currentPosition = head - 1;

        @Override
        public boolean hasNext() {
            return currentPosition != tail;
        }
        @Override
        public Item next() {
            currentPosition = (currentPosition +1)%elementData.length;
            return elementData[currentPosition];
        }

    }
}