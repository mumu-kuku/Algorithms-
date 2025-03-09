package mumu;

public class MyLinkedDeque <E> {
    private MyDoublyListNode<E> first;
    private MyDoublyListNode<E> last;
    private int size;

    // 无参构造方法
    public MyLinkedDeque() {
        first = null;
        last = null;
        size = 0;
    }

    // 传入一个MyLinkedDeque对象作为参数的构造方法
    public MyLinkedDeque(MyLinkedDeque other) {
        MyDoublyListNode SearchNode = other.first;
        // 遍历传入的MyLinkedStack对象，将每个节点的数据添加到当前MyLinkedStack对象中
        while (SearchNode!= null) {
            pushFirst((E)SearchNode.data);
            SearchNode = SearchNode.next;
        }
        size = other.size();
    }

    // 传入一个可变参数作为参数的构造方法
    public MyLinkedDeque(E... elements) {
        // 如果传入的可变参数为空，则直接返回
        if (elements.length == 0) {
            return;
        }
        // 遍历传入的可变参数，将每个元素添加到当前MyLinkedStack对象中
        for (int i = 0; i < elements.length; i++) {
            pushFirst(elements[i]);
        }
        size = elements.length;
    }

    public int size() { return size; }

    // 入队操作
    private void push(E e, boolean isFirst) {
        MyDoublyListNode node = new MyDoublyListNode<>(e);
        // 如果当前MyLinkedDeque对象为空，将头尾节点都指向新节点
        if (isEmpty()) {
            first = last = node;
        }
        // 队首入队操作
        else if (isFirst) {
            // 将新节点添加到链表的头节点之前
            node.next = first;
            first.prev = node;
            first = node;
        }
        // 队尾入队操作
        else {
            // 将新节点添加到链表的尾节点之后
            node.prev = last;
            last.next = node;
            last = node;
        }
        size++;
    }

    // 队首入队操作
    public void pushFirst(E e) {
        push(e, true);
    }

    // 队尾入队操作
    public void pushLast(E e) {
        push(e, false);
    }

    // 复制传入的MyLinkedDeque对象到当前MyLinkedDeque对象中
    public boolean copyQueue(MyLinkedDeque other) {
        // 如果传入的MyLinkedDeque对象是当前MyLinkedDeque对象，则返回true
        if (this == other) {
            return true;
        }
        // 如果传入的MyLinkedDeque对象为空，则返回false
        if (other.isEmpty()) {
            return false;
        }
        // 将传入的MyLinkedStack对象的数据复制到当前MyLinkedStack对象中
        this.size = other.size();
        MyDoublyListNode SearchNode = other.first;
        while (SearchNode != null) {
            pushFirst((E)SearchNode.data);
            SearchNode = SearchNode.next;
        }
        return true;
    }

    // 出队操作
    public E pop(boolean isFirst) {
        // 如果队列为空，返回空
        if (isEmpty()) {
            return null;
        }
        E val;
        // 删除头节点并返回头节点的数据
        if (isFirst) {
            val = first.data;
            MyDoublyListNode fNext = first.next;
            if (fNext != null) {
                fNext.prev = null;
                first.next = null;
            }
            first = fNext;
        }
        // 删除尾节点并返回尾节点的数据
        else {
            val = last.data;
            MyDoublyListNode fPrev = last.prev;
            if (fPrev != null) {
                fPrev.next = null;
                last.prev = null;
            }
            last = fPrev;
        }
        size--;
        return val;
    }

    // 头节点出队操作
    public E popFirst() {
        return pop(true);
    }

    // 尾节点出队操作
    public E popLast() {
        return pop(false);
    }

    // 返回链表的头节点的数据
    public E peekFirst() {
        // 如果链表为空，则返回null
        if (isEmpty()) {
            return null;
        }
        return first.data;
    }

    // 返回链表的尾节点的数据
    public E peekLast() {
        // 如果链表为空，则返回null
        if (isEmpty()) {
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
        MyDoublyListNode SearchNode = first;
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
        MyDoublyListNode SearchNode = first;
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
