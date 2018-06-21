import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<Item> implements IStack<Item> {

    private static final int DEFAULT_CAPACITY = 10;
    private Item[] elementData;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this.elementData = (Item[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void push(Item item) {
        if (size == elementData.length){
            this.grow();
        }
        elementData[size] = item;
        size++;
    }

    @Override
    public Item pop() {
        if (size == 0){
            return null;
        }
        if (elementData.length/size >= 4) this.shrink();
        size--;
        Item res = elementData[size];
        elementData[size] = null;
        return res;
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
        this.changeCapacity((int)Math.abs(size*1.5));
    }
    private void shrink() {
        this.changeCapacity(Math.abs(size/2));
    }

    private void changeCapacity(int newCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<Item> {

        private int currentPosition = size;

        @Override
        public boolean hasNext() {
            return currentPosition != 0;
        }

        @Override
        public Item next() {
            return elementData[--currentPosition];
        }

    }

}
