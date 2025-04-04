package Linear;

// 手写实现基于环形链表的列表
public class MyCircularLinkedList <E> {
    private Node first;     // 链表的头节点
    private Node last;      // 链表的尾节点
    private int size;       // 链表的大小

    // 无参构造方法
    public MyCircularLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    // 复制构造方法
    public MyCircularLinkedList(MyCircularLinkedList other) {
        Node SearchNode = other.first;
        size = other.size();
        for (int i = 0; i < other.size(); i++) {
            add(SearchNode.data);
            SearchNode = SearchNode.next;
        }
    }

    // 返回链表的大小
    public int size() {
        return size;
    }

    // 获取链表的第一个元素
    public E getFirst() {
        return first.data;
    }

    // 获取链表的最后一个元素
    public E getLast() {
        return last.data;
    }

    // 获取链表中指定索引位置的元素
    public E get(int index) {
        // 查找该索引位置的节点
        Node SearchNode = SearchIndex(index);
        // 如果节点为空，则返回null
        if (SearchNode == null) {
            return null;
        }
        // 不为空返回节点的数据
        return SearchNode.data;
    }

    // 在链表末尾添加一个元素
    public void add(E e) {
        // 如果链表为空，则将新节点设置为第一个节点
        if (first == null) {
            first = new Node();
            first.data = e;
            first.next = null;
            first.prev = null;
            last = first;
            size++;
            return;
        }
        // 如果链表不为空，则将新节点添加到链表末尾
        last.next = new Node();
        last.next.data = e;
        last.next.prev = last;
        last.next.next = first;
        last = last.next;
        first.prev = last;
        size++;
    }

    // 在链表的指定位置插入一个元素
    public boolean add(int index, E e) {
        // 如果索引位置为0，则将新节点插入到链表头部
        if (index == 0) {
            addFirst(e);
            return true;
        }
        // 如果索引位置等于链表长度，则将新节点插入到链表尾部
        else if (index == size) {
            addLast(e);
            return true;
        }
        // 如果索引位置在链表中间，则在指定位置插入新节点
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
        // 如果索引位置无效，则返回false
        return false;
    }

    // 在链表的头部添加一个元素
    public void addFirst(E e) {
        Node OldNode = first;
        Node NewNode = new Node();
        NewNode.data = e;
        NewNode.next = OldNode;
        NewNode.prev = last;
        OldNode.prev = NewNode;
        first = NewNode;
        size++;
    }

    // 在链表的尾部添加一个元素
    public void addLast(E e) {
        add(e);
    }

    // 删除链表中指定索引位置的元素
    public E remove(int index) {
        // 如果索引位置为0，则删除链表头部的元素
        if (index == 0) {
            return removeFirst();
        }
        // 如果索引位置等于链表长度减1，则删除链表尾部的元素
        else if (index == size -1) {
            return removeLast();
        }
        // 获取指定索引位置的节点
        Node SearchNode = SearchIndex(index);
        E removeData = null;
        // 如果节点不为空，则删除该节点
        if (SearchNode != null) {
            removeData = SearchNode.data;
            SearchNode.prev.next = SearchNode.next;
            SearchNode.next.prev = SearchNode.prev;
            size--;
        }
        // 返回被删除的元素或null
        return removeData;
    }

    // 删除链表中第一个出现的指定元素
    public boolean remove(E e) {
        // 记录是否成功删除元素的标志位
        boolean flag = false;
        // 如果链表为空，则直接返回false
        if (size == 0) {
            return flag;
        }
        // 如果链表不为空，则遍历链表，查找并删除第一个出现的指定元素
        Node SearchNode = first;
        // 如果第一个节点就是要删除的节点，直接删除
        if (SearchNode.data == e) {
            first = SearchNode.next;
            SearchNode = SearchNode.next;
            first.prev = last;
            last.next = first;
            size--;
            flag = true;
            return flag;
        }
        // 遍历链表，查找并删除第一个出现的指定元素
        while(SearchNode != null) {
            if (SearchNode.data == e) {
                // 如果要删除的节点是最后一个节点，需要特殊处理
                if (SearchNode == last) {
                    last = SearchNode.prev;
                    last.next = first;
                    first.prev = last;
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
        // 如果没有找到要删除的节点，则返回false
        return flag;
    }

    // 删除链表中所有出现的指定元素
    public boolean removeAll(E e) {
        // 记录是否成功删除元素的标志位
        boolean flag = false;
        // 如果链表为空，则直接返回false
        if (size == 0) {
            return flag;
        }
        // 如果链表不为空，则遍历链表，查找并删除所有出现的指定元素
        Node SearchNode = first;
        // 如果第一个节点就是要删除的节点，还要继续判断新的第一个节点是否为要删除的节点
        while (SearchNode.data == e) {
            first = SearchNode.next;
            SearchNode = SearchNode.next;
            first.prev = last;
            last.next = first;
            size--;
            flag = true;
        }
        // 遍历链表，查找并删除所有出现的指定元素
        for (int i = 0; i < size; i++) {
            if (SearchNode.data == e) {
                // 如果要删除的节点是最后一个节点，需要特殊处理
                if (SearchNode == last) {
                    last = SearchNode.prev;
                    last.next = first;
                    first.prev = last;
                    size--;
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
        // 返回是否成功删除元素的标志位
        return flag;
    }

    // 删除链表中第一个元素
    public E removeFirst() {
        Node OldFirst = first;
        first = OldFirst.next;
        E removeData = OldFirst.data;
        first.prev = last;
        last.next = first;
        size--;
        // 返回第一个元素的数据
        return removeData;
    }

    // 删除链表中最后一个元素
    public E removeLast() {
        Node OldLast = last;
        last = OldLast.prev;
        E removeData = OldLast.data;
        last.next = first;
        first.prev = last;
        size--;
        // 返回最后一个元素的数据
        return removeData;
    }

    // 替换链表中指定索引位置的元素
    public E set(int index, E e) {
        // 获取指定索引位置的节点
        Node SearchNode = SearchIndex(index);
        E removeData = null;
        // 如果节点不为空，则替换该节点的数据
        if (SearchNode != null) {
            removeData = SearchNode.data;
            SearchNode.data = e;
        }
        // 返回被替换的元素或null
        return removeData;
    }

    // 查找链表中指定元素的数量
    public int count(E e) {
        // 初始化计数器
        int count = 0;
        // 遍历链表，查找并统计指定元素的数量
        Node SearchNode = first;
        for (int i = 0; i < size; i++) {
            if (SearchNode.data == e) {
                count++;
            }
            SearchNode = SearchNode.next;
        }
        // 返回计数器
        return count;
    }

    // 查找链表中第一个出现的指定元素的索引位置
    public int indexOf(E e) {
        // 记录元素的索引位置
        int index = -1;
        // 遍历链表，查找并记录第一个出现的指定元素的索引位置
        Node SearchNode = first;
        for (int i = 0; i < size; i++) {
            if (SearchNode.data == e) {
                index = i;
                break;
            }
            SearchNode = SearchNode.next;
        }
        // 找到了指定元素，则返回索引位置；否则返回-1
        return index;
    }

    // 查找链表中指定元素的索引位置，从指定的起始位置开始查找
    public int indexOf(E e, int startIndex) {
        // 记录元素的索引位置
        int index = -1;
        // 获取指定起始位置的节点
        Node SearchNode = SearchIndex(startIndex);
        for (int i = startIndex; i < size; i++) {
            if (SearchNode.data == e) {
                index = i;
                break;
            }
            SearchNode = SearchNode.next;
        }
        // 找到了指定元素，则返回索引位置；否则返回-1
        return index;
    }

    // 查找链表中最后一个出现的指定元素的索引位置
    public int lastIndexOf(E e) {
        // 记录元素的索引位置
        int index = -1;
        // 从链表的末尾开始遍历链表，查找并记录最后一个出现的指定元素的索引位置
        Node SearchNode = last;
        for (int i = size -1; i >= 0; i--) {
            if (SearchNode.data == e) {
                index = i;
                break;
            }
            SearchNode = SearchNode.prev;
        }
        // 找到了指定元素，则返回索引位置；否则返回-1
        return index;
    }

    // 判断链表中是否包含指定元素
    public boolean contains(E e) {
        // 遍历链表，查找是否包含指定元素
        Node SearchNode = first;
        for (int i = 0; i < size; i++) {
            // 如果找到了指定元素，则返回true
            if (SearchNode.data == e) {
                return true;
            }
            SearchNode = SearchNode.next;
        }
        // 没有找到指定元素，则返回false
        return false;
    }

    // 查找链表中指定索引位置的节点
    private Node SearchIndex(int index) {
        // 如果索引位置无效，则返回null
        if (index >= size || index < 0) {
            return null;
        }
        // 如果索引位置为0，则返回第一个节点；如果索引位置为链表长度减1，则返回最后一个节点
        else if (index == 0) {
            return first;
        }
        else if (index == size - 1) {
            return last;
        }

        // 如果索引位置在链表中间，判断索引位置在前半部分还是后半部分，然后从对应的方向开始查找节点
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

        // 返回找到的节点
        return SearchNode;
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空链表
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    // 将链表转换为字符串表示形式
    public String toString() {
        String str = "";
        Node SearchNode = first;
        if (SearchNode == null) {
            str += "[]";
            return str;
        }
        str += "[";
        for (int i = 0; i < size; i++) {
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

    // 定义链表节点的内部类
    private class Node {
        private Node next;  // 指向下一个节点的指针
        private Node prev;  // 指向上一个节点的指针
        private E data;     // 存储节点的数据
    }
}
