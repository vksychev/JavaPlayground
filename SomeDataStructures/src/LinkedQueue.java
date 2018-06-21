import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void enqueue(Item item) {
        if (this.isEmpty()){
            head = new Node<>(item);
            tail = head;
            size = 1;
        }else {
            Node<Item> tempo = new Node<>(item,null);
            head.next = tempo;
            head = head.next;
            size++;
        }
    }

    @Override
    public Item dequeue() {
        if (!this.isEmpty()){
            Node<Item> tempo = tail;
            tail = tail.next;
            size--;
            return tempo.item;
        }else {
            return null;
        }
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
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<Item> {

        private Node<Item> currentNode = tail;

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

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}
