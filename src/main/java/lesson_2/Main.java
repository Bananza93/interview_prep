package lesson_2;

import lesson_2.task_1.MyArrayList;

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
    }
}
