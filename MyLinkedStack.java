package Linear;

// 手写实现基于单向链表的栈
public class MyLinkedStack <E>{
    private Node StackPeek;     // 链表的头节点
    private int size;       // 链表的长度

    // 无参构造方法
    public MyLinkedStack() {
        StackPeek = null;
        size = 0;
    }

    // 传入一个MyLinkedStack对象作为参数的构造方法
    public MyLinkedStack(MyLinkedStack other) {
        copyStack(other);
    }

    // 传入一个可变参数作为参数的构造方法
    public MyLinkedStack(E... elements) {
        // 如果传入的可变参数为空，则直接初始化为空
        copyArray(elements);
    }

    // 返回链表的长度
    public int size() {
        return size;
    }

    // 入栈操作
    public void push(E e) {
        // 创建一个新节点，并将其next指向当前链表的头节点
        Node node = new Node(e);
        node.next = StackPeek;
        // 将新节点设置为链表的头节点,作为栈顶来提供栈顶操作
        StackPeek = node;
        size++;
    }

    // 复制传入的MyLinkedStack对象到当前MyLinkedStack对象中
    public void copyStack(MyLinkedStack other) {
        // 如果传入的MyLinkedStack对象是当前MyLinkedStack对象，则返回true
        if (this == other) {
            return;
        }
        // 如果传入的MyLinkedStack对象为空，则返回false
        if (other.isEmpty()) {
            clearStack();
            return;
        }
        // 将传入的MyLinkedStack对象的数据复制到当前MyLinkedStack对象中
        this.size = other.size();
        Node SearchNode = other.StackPeek;
        while (SearchNode != null) {
            push(SearchNode.data);
            SearchNode = SearchNode.next;
        }
    }

    // 复制传入的可变参数到当前MyLinkedStack对象中
    public void copyArray(E... elements) {
        if (elements == null || elements.length == 0) {
            StackPeek = null;
            size = 0;
            return;
        }
        // 遍历传入的可变参数，将每个元素添加到当前MyLinkedStack对象中
        for (int i = 0; i < elements.length; i++) {
            push(elements[i]);
        }
        size = elements.length;
    }

    // 出栈操作
    public E pop() {
        // 如果当前MyLinkedStack对象为空，则返回null
        if (isEmpty()) {
            return null;
        }

        // 删除头节点并返回头节点的数据
        E removeData = peek();
        StackPeek = StackPeek.next;
        size--;

        return removeData;
    }

    // 返回链表的尾节点的数据
    public E peek() {
        // 如果链表为空，则返回null
        if (isEmpty()) {
            return null;
        }
        return StackPeek.data;
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空链表
    public void clearStack() {
        StackPeek = null;
        size = 0;
    }

    // 返回链表的数组表示
    public E[] toArray() {
        Node SearchNode = StackPeek;
        E[] arr = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = SearchNode.data;
            SearchNode = SearchNode.next;
        }
        return arr;
    }

    // 返回链表的字符串表示
    public String toString() {
        // 如果链表为空，则返回空数组
        if (isEmpty()) {
            return "[]";
        }
        // 遍历链表，将每个节点的数据添加到字符串中
        StringBuilder str = new StringBuilder();
        Node SearchNode = StackPeek;
        str.append("[ ");
        while(SearchNode!= null) {
            if (SearchNode.next == null) {
                str.append(SearchNode.data);
                break;
            }
            str.append(SearchNode.data + ", ");
            SearchNode = SearchNode.next;
        }
        str.append(" ]");
        return str.toString();
    }

    // 内部类，用于表示链表的节点
    private class Node {
        private Node next;      // 指向下一个节点
        private E data;         // 节点的数据

        public Node() {
            next = null;
            data = null;
        }

        public Node(E data) {
            this.data = data;
            next = null;
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
