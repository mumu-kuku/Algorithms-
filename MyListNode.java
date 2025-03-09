package mumu;

// 一个简单的链表节点类
public class MyListNode<T> {
    public T data;
    public MyListNode next;

    public MyListNode() {
        this.data = null;
        this.next = null;
    }

    public MyListNode(T data) {
        this.data = data;
        this.next = null;
    }

    public MyListNode(T data, MyListNode next) {
        this.data = data;
        this.next = next;
    }

    public String toString() {
        return data.toString();
    }
}
