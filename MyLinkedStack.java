package mumu;

// 手写实现基于单向链表的栈
public class MyLinkedStack <E>{
    private Node first;     // 链表的头节点
    private Node last;      // 链表的尾节点
    private int size;       // 链表的长度

    // 无参构造方法
    public MyLinkedStack() {
        first = null;
        last = null;
        size = 0;
    }

    // 传入一个MyLinkedStack对象作为参数的构造方法
    public MyLinkedStack(MyLinkedStack other) {
        Node SearchNode = other.first;
        // 遍历传入的MyLinkedStack对象，将每个节点的数据添加到当前MyLinkedStack对象中
        while (SearchNode!= null) {
            push(SearchNode.data);
            SearchNode = SearchNode.next;
        }
        size = other.size();
    }

    // 传入一个可变参数作为参数的构造方法
    public MyLinkedStack(E... elements) {
        // 如果传入的可变参数为空，则直接返回
        if (elements.length == 0) {
            return;
        }
        // 遍历传入的可变参数，将每个元素添加到当前MyLinkedStack对象中
        for (int i = 0; i < elements.length; i++) {
            push(elements[i]);
        }
        size = elements.length;
    }

    public int size() {
        return size;
    }

    // 入栈操作
    public void push(E e) {
        // 如果当前MyLinkedStack对象为空，则创建一个头节点，并将last指向头节点
        if (first == null) {
            first = new Node();
            first.data = e;
            first.next = null;
            last = first;
            size++;
            return;
        }
        // 创建一个新节点，并将其添加到链表的尾部
        last.next = new Node();
        last.next.data = e;
        last.next.next = null;
        last = last.next;
        size++;
    }

    // 复制传入的MyLinkedStack对象到当前MyLinkedStack对象中
    public boolean copyStack(MyLinkedStack other) {
        // 如果传入的MyLinkedStack对象是当前MyLinkedStack对象，则返回true
        if (this == other) {
            return true;
        }
        // 如果传入的MyLinkedStack对象为空，则返回false
        if (other.size() == 0 || other == null) {
            return false;
        }
        // 将传入的MyLinkedStack对象的数据复制到当前MyLinkedStack对象中
        this.size = other.size();
        Node SearchNode = other.first;
        while (SearchNode != null) {
            push(SearchNode.data);
            SearchNode = SearchNode.next;
        }
        return true;
    }

    // 出栈操作
    public E pop() {
        // 如果当前MyLinkedStack对象为空，则返回null
        if (last == null) {
            return null;
        }
        // 删除并返回链表的尾节点
        E removeData = last.data;
        // 如果链表的长度为1，则将last指向null
        if (last == first) {
            last = null;
            size--;
            return removeData;
        }
        // 遍历链表，找到链表的倒数第二个节点，并将其next指向null
        Node SearchNode = first;
        while (SearchNode.next != last){
            SearchNode = SearchNode.next;
        }
        SearchNode.next = null;
        last = SearchNode;
        size--;

        return removeData;
    }

    // 返回链表的尾节点的数据
    public E peek() {
        // 如果链表为空，则返回null
        if (last == null) {
            return null;
        }
        return last.data;
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        if (first == null || size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    // 清空链表
    public void clearStack() {
        first = null;
        last = null;
        size = 0;
    }

    // 返回链表的字符串表示
    public String toString() {
        String str = "";
        // 如果链表为空，则返回空数组
        MyLinkedStack.Node SearchNode = first;
        if (SearchNode == null) {
            str += "[]";
            return str;
        }
        // 遍历链表，将每个节点的数据添加到字符串中
        str += "[";
        while(SearchNode!= null) {
            if (SearchNode == last) {
                str += SearchNode.data;
                break;
            }
            str += SearchNode.data + ", ";
            SearchNode = SearchNode.next;
        }
        str += "]";
        return str;
    }

    // 内部类，用于表示链表的节点
    private class Node {
        private Node next;      // 指向下一个节点
        private E data;         // 节点的数据
    }
}
