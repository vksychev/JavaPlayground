import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDeque<Item> implements IDeque<Item> {

    private Node<Item> first;
    private Node<Item> last;

    private int size;

    @Override
    public void pushFront(Item item) {
        if (this.isEmpty()){
            first = new Node<>(item,null,null);
            last = first;
            size = 1;
        }else {
            Node<Item> tempo = new Node<>(item,first,null);
            first.prev = tempo;
            first = first.prev;
            size++;
        }
    }

    @Override
    public void pushBack(Item item) {
        if (this.isEmpty()){
            first = new Node<>(item);
            last = first;
            size = 1;
        }else {
            Node<Item> tempo = new Node<>(item,null,last);
            last.next = tempo;
            last = last.next;
            size++;
        }
    }

    @Override
    public Item popFront() {
        if (isEmpty())throw new ArrayIndexOutOfBoundsException("deque");

        Node<Item> prevHead = first;
        first = prevHead.next;
        prevHead.prev = null;
        size--;
        if (first != null) {
            first.prev = null;
        }
        return prevHead.item;
    }

    @Override
    public Item popBack() {
        if (isEmpty())throw new ArrayIndexOutOfBoundsException("deque");

        Node<Item> prevLast = last;
        last = prevLast.prev;
        prevLast.prev = null;
        size--;
        if (last != null) {
            last.next = null;
        }
        return prevLast.item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedDequeIterator();
    }

    private class LinkedDequeIterator implements Iterator<Item> {

        private Node<Item> currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode.next != null;
        }

        @Override
        public Item next() {
            if (currentNode == null) throw new NoSuchElementException();
            Item item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }

        public boolean hasPrev() {
            return currentNode.prev != null;
        }

        public Item prev() {
            if (currentNode == null) throw new NoSuchElementException();
            Item item = currentNode.item;
            currentNode = currentNode.prev;
            return item;
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;
        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next,Node<Item> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}