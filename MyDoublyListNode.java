package mumu;

// 一个简单的双向链表结点类
public class MyDoublyListNode <T> extends MyListNode<T>{
    public MyDoublyListNode<T> prev;
    public MyDoublyListNode<T> next;
    public T data;

    public MyDoublyListNode(T data) {
        super(data);
    }
    public MyDoublyListNode(T data, MyDoublyListNode<T> next, MyDoublyListNode<T> prev) {
        super(data, next);
        this.prev = prev;
    }
}
