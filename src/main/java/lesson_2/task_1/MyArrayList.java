package lesson_2.task_1;

import lesson_2.MyList;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class MyArrayList<E> implements MyList<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public MyArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
    }

    @Override
    public boolean add(E e) {
        if (size == elements.length) {
            elements = grow();
        }
        elements[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        if (size == elements.length) {
            elements = grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    private Object[] grow() {
        int oldCapacity = elements.length;
        int newCapacity;
        if ((newCapacity = oldCapacity >> 1) < oldCapacity) {
            if ((newCapacity = oldCapacity + 1) < oldCapacity) {
                throw new OutOfMemoryError("Cannot grows the list (current size = " + size + ")");
            }
        }
        return Arrays.copyOf(elements, newCapacity);
    }

    private void checkIndex(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("index must be >= 0");
        if (index >= size) throw new IndexOutOfBoundsException("index >= size");
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        E e = get(index);
        elements[index] = element;
        return e;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E e = (E) elements[index];
        remove0(index);
        return e;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0) return false;
        remove0(index);
        return true;
    }

    private void remove0(int index) {
        System.arraycopy(elements, index + 1, elements, index, --size - index);
        elements[size] = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) return i;
        }
        return -1;
    }

    @Override
    public void clear() {
        this.elements = new Object[elements.length];
        this.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        StringJoiner sj = new StringJoiner(", ");
        for (int i = 0; i < size; i++) {
            sj.add(elements[i].toString());
        }
        sb.append(sj);
        sb.append("]");
        return sb.toString();
    }
}
