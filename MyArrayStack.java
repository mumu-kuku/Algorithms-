package mumu;

// 手写实现基于数组的栈
public class MyArrayStack <E> {
    private Object[] elements;     // 存储元素的数组
    private int size = 0;          // 记录元素个数
    private static final int DEFAULT_CAPACITY = 10;     // 默认容量
    private int modCount = 0;       // 记录修改次数

    // 无参构造方法，创建一个空的MyArrayStack对象
    public MyArrayStack() {
        elements = new Object[0];
    }

    // 传入一个MyArrayStack对象，复制其元素到新的MyArrayStack对象中
    public MyArrayStack(MyArrayStack other)
    {
        this.size = other.size();
        elements = new Object[size];
        for (int i = 0; i < size; i++) {
            elements[i] = other.elements[i];
        }
    }

    // 传入一个可变参数，将其元素添加到新的MyArrayStack对象中
    public MyArrayStack(E... elements) {
        this.size = elements.length;
        this.elements = new Object[size];
        for (int i = 0; i < size; i++) {
            this.elements[i] = elements[i];
        }
    }

    // 返回MyArrayStack对象中元素的个数
    public int size() {
        return size;
    }

    // 将指定元素添加到MyArrayStack对象的末尾
    public boolean push(E element) {
        modCount++;
        if (size == elements.length) {  // 如果数组已满，则扩容
            grow(size + 1);  // 调用grow方法
        }
        elements[size++] = element;     // 将元素添加到数组末尾，并更新size
        return true;
    }

    // 将一个MyArrayStack对象中的所有元素添加到当前MyArrayStack对象的末尾
    public boolean copyStack(MyArrayStack other) {
        // 如果传入的MyArrayStack对象为空，则返回false
        if (other.size() == 0) {
            return false;
        }
        // 如果数组已满，则扩容
        if (size + other.size() >= elements.length) {
            grow(size + other.size());  // 调用grow方法
        }
        for (int i = 0; i < other.size(); i++) {
            // 将传入的MyArrayStack对象中的元素添加到当前MyArrayStack对象的末尾
            elements[size++] = other.elements[i];
        }
        modCount++;
        return true;
    }

    // 删除并返回栈顶元素
    public E pop() {
        E oldValue = (E)elements[size - 1];    // 获取栈顶元素
        elements[size - 1] = null;     // 将栈顶元素置为null
        size--;
        modCount++;
        return oldValue;
    }

    // 返回栈顶元素
    public E peek() {
        if (size == 0) {
            return null;
        }
        return (E)elements[size - 1];
    }

    private void grow(int minCapacity) {    // 扩容方法
        int oldCapacity = elements.length;  // 获取当前数组的长度
        // 计算新的数组长度，新长度为旧长度的1.5倍，如果新长度小于最小容量，则使用最小容量
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 如果新长度超过了int类型的最大值，则抛出异常
        if (newCapacity > Integer.MAX_VALUE - 8) {
            throw new OutOfMemoryError();
        }
        // 如果新长度小于最小容量，则使用最小容量
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        // 将当前数组的元素复制到新的数组中
        elements = java.util.Arrays.copyOf(elements, newCapacity);
    }

    // 判断MyArrayStack对象是否为空
    public boolean isEmpty() {
        // 如果size为0或者elements为null，则返回true，否则返回false
        if (size == 0 || elements == null) {
            return true;
        }
        else {
            return false;
        }
    }

    // 清空MyArrayStack对象
    public void clearStack() {
        elements = new Object[0];
        size = 0;
        modCount++;
    }

    // 返回MyArrayStack对象的字符串表示形式
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        // 使用StringBuilder类构建字符串，并将数组中的元素添加到字符串中
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
