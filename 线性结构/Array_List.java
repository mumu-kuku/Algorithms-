package Linear;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

// 手写实现基于数组的列表
public class Array_List<E> extends My_List<E> implements Iterable<E> {      // 模拟ArrayList
    private Object[] elements;     // 存储元素的数组
    private int size = 0;          // 记录元素个数
    private static final int DEFAULT_CAPACITY = 10;     // 默认容量

    // 无参构造方法，创建一个空的Array_List对象
    public Array_List() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    // 传入一个Array_List对象，复制其元素到新的Array_List对象中
    public Array_List(My_List<E> other) {
        this.size = other.size();
        elements = new Object[size];
        System.arraycopy(other.toArray(), 0, this.elements, 0, size);
    }

    // 传入一个可变参数，将其元素添加到新的Array_List对象中
    public Array_List(E... elements) {
        this.size = elements.length;
        this.elements = new Object[size];
        System.arraycopy(elements, 0, this.elements, 0, size);
    }

    // 传入列表大小的构造方法
    public Array_List(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        elements = new Object[capacity];
        this.size = 0;
    }

    // 返回指定索引位置的元素
    public E get(int index) {
        return (E) elements[index];
    }

    // 返回Array_List对象中元素的个数
    public int size() {
        return size;
    }

    // 返回Array_List的容量
    public int capacity() {
        return elements.length;
    }

    // 将指定元素添加到Array_List对象的末尾
    public void add(E element) {
        if (size == elements.length) {  // 如果数组已满，则扩容
            grow(size + 1);  // 调用grow方法
        }
        elements[size++] = element;     // 将元素添加到数组末尾，并更新size
    }

    // 将指定元素插入到Array_List对象的指定位置
    public boolean add(int index, E element) {
        if (index < 0 || index > size) {    // 如果索引越界，则抛出异常
            return false;
        }
        if (size == elements.length) {  // 如果数组已满，则扩容
            grow(size + 1);  // 调用grow方法
        }
        for (int i = size; i > index; i--) {
            // 将指定位置及其之后的元素后移一位
            elements[i] = elements[i - 1];
        }
        elements[index] = element;     // 将元素插入到指定位置
        size++;
        return true;
    }

    // 将一个Array_List对象中的所有元素添加到当前Array_List对象的末尾
    public boolean addAll(My_List<E> other) {
        // 如果传入的Array_List对象为空，则返回false
        if (other.size() == 0) {
            return false;
        }
        // 如果数组已满，则扩容
        if (size + other.size() >= elements.length) {
            grow(size + other.size());  // 调用grow方法
        }
        for (int i = 0; i < other.size(); i++) {
            // 将传入的Array_List对象中的元素添加到当前Array_List对象的末尾
            elements[size++] = other.get(i);
        }
        return true;
    }

    // 删除指定索引位置的元素
    public E remove(int index) {
        // 如果索引越界，则抛出异常
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E removeElement = (E)elements[index];
        // 将指定位置及其之后的元素前移一位
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i+1];
        }
        size--;
        return removeElement;
    }

    // 删除指定元素
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            // 如果找到指定元素，则删除
            if (elements[i].equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
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

    // 修改指定索引位置的元素
    public E set(int index, E element) {
        if (index < 0 || index > size) {    // 如果索引越界，则抛出异常
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E oldValue = (E)elements[index];    // 获取指定索引位置的旧元素
        elements[index] = element;     // 将新元素赋值给指定索引位置的元素
        return oldValue;
    }

    // 修改第一个与指定元素相等的元素
    public int set(E oldValue, E newValue) {
        // 遍历数组，找到第一个与oldValue相等的元素，将其替换为newValue，并返回其索引位置
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(oldValue)) {
                elements[i] = newValue;
                return i;
            }
        }
        return -1;
    }

    // 返回指定元素在Array_List对象中的索引位置
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            // 如果找到指定元素，则返回其索引位置
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    // 返回指定元素在Array_List对象中从指定位置开始搜索的索引位置
    public int indexOf(E element, int startIndex) {
        // 如果指定位置越界，则抛出异常
        if (startIndex < 0 || startIndex >= size) {
            throw new IndexOutOfBoundsException("Index: " + startIndex + ", Size: " + size);
        }
        // 从指定位置开始搜索，找到第一个与element相等的元素，并返回其索引位置
        for (int i = startIndex; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    // 查找指定元素在Array_List对象中是否存在
    public boolean contains(E element) {
        // 遍历数组，找到第一个与element相等的元素，则返回true，否则返回false
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    // 判断Array_List对象是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空Array_List对象
    public void clear() {
        elements = new Object[0];
        size = 0;
    }

    // 遍历方法1
    // 借用Consumer接口
    // Params:consumer - 遍历要执行的操作,入参:每个元素
    public void forEach(Consumer<? super E> consumer) {
        for (int i = 0; i < size; i++) {
            consumer.accept((E)elements[i]);
        }
    }

    // 遍历方法2
    // 实现Iterator接口
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public E next() {
                return (E)elements[i++];
            }
        };
    }

    // 遍历方法3: 实现Stream流
    public Stream<E> Stream() {
        return (Stream<E>) Stream.of(Arrays.copyOfRange(elements, 0, size));
    }

    // 返回Array_List对象的数组表示形式
    public E[] toArray() {
        return (E[]) Arrays.copyOf(elements, size);
    }

    // 返回Array_List对象的字符串表示形式
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
