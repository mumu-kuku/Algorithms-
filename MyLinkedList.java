package mumu;

public class MyLinkedList<E> {
    private Node first;
    private Node last;
    private int size;

    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public MyLinkedList(MyLinkedList other) {
        for (int i = 0; i < other.size(); i++) {
            add((E) other.get(i));
        }
        size = other.size();
    }

    public int size() {
        return size;
    }

    public E getFirst() {
        return first.data;
    }

    public E getLast() {
        return last.data;
    }

    public E get(int index) {
        if (index >= size) {
            return null;
        }
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

        return SearchNode.data;
    }

    public void add(E e) {
        if (first == null) {
            first = new Node();
            first.data = e;
            first.next = null;
            first.prev = null;
            last = first;
            size++;
            return;
        }
        last.next = new Node();
        last.next.data = e;
        last.next.prev = last;
        last.next.next = null;
        last = last.next;
        size++;
    }

    public void add(int index, E e) {
        if (index == 0) {
            Node OldNode = first;
            Node NewNext = new Node();
            NewNext.data = e;
            NewNext.next = OldNode;
            first = NewNext;
            size++;
            return;
        }
        if (index == size - 1) {
            add(e);
        }
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
        Node NewNode = new Node();
        NewNode.data = e;
        NewNode.next = SearchNode;
        NewNode.prev = SearchNode.prev;
        SearchNode.prev.next = NewNode;
        SearchNode.prev = NewNode;
        size++;
    }

    public void addFirst(E e) {
        Node OldNode = first;
        Node NewNode = new Node();
        NewNode.data = e;
        NewNode.next = OldNode;
        OldNode.prev = NewNode;
        first = NewNode;
        size++;
    }

    public void addLast(E e) {
        add(e);
    }

    public E remove(int index) {
        if (index >= size) {
            return null;
        }

        Node SearchNode = new Node();
        if (index == 0) {
            return removeFirst();
        }
        else if (index == size -1) {
            return removeLast();
        }
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
        E removeData = SearchNode.data;
        SearchNode.prev.next = SearchNode.next;
        SearchNode.next.prev = SearchNode.prev;
        size--;
        return removeData;
    }
    public boolean remove(E e) {
        boolean flag = false;
        if (size == 0) {
            return flag;
        }
        Node SearchNode = first;
        if (SearchNode.data == e) {
            first = SearchNode.next;
            size--;
            flag = true;
        }
        for (int i = 0; i < size-1; i++) {
            if (SearchNode.next.data == e) {
                if (i == size - 2) {
                    last = SearchNode;
                    last.next = null;
                    size--;
                    flag = true;
                    break;
                }
                SearchNode.next = SearchNode.next.next;
                SearchNode.next.prev = SearchNode;
                size--;
                flag = true;
            }
            SearchNode = SearchNode.next;
        }
        return flag;
    }

    public E removeFirst() {
        Node OldFirst = first;
        first = OldFirst.next;
        E removeData = OldFirst.data;
        first.prev = null;
        size--;
        return removeData;
    }

    public E removeLast() {
        Node OldLast = last;
        last = OldLast.prev;
        E removeData = OldLast.data;
        last.next = null;
        size--;
        return removeData;
    }

    public boolean isEmpty() {
        if (first == null || size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

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

    private class Node {
        private Node next;
        private Node prev;
        private E data;
    }
}
