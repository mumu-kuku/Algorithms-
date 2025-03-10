package mumu;

// 手写实现基于动态数组的双向队列
public class MyArrayDeque<E> {
    private MyArrayList<E> elements;

    // 无参构造方法
    public MyArrayDeque() {
        elements = new MyArrayList<>();
    }

    // 复制构造方法
    public MyArrayDeque(MyArrayDeque<E> other) {
        elements = new MyArrayList<>(other.elements);
    }

    // 可变参数的构造方法
    public MyArrayDeque(E... elements) {
        this.elements = new MyArrayList<>();
        for (E element : elements) {
            this.elements.add(element);
        }
    }

    // 获取队列的大小
    public int size() {
        return elements.size();
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    // 在队首插入元素
    public void addFirst(E element) {
        elements.add(0, element);
    }

    // 在队尾插入元素
    public void addLast(E element) {
        elements.add(element);
    }

    // 从队首删除元素
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        return elements.remove(0);
    }

    // 从队尾删除元素
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        return elements.remove(elements.size() - 1);
    }

    // 获取队首元素
    public E getFirst() {
        if (isEmpty()) {
            return null;
        }
        return (E) elements.get(0);
    }

    // 获取队尾元素
    public E getLast() {
        if (isEmpty()) {
            return null;
        }
        return (E) elements.get(elements.size() - 1);
    }

    // 将队列转化为数组并返回
    public Object[] toArray() {
        return elements.toArray();
    }

    // 返回 MyArrayDeque 对象的字符串表示形式
    public String toString() {
        return elements.toString();
    }
}