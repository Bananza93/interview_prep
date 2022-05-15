package lesson_2;

import lesson_2.task_1.MyArrayList;
import lesson_2.task_2.MyLinkedList;

public class Main {

    public static void main(String[] args) {
        MyList<Integer> list = new MyArrayList<>();
        System.out.println("Add 1: " + list.add(1));
        System.out.println("Add 2: " + list.add(2));
        System.out.println("Add 3: " + list.add(3));
        System.out.println("size = " + list.size());
        System.out.println("list = " + list);
        System.out.println("Add 4 to index 1");
        list.add(1, 4);
        System.out.println("size = " + list.size());
        System.out.println("list = " + list);
        System.out.println("Get index 2: " + (2 == list.get(2)));
        System.out.println("Set index 2 to value 5: " + list.set(2, 5));
        System.out.println("size = " + list.size());
        System.out.println("list = " + list);
        System.out.println("Remove index 1: " + list.remove(1));
        System.out.println("size = " + list.size());
        System.out.println("list = " + list);
        System.out.println("Remove value 3");
        list.remove((Integer) 3);
        System.out.println("size = " + list.size());
        System.out.println("list = " + list);

        MyLinkedList<Integer> list2 = new MyLinkedList<>();
        System.out.println("Add 10: " + list2.add(10));
        System.out.println("Add 20 as last");
        list2.addLast(20);
        System.out.println("Add 30 as first");
        list2.addFirst(30);
        System.out.println("size = " + list2.size());
        System.out.println("list = " + list2);
        System.out.println("Add 40 to index 1");
        list2.add(1, 40);
        System.out.println("size = " + list2.size());
        System.out.println("list = " + list2);
        System.out.println("Get index 2: " + (20 == list2.get(2)));
        System.out.println("Set index 2 to value 50: " + list2.set(2, 50));
        System.out.println("size = " + list2.size());
        System.out.println("list = " + list2);
        System.out.println("Remove index 1: " + list2.remove(1));
        System.out.println("size = " + list2.size());
        System.out.println("list = " + list2);
        System.out.println("Remove value 30");
        list2.remove((Integer) 30);
        System.out.println("size = " + list2.size());
        System.out.println("list = " + list2);
    }
}
