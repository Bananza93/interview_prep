package lesson_2.task_2;

import lesson_2.MyList;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class MyLinkedList<E> implements MyList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index = " + index + ", size = " + size);
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<E> beforeNode = getNode(index);
            Node<E> node = new Node<>(element, beforeNode.previous, beforeNode);
            beforeNode.previous.next = node;
            beforeNode.previous = node;
            size++;
        }
    }

    private Node<E> getNode(int index) {
        Node<E> node;
        if (index <= size / 2) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.previous;
            }
        }
        return node;
    }

    public void addFirst(E e) {
        Node<E> node = new Node<>(e, head);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.previous = node;
            head = node;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> node = new Node<>(e, tail, null);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index = " + index + ", size = " + size);
        return getNode(index).data;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index = " + index + ", size = " + size);
        Node<E> node = getNode(index);
        E oldValue = node.data;
        node.data = element;
        return oldValue;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index = " + index + ", size = " + size);
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> removedNode = getNode(index);
            E e = removedNode.data;
            removedNode.next.previous = removedNode.previous;
            removedNode.previous.next = removedNode.next;
            removedNode.data = null;
            removedNode.previous = null;
            removedNode.next = null;
            size--;
            return e;
        }
    }

    @Override
    public boolean remove(Object o) {
        Node<E> nextNode = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(nextNode.data, o)) {
                if (i == 0) {
                    removeFirst();
                } else if (i == size - 1) {
                    removeLast();
                } else {
                    nextNode.next.previous = nextNode.previous;
                    nextNode.previous.next = nextNode.next;
                    nextNode.data = null;
                    nextNode.previous = null;
                    nextNode.next = null;
                    size--;
                }
                return true;
            }
            nextNode = nextNode.next;
        }
        return false;
    }

    public E removeFirst() {
        if (head == null)
            throw new NoSuchElementException("List is empty");
        E e = head.data;
        Node<E> nextNode = head.next;
        head.data = null;
        head.next = null;
        head = nextNode;
        if (nextNode == null) {
            tail = null;
        } else {
            nextNode.previous = null;
        }

        size--;
        return e;
    }

    public E removeLast() {
        if (tail == null)
            throw new NoSuchElementException("List is empty");
        E e = tail.data;
        Node<E> prevNode = tail.previous;
        tail.data = null;
        tail.previous = null;
        tail = prevNode;
        if (prevNode == null) {
            head = null;
        } else {
            prevNode.next = null;
        }
        size--;
        return e;
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
        if (head != null) {
            Node<E> node = head;
            for (int i = 0; i < size; i++) {
                if (Objects.equals(node.data, o)) return i;
                node = node.next;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        StringJoiner sj = new StringJoiner(" -> ");
        Node<E> nextNode = head;
        for (int i = 0; i < size; i++) {
            sj.add(nextNode.data.toString());
            nextNode = nextNode.next;
        }
        sb.append(sj);
        sb.append("]");
        return sb.toString();
    }

    private static class Node<E> {

        private E data;
        private Node<E> previous;
        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        public Node(E data, Node<E> previous, Node<E> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", previous=" + (previous == null ? "null" : previous.data.toString()) +
                    ", next=" + (next == null ? "null" : next.data.toString()) +
                    '}';
        }
    }
}
