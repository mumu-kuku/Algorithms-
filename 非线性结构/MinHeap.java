package Nonlinear;

import Linear.MyArrayList;

// 小顶堆实现
public class MinHeap <E> extends Heap<E> {
    public MinHeap() {
        super();
    }

    public MinHeap(MyArrayList<E> nums) {
        super(nums);
    }

    @Override
    protected void siftUp(int i) {
        while (true) {
            int p = parent(i);
            if (p < 0 || heap.get(p).hashCode() <= heap.get(i).hashCode())
                break;
            swap(p, i);
            i = p;
        }
    }

    @Override
    protected void siftDown(int i) {
        while (true) {
            int l = left(i), r = right(i), ma = i;
            if (l < size() && heap.get(l).hashCode() < heap.get(ma).hashCode())
                ma = l;
            if (r < size() && heap.get(r).hashCode() < heap.get(ma).hashCode())
                ma = r;
            if (ma == i)
                break;
            swap(i, ma);
            i = ma;
        }
    }
}
