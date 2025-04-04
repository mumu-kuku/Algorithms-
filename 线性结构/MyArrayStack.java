package Linear;

// 手写实现基于数组的栈
public class MyArrayStack <E> {
    private MyArrayList<E> stack;

    // 无参构造方法，创建一个空的MyArrayStack对象
    public MyArrayStack() {
        stack = new MyArrayList<>();
    }

    // 传入一个MyArrayStack对象，复制其元素到新的MyArrayStack对象中
    public MyArrayStack(MyArrayStack other) {
        copyStack(other);
    }

    // 传入一个可变参数，将其元素添加到新的MyArrayStack对象中
    public MyArrayStack(E... elements) {
        copyArray(elements);
    }

    // 返回MyArrayStack对象中元素的个数
    public int size() {
        return stack.size();
    }

    // 将指定元素添加到MyArrayStack对象的末尾
    public void push(E element) {
        stack.add(element);
    }

    // 将一个可变参数中的元素拷贝到MyArrayStack对象中
    public void copyArray(E... elements) {
        stack = new MyArrayList<>(elements);
        for (int i = 0; i < elements.length; i++) {
            stack.add(elements[i]);
        }
    }

    // 将一个MyArrayStack对象中的所有元素拷贝到当前MyArrayStack对象
    public void copyStack(MyArrayStack other) {
        // 如果传入的MyArrayStack对象为空,则清空当前MyArrayStack对象
        stack = new MyArrayList<>();
        if (other.isEmpty() || other == null || other == this) {
            return;
        }
        for (int i = 0; i < other.size(); i++) {
            stack.add((E) other.stack.get(i));
        }
    }

    // 删除并返回栈顶元素
    public E pop() {
        if (isEmpty())
            return null;
        return stack.remove(size() - 1);
    }

    // 返回栈顶元素
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) stack.get(size() - 1);
    }

    // 判断MyArrayStack对象是否为空
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // 清空MyArrayStack对象
    public void clearStack() {
        stack.clear();
    }

    // 返回MyArrayStack对象的数组表示形式
    public Object[] toArray() {
        return stack.toArray();
    }

    // 返回MyArrayStack对象的字符串表示形式
    public String toString() {
        return stack.toString();
    }

}
