package lesson_2;

public interface MyList<E> {

    boolean add(E e);

    void add(int index, E element);

    E get(int index);

    E set(int index, E element);

    E remove(int index);

    boolean remove(Object o);

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    int indexOf(Object o);

    void clear();
}
