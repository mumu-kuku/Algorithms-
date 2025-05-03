package Linear;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        // 测试Singly_Linked_List
        Singly_Linked_List<Integer> list = new Singly_Linked_List<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.forEach(System.out::println);
        System.out.println();
        list.set(0, 10);
        list.Stream().forEach(System.out::println);
        System.out.println();
        list.remove(0);
        list.iterator().forEachRemaining(System.out::println);
        System.out.println();
        list.remove((Integer) 9);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
