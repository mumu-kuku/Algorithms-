package mumu;

public class MyRigidArrayQueue <E>{
    private Object[] elements;
    private int first;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    // 无参构造方法
    public MyRigidArrayQueue(){
        elements = new Object[DEFAULT_CAPACITY];
        first = size = 0;
    }

    // 指定容量构造方法
    public MyRigidArrayQueue(int capacity) {
        elements = new Object[capacity];
        first = size = 0;
    }

    // 复制构造方法
    public MyRigidArrayQueue(MyRigidArrayQueue other){
        elements = new Object[other.elements.length];
        for(int i = 0; i < other.elements.length; i++){
            elements[i] = other.elements[i];
        }
        first = other.first;
        size = other.size;
    }

    // 可变参数的构造方法
    public MyRigidArrayQueue(E... elements){
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

    // 入队操作
    public boolean push(E element) {
        if (size == capacity()) {  // 如果数组已满，入队失败
              return false;
        }
        // 计算队尾指针，指向队尾索引的下一个位置
        // 通过取模运算实现循环队列
        // last如果越过最后一个元素，则从数组的第一个元素开始
        int last = (first + 1) % capacity();
        elements[last] = element;
        size++;
        return true;
    }

    // 出队操作
    public E pop() {
        E val = peek();
        if (peek() == null) {
            return null;
        }
        // 队首元素后移一位，若越过尾部，则回到头部
        first = (first + 1) % capacity();
        size--;
        return val;
    }

    // 获取队首元素
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) elements[first];
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
            arr[i] = elements[j % capacity()];
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
