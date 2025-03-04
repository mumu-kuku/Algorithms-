package mumu;

public class MyArrayList<E> {      // 模拟ArrayList
    private Object[] elements;     // 存储元素的数组
    private int size = 0;          // 记录元素个数
    private static final int DEFAULT_CAPACITY = 10;     // 默认容量
    private int modCount = 0;

    public MyArrayList() {
        elements = new Object[0];
    }

    public MyArrayList(MyArrayList other)
    {
        this.size = other.size();
        elements = new Object[size];
        for (int i = 0; i < size; i++) {
            elements[i] = other.get(i);
        }
    }

    public Object get(int index) {
        return elements[index];
    }

    public int size() {
        return size;
    }

    public boolean add(E element) {
        modCount++;
        if (size == elements.length) {
            grow(size + 1);
        }
        elements[size++] = element;
        return true;
    }

    public boolean add(int index, E element) {
        modCount++;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (size == elements.length) {
            grow(size + 1);
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
        return true;
    }

    public boolean addAll(MyArrayList other) {
        if (other.size() == 0) {
            return false;
        }
        if (size + other.size() >= elements.length) {
            grow(size + other.size());
        }
        for (int i = 0; i < other.size(); i++) {
            elements[size++] = other.get(i);
        }
        modCount++;
        return true;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i+1];
        }
        size--;
        modCount++;
    }

    private void grow(int minCapacity) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity > Integer.MAX_VALUE - 8) {
            throw new OutOfMemoryError();
        }
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        elements = java.util.Arrays.copyOf(elements, newCapacity);
    }

    public E set(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E oldValue = (E)elements[index];
        elements[index] = element;
        return oldValue;
    }

    public String toString() {
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
