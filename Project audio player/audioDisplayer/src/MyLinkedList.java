import java.util.ArrayList;
import java.util.List;

public class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node<T> getHead() {
        return head;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void remove(T element) {
        Node<T> current = head;
        Node<T> previous = null;

        while (current != null) {
            if (current.data.equals(element)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                if (current == tail) {
                    tail = previous;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    // Convert linked list to a list of nodes
    public List<Node<T>> toNodeList() {
        List<Node<T>> nodeList = new ArrayList<>();
        Node<T> current = head;
        while (current != null) {
            nodeList.add(current);
            current = current.next;
        }
        return nodeList;
    }
}
