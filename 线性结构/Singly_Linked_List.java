package Linear;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

// 手写实现基于单向链表实现的列表
public class Singly_Linked_List<E> extends My_List<E> {
    private Node first;     // 头节点
    private int size;       // 列表大小

    // 无参构造方法
    public Singly_Linked_List() {
        first = null;
        size = 0;
    }

    // 传入一个MySinglyLinkedList对象作为参数的构造方法
    public Singly_Linked_List(Singly_Linked_List other) {
        for (int i = 0; i < other.size(); i++) {
            add((E) other.get(i));
        }
        size = other.size();
    }

    // 返回列表大小
    public int size() {
        return size;
    }

    // 获取列表第一个元素
    public E getFirst() {
        return first.data;
    }

    // 获取指定索引的元素
    public E get(int index) {
        if (index >= size) {
            return null;
        }
        Node SearchNode = first;
        for (int i = 0; i < index; i++) {
            SearchNode = SearchNode.next;
        }
        return SearchNode.data;
    }

    // 添加一个新的元素到列表末尾
    public void add(E e) {
        // 如果列表为空，直接创建一个新节点作为头节点
        if (first == null) {
            first = new Node();
            first.data = e;
            first.next = null;
            size++;
            return;
        }
        // 否则，遍历列表找到最后一个节点，并将新节点添加到其后面
        Node SearchNode = first;
        for (int i = 0; i < size-1; i++) {
            SearchNode = SearchNode.next;
        }
        SearchNode.next = new Node();
        SearchNode.next.data = e;
        SearchNode.next.next = null;
        size++;
    }

    // 在指定索引处添加一个新的元素
    public boolean add(int index, E e) {
        // 如果索引无效，直接返回
        if (index > size || index < 0) {
            return false;
        }
        // 如果index = 0，直接调用addFirst方法
        if (index == 0) {
            addFirst(e);
            return true;
        }
        // 否则，遍历列表找到指定索引的前一个节点，并将新节点添加到其后面
        Node SearchNode = first;
        for (int i = 0; i < index - 1; i++) {
            SearchNode = SearchNode.next;
        }
        Node NewNext = new Node();
        NewNext.data = e;
        NewNext.next = SearchNode.next;
        SearchNode.next = NewNext;
        size++;
        return true;
    }

    // 添加一个新的元素到列表开头
    public void addFirst(E e) {
        Node OldNode = first;
        Node NewNext = new Node();
        NewNext.data = e;
        NewNext.next = OldNode;
        first = NewNext;
        size++;
    }

    // 删除指定索引处的元素
    public E remove(int index) {
        // 如果索引无效，返回null
        if (index >= size || index < 0) {
            return null;
        }
        // 如果index = 0，直接调用removeFirst方法
        Node SearchNode = first;
        if (index == 0) {
            return removeFirst();
        }
        // 否则，遍历列表找到指定索引的前一个节点，并将其next指针指向下一个节点
        for (int i = 0; i < index - 1; i++) {
            SearchNode = SearchNode.next;
        }
        Node OldNode = SearchNode.next;
        SearchNode.next = SearchNode.next.next;
        size--;
        // 返回被删除的元素
        return OldNode.data;
    }

    // 删除第一个指定元素
    public boolean remove(E e) {
        // 记录是否删除成功
        boolean flag = false;
        // 如果列表为空，直接返回false
        if (isEmpty()) {
            return flag;
        }
        // 如果第一个节点就是要删除的节点，直接将头节点指向下一个节点
        Node SearchNode = first;
        if (SearchNode.data == e) {
            first = SearchNode.next;
            size--;
            flag = true;
            return flag;
        }
        // 否则，遍历列表找到要删除的节点，并将其前一个节点的next指针指向下一个节点
        for (int i = 0; i < size-1; i++) {
            if (SearchNode.next.data == e) {
                SearchNode.next = SearchNode.next.next;
                size--;
                flag = true;
                return flag;
            }
            SearchNode = SearchNode.next;
        }
        // 如果没有找到要删除的节点，返回false
        return flag;
    }

    // 删除所有指定元素
    public boolean removeAll(E e) {
        // 记录是否删除成功
        boolean flag = false;
        // 如果列表为空，直接返回false
        if (isEmpty()) {
            return flag;
        }
        // 否则，遍历列表找到要删除的节点，并将其前一个节点的next指针指向下一个节点
        Node SearchNode = first;
        while (SearchNode.data == e) {
            first = SearchNode.next;
            SearchNode = SearchNode.next;
            size--;
            flag = true;
        }
        // 遍历列表找到要删除的节点，并将其前一个节点的next指针指向下一个节点
        while(SearchNode.next != null) {
            if (SearchNode.next.data == e) {
                SearchNode.next = SearchNode.next.next;
                size--;
                flag = true;
            }
            SearchNode = SearchNode.next;
            if (SearchNode == null) {
                break;
            }
        }
        // 返回是否删除成功
        return flag;
    }

    // 删除第一个元素
    public E removeFirst() {
        Node OldFirst = first;
        first = OldFirst.next;
        size--;
        return OldFirst.data;
    }

    // 修改指定索引处的元素
    public E set(int index, E e) {
        // 如果索引无效，返回null
        if (index >= size || index < 0) {
            return null;
        }
        // 否则，遍历列表找到指定索引的节点，并修改其data值
        Node SearchNode = first;
        for (int i = 0; i < index; i++) {
            SearchNode = SearchNode.next;
        }
        E OldData = SearchNode.data;
        SearchNode.data = e;
        return OldData;
    }

    // 查找指定元素的第一个索引
    public int indexOf(E e) {
        // 记录索引
        int index = -1;
        // 遍历列表找到指定元素的第一个索引
        Node SearchNode = first;
        for (int i = 0; i < size; i++) {
            if (SearchNode.data == e) {
                index = i;
                break;
            }
            SearchNode = SearchNode.next;
        }
        // 找到了返回索引，没找到返回-1
        return index;
    }

    @Override
    public boolean contains(E element) {
        if (indexOf(element) == -1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(My_List<E> other) {
        if (other == null) {
            return false;
        }
        for (int i = 0; i < other.size(); i++) {
            add(other.get(i));
        }
        return true;
    }

    @Override
    public E[] toArray() {
        E[] array = (E[]) new Object[size];
        Node SearchNode = first;
        for (int i = 0; i < size; i++) {
            array[i] = SearchNode.data;
            SearchNode = SearchNode.next;
        }
        return array;
    }

    // 查找指定元素在列表中出现的次数
    public int count(E e) {
        // 记录出现次数
        int count = 0;
        // 遍历列表找到指定元素的出现次数
        Node SearchNode = first;
        for (int i = 0; i < size; i++) {
            if (SearchNode.data == e) {
                count++;
            }
            SearchNode = SearchNode.next;
        }
        // 返回出现次数
        return count;
    }

    // 判断列表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空列表
    public void clear() {
        first = null;
        size = 0;
    }

    // 将列表转化为字符串并返回
    public String toString() {
        String str = "";
        Node SearchNode = first;
        if (SearchNode == null) {
            str += "[]";
            return str;
        }
        str += "[";
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                str += SearchNode.data;
                break;
            }
            str += SearchNode.data + ", ";
            SearchNode = SearchNode.next;
        }
        str += "]";
        return str;
    }

    @Override
    public void forEach(Consumer<? super E> consumer) {
        for (Node SearchNode = first; SearchNode != null; SearchNode = SearchNode.next) {
            consumer.accept(SearchNode.data);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node current = first;
            @Override
            public boolean hasNext() {
                return current != null;
            }
            @Override
            public E next() {
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public Stream<E> Stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    // 内部节点类
    private class Node {
        private Node next;  // 指向下一个节点的指针
        private E data;     // 存储的数据
    }
}
