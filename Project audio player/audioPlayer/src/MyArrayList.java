import java.util.Arrays;

public class MyArrayList<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;

    public MyArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                // Shift elements to the left
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[--size] = null;
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) elements[index];
        }
        throw new IndexOutOfBoundsException();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }
}
