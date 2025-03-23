package Nonlinear;

import Linear.MyArrayList;

// 抽象类Heap 堆
public abstract class Heap<E> {
    protected MyArrayList<E> heap;

    public Heap() {
        heap = new MyArrayList<E>();
    }

    public Heap(MyArrayList<E> nums) {
        heap = new MyArrayList<>(nums);
        for (int i = parent(size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int left(int i) { return 2 * i + 1; }
    public int right(int i) { return 2 * i + 2; }
    public int parent(int i) { return (i - 1) / 2; }

    protected abstract void siftUp(int i);
    protected abstract void siftDown(int i);

    protected void swap(int i, int j) {
        if (i == j) return;
        if (i < 0 || i >= heap.size()) return;
        if (j < 0 || j >= heap.size()) return;
        E tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }
    public int size() { return heap.size(); };
    public void push(E e) {
        heap.add(e);
        siftUp(heap.size() - 1);
    };
    public E pop() {
        if (isEmpty()) return null;
        swap(0, size() - 1);
        E val = heap.remove(size() - 1);
        siftDown(0);
        return val;
    };
    public E peek() {return heap.get(0);};
    public boolean isEmpty() { return heap.isEmpty();};
}
