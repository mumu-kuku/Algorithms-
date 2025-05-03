package Linear;

// 一个简单的双向链表结点类
public class MyDoublyListNode <T> extends List_Node<T> {
    public MyDoublyListNode<T> prev;    // 指向前一个结点的指针
    public MyDoublyListNode<T> next;    // 指向后一个结点的指针
    public T data;                      // 存储数据的变量

    // 构造函数，初始化结点
    public MyDoublyListNode() { super(); }
    public MyDoublyListNode(T data) {
        super(data);
    }    // 构造函数，传入数据，初始化结点
    // 构造函数，传入数据和前后的结点，初始化结点
    public MyDoublyListNode(T data, MyDoublyListNode<T> next, MyDoublyListNode<T> prev) {
        super(data, next);
        this.prev = prev;
    }
}
