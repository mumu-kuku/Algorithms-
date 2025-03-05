package mumu;

public class MySinglyLinkedList<E> {
    private Node first;
    private int size;

    public MySinglyLinkedList() {
        first = null;
        size = 0;
    }

    public MySinglyLinkedList(MySinglyLinkedList other) {
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

    private Node getNodeFirst () {
        return first;
    }

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

    public void add(E e) {
        if (first == null) {
            first = new Node();
            first.data = e;
            first.next = null;
            size++;
            return;
        }
        Node SearchNode = first;
        for (int i = 0; i < size-1; i++) {
            SearchNode = SearchNode.next;
        }
        SearchNode.next = new Node();
        SearchNode.next.data = e;
        SearchNode.next.next = null;
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
        Node SearchNode = first;
        for (int i = 0; i < index - 1; i++) {
            SearchNode = SearchNode.next;
        }
        Node NewNext = new Node();
        NewNext.data = e;
        NewNext.next = SearchNode.next;
        SearchNode.next = NewNext;
        size++;
    }

    public void addFirst(E e) {
        Node OldNode = first;
        Node NewNext = new Node();
        NewNext.data = e;
        NewNext.next = OldNode;
        first = NewNext;
        size++;
    }

    public E remove(int index) {
        if (index >= size) {
            return null;
        }
        Node SearchNode = first;
        if (index == 0) {
            first = SearchNode.next;
            size--;
            return SearchNode.data;
        }
        for (int i = 0; i < index - 1; i++) {
            SearchNode = SearchNode.next;
        }
        Node OldNode = SearchNode.next;
        SearchNode.next = SearchNode.next.next;
        size--;
        return OldNode.data;
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
                SearchNode.next = SearchNode.next.next;
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
        size--;
        return OldFirst.data;
    }

    public boolean set(int index, E e) {
        if (index >= size) {
            return false;
        }
        Node SearchNode = first;
        for (int i = 0; i < index; i++) {
            SearchNode = SearchNode.next;
        }
        SearchNode.data = e;
        return true;
    }

    public int count(E e) {
        int count = 0;
        Node SearchNode = first;
        for (int i = 0; i < size; i++) {
            if (SearchNode.data == e) {
                count++;
            }
            SearchNode = SearchNode.next;
        }
        return count;
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
        private E data;
    }
}
