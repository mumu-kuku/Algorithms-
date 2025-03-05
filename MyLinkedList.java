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
        Node SearchNode = SearchIndex(index);
        if (SearchNode == null) {
            return null;
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

    public boolean add(int index, E e) {
        if (index == 0) {
            addFirst(e);
            return true;
        }
        else if (index == size) {
            addLast(e);
            return true;
        }
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
        return false;
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
        if (index == 0) {
            return removeFirst();
        }
        else if (index == size -1) {
            return removeLast();
        }
        Node SearchNode = SearchIndex(index);
        E removeData = null;
        if (SearchNode != null) {
            removeData = SearchNode.data;
            SearchNode.prev.next = SearchNode.next;
            SearchNode.next.prev = SearchNode.prev;
            size--;
        }
        return removeData;
    }

    public boolean remove(E e) {
        boolean flag = false;
        if (size == 0) {
            return flag;
        }
        Node SearchNode = first;
        while (SearchNode.data == e) {
            first = SearchNode.next;
            SearchNode = SearchNode.next;
            first.prev = null;
            size--;
            flag = true;
            return flag;
        }
        while(SearchNode != null) {
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
        return flag;

    }

    public boolean remove(E e, boolean all) {
        if (all) {
            boolean flag = false;
            if (size == 0) {
                return flag;
            }
            Node SearchNode = first;
            while (SearchNode.data == e) {
                first = SearchNode.next;
                SearchNode = SearchNode.next;
                first.prev = null;
                size--;
                flag = true;
            }
            while(SearchNode != null) {
                if (SearchNode.data == e) {
                    if (SearchNode == last) {
                        last = SearchNode.prev;
                        last.next = null;
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
            return flag;
        }
        return remove(e);
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

    public E set(int index, E e) {
        Node SearchNode = SearchIndex(index);
        E removeData = null;
        if (SearchNode != null) {
            removeData = SearchNode.data;
            SearchNode.data = e;
        }
        return removeData;
    }

    public int count(E e) {
        int count = 0;
        Node SearchNode = first;
        while (SearchNode!= null) {
            if (SearchNode.data == e) {
                count++;
            }
            SearchNode = SearchNode.next;
        }
        return count;
    }

    public int indexOf(E e) {
        int index = -1;
        Node SearchNode = first;
        for (int i = 0; i < size; i++) {
            if (SearchNode.data == e) {
                index = i;
                break;
            }
            SearchNode = SearchNode.next;
        }
        return index;
    }
    public int indexOf(E e, int startIndex) {
        int index = -1;
        Node SearchNode = SearchIndex(startIndex);
        for (int i = startIndex; i < size; i++) {
            if (SearchNode.data == e) {
                index = i;
                break;
            }
            SearchNode = SearchNode.next;
        }
        return index;
    }

    public int lastIndexOf(E e) {
        int index = -1;
        Node SearchNode = last;
        for (int i = size -1; i >= 0; i--) {
            if (SearchNode.data == e) {
                index = i;
                break;
            }
            SearchNode = SearchNode.prev;
        }
        return index;
    }

    public boolean contains(E e) {
        Node SearchNode = first;
        while (SearchNode!= null) {
            if (SearchNode.data == e) {
                return true;
            }
            SearchNode = SearchNode.next;
        }
        return false;
    }

    private Node SearchIndex(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        else if (index == 0) {
            return first;
        }
        else if (index == size - 1) {
            return last;
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

        return SearchNode;
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
