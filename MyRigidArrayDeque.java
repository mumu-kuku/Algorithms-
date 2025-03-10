package mumu;

// 手写实现基于循环数组实现的双端队列
public class MyRigidArrayDeque <E> {
    private Object[] elements;
    private int first;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    // 无参构造方法
    public MyRigidArrayDeque(){
        elements = new Object[DEFAULT_CAPACITY];
        first = size = 0;
    }

    // 指定容量构造方法
    public MyRigidArrayDeque(int capacity) {
        elements = new Object[capacity];
        first = size = 0;
    }

    // 复制构造方法
    public MyRigidArrayDeque(MyRigidArrayDeque other){
        elements = new Object[other.elements.length];
        for(int i = 0; i < other.elements.length; i++){
            elements[i] = other.elements[i];
        }
        first = other.first;
        size = other.size;
    }

    // 可变参数的构造方法
    public MyRigidArrayDeque(E... elements){
        this.elements = new Object[elements.length];
        for(int i = 0; i < elements.length; i++){
            this.elements[i] = elements[i];
        }
        first = 0;
        size = elements.length;
    }

    // 获取队列的容量
    public int capacity(){
        return elements.length;
    }

    // 获取队列的大小
    public int size(){
        return size;
    }

    // 计算环形数组索引
    private int index(int i) {
        // 通过取模运算实现循环队列
        // 当 i 越过数组尾部后，回到头部
        // 当 i 越过数组头部后，回到尾部
        return (i + capacity()) % capacity();
    }

    // 队首入队操作
    public boolean pushFirst(E element) {
        if (size == capacity()) {  // 如果数组已满，入队失败
            return false;
        }
        // 队首指针左移一位，若越过头部，则回到尾部
        first = index(first - 1);
        elements[first] = element;
        size++;
        return true;
    }

    // 队尾入队操作
    public boolean pushLast(E element) {
        if (size == capacity()) {  // 如果数组已满，入队失败
            return false;
        }
        // 队尾指针右移一位，若越过尾部，则回到头部
        int last = index(first + size);
        elements[last] = element;
        size++;
        return true;
    }

    // 队首出队操作
    public E popFirst() {
        if (peekFirst() == null) {
            return null;
        }
        // 队首元素后移一位，若越过尾部，则回到头部
        E val = peekFirst();
        first = index(first + 1);
        size--;
        return val;
    }

    // 队尾出队操作
    public E popLast() {
        if (peekLast() == null) {
            return null;
        }
        E val = peekLast();
        size--;
        return val;
    }

    // 获取队首元素
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return (E) elements[first];
    }

    // 获取队尾元素
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        // 获取队尾指针
        int last = index(first + size - 1);
        return (E) elements[last];
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 将队列转化为数组并返回
    public Object[] toArray() {
        Object[] arr = new Object[size];
        // 从队首开始遍历队列，将元素依次添加到数组中
        // i表示数组的索引，j表示队列的索引
        for (int i = 0, j = first; i < size; i++, j++) {
            arr[i] = elements[index(j)];
        }
        return arr;
    }

    // 返回MyArrayQueue对象的字符串表示形式
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        // 使用StringBuilder类构建字符串，并将数组中的元素添加到字符串中
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0, j = first; i < size; i++, j++) {
            sb.append(elements[j % capacity()]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
