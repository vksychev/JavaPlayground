import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> implements IStack<Item> {

    private Node<Item> head;
    private int size;

    @Override
    public void push(Item item) {
        if (isEmpty()) {
            head = new Node<>(item, head);
            size++;

            return;
        }
        size++;
        Node<Item> temp = new Node<>(item, head);
        head = temp;
    }

    @Override
    public Item pop() {
        if (isEmpty()) {
            return null;
        }
        size--;
        Item temp = head.item;
        head = head.next;
        return temp;
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
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<Item> {

        private Node<Item> currentNode = head;

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

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}