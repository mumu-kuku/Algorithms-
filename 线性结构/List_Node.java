package Linear;

// 一个简单的链表节点类
public class List_Node<T> {
    public T data;          // 存储节点的数据
    public List_Node next; // 指向下一个节点的引用

    // 无参构造方法
    public List_Node() {
        this.data = null;
        this.next = null;
    }

    // 传入一个节点数据的构造方法
    public List_Node(T data) {
        this.data = data;
        this.next = null;
    }

    // 传入一个节点数据和下一个节点引用的构造方法
    public List_Node(T data, List_Node next) {
        this.data = data;
        this.next = next;
    }

    // 重写toString方法，返回节点数据的字符串表示
    public String toString() {
        return data.toString();
    }
}
