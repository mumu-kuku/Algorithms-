package Linear;

// 手写实现基于链表的队列
public class MyLinkedQueue <E>{
    private List_Node<E> first;
    private List_Node<E> last;
    private int size;

    // 无参构造方法
    public MyLinkedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    // 传入一个MyLinkedQueue对象作为参数的构造方法
    public MyLinkedQueue(MyLinkedQueue other) {
        List_Node SearchNode = other.first;
        // 遍历传入的MyLinkedStack对象，将每个节点的数据添加到当前MyLinkedStack对象中
        while (SearchNode!= null) {
            push((E)SearchNode.data);
            SearchNode = SearchNode.next;
        }
        size = other.size();
    }

    // 传入一个可变参数作为参数的构造方法
    public MyLinkedQueue(E... elements) {
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

    public int size() { return size; }

    // 入队操作
    public void push(E e) {
        List_Node node = new List_Node<>(e);
        // 如果当前MyLinkedQueue对象为空，将头尾节点都指向新节点
        if (first == null) {
            first = node;
            last = node;
        }
        // 创建一个新节点，并将其添加到链表的尾部
        else {
            last.next = node;
            last = last.next;
        }
        size++;
    }

    // 复制传入的MyLinkedQueue对象到当前MyLinkedQueue对象中
    public boolean copyQueue(MyLinkedQueue other) {
        // 如果传入的MyLinkedQueue对象是当前MyLinkedQueue对象，则返回true
        if (this == other) {
            return true;
        }
        // 如果传入的MyLinkedQueue对象为空，则返回false
        if (other.isEmpty()) {
            return false;
        }
        // 将传入的MyLinkedStack对象的数据复制到当前MyLinkedStack对象中
        this.size = other.size();
        List_Node SearchNode = other.first;
        while (SearchNode != null) {
            push((E)SearchNode.data);
            SearchNode = SearchNode.next;
        }
        return true;
    }

    // 出队操作
    public E pop() {
        E val = peek();
        // 删除并返回链表的头节点
        if (peek() == null) {
            return null;
        }
        // 修改头节点
        first = first.next;
        size--;

        return val;
    }

    // 返回链表的头节点的数据
    public E peek() {
        // 如果链表为空，则返回null
        if (isEmpty()) {
            return null;
        }
        return first.data;
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
    public void clearQueue() {
        first = null;
        last = null;
        size = 0;
    }

    // 将链表转化为数组并返回
    public Object[] toArray() {
        // 如果链表为空，则返回null
        if (isEmpty()) {
            return null;
        }
        List_Node SearchNode = first;
        Object[] arr = new Object[size];
        // 遍历链表，将每个节点的数据添加到数组中
        for (int i = 0; i < size; i++) {
            arr[i] = SearchNode.data;
            SearchNode = SearchNode.next;
        }

        return arr;
    }

    // 返回链表的字符串表示
    public String toString() {
        String str = "";
        // 如果链表为空，则返回空数组
        List_Node SearchNode = first;
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
}
