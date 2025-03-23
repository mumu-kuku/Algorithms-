package Linear;

// 手写实现基于双向链表的列表
public class MyLinkedList<E> {
    private Node first;     // 头节点
    private Node last;      // 尾节点
    private int size;       // 列表大小

    // 无参构造方法
    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    // 传入一个MyLinkedList对象作为参数的构造方法
    public MyLinkedList(MyLinkedList other) {
        Node SearchNode = other.first;
        while (SearchNode!= null) {
            add(SearchNode.data);
            SearchNode = SearchNode.next;
        }
        size = other.size();
    }

    // 返回列表的大小
    public int size() {
        return size;
    }

    // 获取列表的第一个元素
    public E getFirst() {
        return first.data;
    }

    // 获取列表的最后一个元素
    public E getLast() {
        return last.data;
    }

    // 获取指定索引位置的元素
    public E get(int index) {
        // 获取指定索引位置的节点
        Node SearchNode = SearchIndex(index);
        // 如果节点为空，返回null
        if (SearchNode == null) {
            return null;
        }
        // 返回节点的数据
        return SearchNode.data;
    }

    // 添加一个元素到列表的末尾
    public void add(E e) {
        // 如果列表为空，将头节点和尾节点都指向新节点
        if (first == null) {
            first = new Node();
            first.data = e;
            first.next = null;
            first.prev = null;
            last = first;
            size++;
            return;
        }
        // 将新节点添加到链表的尾节点之后
        last.next = new Node();
        last.next.data = e;
        last.next.prev = last;
        last.next.next = null;
        last = last.next;
        size++;
    }

    // 在指定索引位置插入一个元素
    public boolean add(int index, E e) {
        // 如果索引等于0，将新节点添加到链表的头节点之前
        if (index == 0) {
            addFirst(e);
            return true;
        }
        // 如果索引等于列表的大小，将新节点添加到链表的尾节点之后
        else if (index == size) {
            addLast(e);
            return true;
        }
        // 获取指定索引位置的节点
        Node SearchNode = SearchIndex(index);
        if (SearchNode != null) {
            Node NewNode = new Node();
            NewNode.data = e;
            NewNode.next = SearchNode;
            NewNode.prev = SearchNode.prev;
            SearchNode.prev.next = NewNode;
            SearchNode.prev = NewNode;
            size++;
            return true;
        }
        // 指定索引位置的节点为空，返回false
        return false;
    }

    // 在列表的开头添加一个元素
    public void addFirst(E e) {
        Node OldNode = first;
        Node NewNode = new Node();
        NewNode.data = e;
        NewNode.next = OldNode;
        OldNode.prev = NewNode;
        first = NewNode;
        size++;
    }

    // 在列表的末尾添加一个元素
    public void addLast(E e) {
        add(e);
    }

    // 删除指定索引位置的元素
    public E remove(int index) {
        // 如果索引等于0，调取removeFirst方法删除头节点
        if (index == 0) {
            return removeFirst();
        }
        // 如果索引等于列表的大小减1，调取removeLast方法删除尾节点
        else if (index == size -1) {
            return removeLast();
        }
        // 获取指定索引位置的节点
        Node SearchNode = SearchIndex(index);
        E removeData = null;
        if (SearchNode != null) {
            removeData = SearchNode.data;
            SearchNode.prev.next = SearchNode.next;
            SearchNode.next.prev = SearchNode.prev;
            size--;
        }
        // 返回指定索引位置的节点的数据或者null
        return removeData;
    }

    // 删除列表中的第一个指定元素
    public boolean remove(E e) {
        // 记录是否删除成功
        boolean flag = false;
        // 如果列表为空，返回false
        if (isEmpty()) {
            return flag;
        }
        // 如果头节点的数据等于指定元素，调用removeFirst方法删除头节点
        Node SearchNode = first;
        if (SearchNode.data == e) {
            removeFirst();
            flag = true;
            return flag;
        }
        // 遍历链表，查找指定元素
        while(SearchNode != null) {
            // 如果找到指定元素，删除该节点
            if (SearchNode.data == e) {
                if (SearchNode == last) {
                    last = SearchNode.prev;
                    last.next = null;
                    size--;
                    flag = true;
                    return flag;
                }
                SearchNode.next.prev = SearchNode.prev;
                SearchNode.prev.next = SearchNode.next;
                size--;
                flag = true;
                return flag;
            }
            SearchNode = SearchNode.next;
        }
        // 遍历完链表，没有找到指定元素，返回false
        return flag;

    }

    // 删除列表中的所有指定元素
    public boolean removeAll(E e) {
        // 记录是否删除成功
        boolean flag = false;
        // 如果列表为空，返回false
        if (isEmpty()) {
            return flag;
        }
        Node SearchNode = first;
        // 如果头节点的数据等于指定元素还要继续遍历，直到头节点的数据不等于指定元素
        while (SearchNode.data == e) {
            removeFirst();
            flag = true;
        }
        // 遍历链表，查找指定元素
        while(SearchNode != null) {
            // 如果找到指定元素，删除该节点
            if (SearchNode.data == e) {
                // 如果该节点是尾节点，更新尾节点
                if (SearchNode == last) {
                    removeLast();
                    flag = true;
                    break;
                }
                SearchNode.next.prev = SearchNode.prev;
                SearchNode.prev.next = SearchNode.next;
                size--;
                flag = true;
            }
            SearchNode = SearchNode.next;
        }
        // 遍历完链表，没有找到指定元素，返回false,找到了返回true
        return flag;
    }

    // 删除列表中的第一个元素
    public E removeFirst() {
        // 如果列表为空，返回null
        if (isEmpty()) {
            return null;
        }
        Node OldFirst = first;
        first = OldFirst.next;
        E removeData = OldFirst.data;
        // 如果新的头节点不为空，更新头节点的prev指针
        if (first != null)
            first.prev = null;
        size--;
        // 返回被删除的元素
        return removeData;
    }

    // 删除列表中的最后一个元素
    public E removeLast() {
        // 如果列表为空，返回null
        if (isEmpty()) {
            return null;
        }
        Node OldLast = last;
        last = OldLast.prev;
        E removeData = OldLast.data;
        // 如果新的尾节点不为空，更新尾节点的next指针
        if (last!= null)
            last.next = null;
        size--;
        // 返回被删除的元素
        return removeData;
    }

    // 修改指定索引位置的元素
    public E set(int index, E e) {
        // 获取指定索引位置的节点
        Node SearchNode = SearchIndex(index);
        E removeData = null;
        // 如果节点不为空，修改节点的数据
        if (SearchNode != null) {
            removeData = SearchNode.data;
            SearchNode.data = e;
        }
        // 返回被修改的元素或者null
        return removeData;
    }

    // 查找指定元素在列表中的出现次数
    public int count(E e) {
        // 记录出现次数
        int count = 0;
        // 遍历链表，查找指定元素
        Node SearchNode = first;
        while (SearchNode!= null) {
            if (SearchNode.data == e) {
                count++;
            }
            SearchNode = SearchNode.next;
        }
        // 返回出现次数
        return count;
    }

    // 查找指定元素在列表中第一次出现的索引位置
    public int indexOf(E e) {
        // 记录索引位置
        int index = -1;
        // 遍历链表，查找指定元素
        Node SearchNode = first;
        for (int i = 0; i < size; i++) {
            if (SearchNode.data == e) {
                index = i;
                break;
            }
            SearchNode = SearchNode.next;
        }
        // 返回索引位置或者-1
        return index;
    }

    // 查找指定元素在指定起始索引位置之后第一次出现的索引位置
    public int indexOf(E e, int startIndex) {
        // 记录索引位置
        int index = -1;
        // 遍历链表，查找指定元素
        Node SearchNode = SearchIndex(startIndex);
        for (int i = startIndex; i < size; i++) {
            if (SearchNode.data == e) {
                index = i;
                break;
            }
            SearchNode = SearchNode.next;
        }
        // 返回索引位置或者-1
        return index;
    }

    // 查找指定元素在列表中最后一次出现的索引位置
    public int lastIndexOf(E e) {
        // 记录索引位置
        int index = -1;
        Node SearchNode = last;
        // 遍历链表，查找指定元素
        for (int i = size -1; i >= 0; i--) {
            if (SearchNode.data == e) {
                index = i;
                break;
            }
            SearchNode = SearchNode.prev;
        }
        // 返回索引位置或者-1
        return index;
    }

    // 判断列表中是否包含指定元素
    public boolean contains(E e) {
        // 遍历链表，查找指定元素
        Node SearchNode = first;
        while (SearchNode!= null) {
            if (SearchNode.data == e) {
                return true;
            }
            SearchNode = SearchNode.next;
        }
        // 遍历完链表，没有找到指定元素，返回false
        return false;
    }

    // 查找指定索引位置的节点
    private Node SearchIndex(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (isEmpty()) {
            return null;
        }
        // 如果索引等于0，返回头节点
        else if (index == 0) {
            return first;
        }
        // 如果索引等于列表的大小减1，返回尾节点
        else if (index == size - 1) {
            return last;
        }

        // 如果索引小于列表的大小的一半，从前往后遍历链表
        // 如果索引大于列表的大小的一半，从后往前遍历链表
        Node SearchNode = new Node();
        if (index <= size/2) {
            SearchNode = first;
            for (int i = 0; i < index; i++) {
                SearchNode = SearchNode.next;
            }
        }
        else {
            SearchNode = last;
            int count = size -1 - index;
            for (int i = 0; i < count; i++) {
                SearchNode = SearchNode.prev;
            }
        }

        // 返回指定索引位置的节点
        return SearchNode;
    }

    // 判断列表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空列表
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    // 返回列表的字符串表示
    public String toString() {
        String str = "";
        Node SearchNode = first;
        if (SearchNode == null) {
            str += "[]";
            return str;
        }
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

    // 节点类
    private class Node {
        private Node next;      // 记录下一个节点的指针
        private Node prev;      // 记录前一个节点的指针
        private E data;         // 记录节点的数据
    }
}
