package Nonlinear;

import Linear.MyArrayList;

// 手写实现基于数组实现的哈希表
public class ArrayHashMap <K, V> {
    private MyArrayList<Pair> buckets;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 100;

    public ArrayHashMap() {
        capacity = DEFAULT_CAPACITY;
        buckets = new MyArrayList<Pair>();
        for (int i = 0; i < capacity; i++) {
            buckets.add(null);
        }
        size = 0;
    }

    private int hash(K key) {
        return key.hashCode() % capacity;
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        Pair pair = new Pair(key, value);
        int index = hash(key);
        buckets.set(index, pair);
        size++;
    }

    public V remove(K key) {
        int index = hash(key);
        Pair pair = (Pair) buckets.get(index);
        V value = (V) pair.value;
        buckets.set(index, null);
        size--;

        return value;
    }

    public V get(K key) {
        int index = hash(key);
        Pair pair = (Pair) buckets.get(index);
        if (pair == null) {
            return null;
        }
        return (V) pair.value;
    }

    public MyArrayList<Pair> pairSet() {
        MyArrayList<Pair> pairSet = new MyArrayList<>();
        for (int i = 0; i < buckets.size(); i++) {
            if (buckets.get(i) != null) {
                pairSet.add(buckets.get(i));
            }
        }

        return pairSet;
    }

    public MyArrayList<K> keySet() {
        MyArrayList<K> keySet = new MyArrayList<>();
        for (int i = 0; i < buckets.size(); i++) {
            if (buckets.get(i) != null) {
                keySet.add((K) buckets.get(i).key);
            }
        }

        return keySet;
    }

    public MyArrayList<V> valueSet() {
        MyArrayList<V> valueSet = new MyArrayList<>();
        for (int i = 0; i < buckets.size(); i++) {
            if (buckets.get(i) != null) {
                valueSet.add((V) buckets.get(i).value);
            }
        }

        return valueSet;
    }

    public void print() {
        if (size == 0) {
            System.out.println("{}");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (int i = 0; i < buckets.size(); i++) {
            if (buckets.get(i) != null) {
                sb.append(buckets.get(i).key + " -> " + buckets.get(i).value + ", ");
            }
        }
        sb.delete(sb.length() - 3, sb.length()-1);
        sb.append(" }");
        System.out.println(sb.toString());
    }
}
