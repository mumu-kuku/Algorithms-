package Nonlinear;

import Linear.MyArrayList;

// 手写实现基于数组和仿链表数组实现的哈希表
public class ArrayHashMapChaining<K, V> {
    private MyArrayList<MyArrayList<Pair>> buckets;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;
    private static final double LOAD_FACTOR = 0.75;
    private static final int EXTEND_RATIO = 2;

    public ArrayHashMapChaining() {
        capacity = DEFAULT_CAPACITY;
        size = 0;
        buckets = new MyArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new MyArrayList<>());
        }
    }

    private int hash(K key) {
        return key.hashCode() % capacity;
    }

    public double loadFactor() {
        return (double) size / capacity;
    }

    public void put(K key, V value) {
        if (loadFactor() > LOAD_FACTOR) {
            // 如果负载因子大于阈值，则进行扩容
            extend();
        }
        int index = hash(key);
        MyArrayList<Pair> bucket = (MyArrayList) buckets.get(index);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key.equals(key)) {
                bucket.get(i).value = value;
                return;
            }
        }
        Pair pair = new Pair(key, value);
        bucket.add(pair);
        size++;
    }

    public V remove(K key) {
        int index = hash(key);
        MyArrayList<Pair> bucket = (MyArrayList) buckets.get(index);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key.equals(key)) {
                V value = (V) bucket.get(i).value;
                bucket.remove(i);
                size--;
                return value;
            }
        }
        return null;
    }

    public V get(K key) {
        int index = hash(key);
        // 遍历链表，找到对应的键值对
        MyArrayList<Pair> bucket = (MyArrayList) buckets.get(index);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key.equals(key)) {
                // 返回对应的值
                return (V) bucket.get(i).value;
            }
        }
        // 如果没有找到对应的键值对，返回null
        return null;
    }

    private void extend() {
        MyArrayList<MyArrayList<Pair>> bucketsTmp = buckets;
        capacity *= EXTEND_RATIO;

        MyArrayList<MyArrayList<Pair>> buckets = new MyArrayList<>(capacity);
        for (int i = 0; i < buckets.size(); i++) {
            buckets.add(new MyArrayList<>());
        }

        for (int i = 0; i < bucketsTmp.size(); i++) {
            MyArrayList<Pair> bucket = buckets.get(i);
            for (int j = 0; j < bucket.size(); j++) {
                put((K) bucket.get(j).key, (V) bucket.get(j).value);
            }
        }
    }

    public MyArrayList<Pair> pairSet() {
        MyArrayList<Pair> pairSet = new MyArrayList<>();
        for (int i = 0; i < buckets.size(); i++) {
            MyArrayList<Pair> bucket = buckets.get(i);
            for (int j = 0; j < bucket.size(); j++) {
                pairSet.add(bucket.get(j));
            }
        }

        return pairSet;
    }

    public MyArrayList<K> keySet() {
        MyArrayList<K> keySet = new MyArrayList<>();
        for (int i = 0; i < buckets.size(); i++) {
            MyArrayList<Pair> bucket = buckets.get(i);
            for (int j = 0; j < bucket.size(); j++) {
                keySet.add((K) bucket.get(j).key);
            }
        }

        return keySet;
    }

    public MyArrayList<V> valueSet() {
        MyArrayList<V> valueSet = new MyArrayList<>();
        for (int i = 0; i < buckets.size(); i++) {
            MyArrayList<Pair> bucket = buckets.get(i);
            for (int j = 0; j < bucket.size(); j++) {
                valueSet.add((V) bucket.get(j).value);
            }
        }

        return valueSet;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (int i = 0; i < buckets.size(); i++) {
            MyArrayList<Pair> bucket = buckets.get(i);
            for (int j = 0; j < bucket.size(); j++) {
                sb.append(bucket.get(j).key + " -> " + bucket.get(j).value + ", ");
            }
        }
        sb.delete(sb.length() - 3, sb.length()-1);
        sb.append(" }");
        System.out.println(sb.toString());
    }
}
