package Nonlinear;

// 手写实现基于开放寻址数组实现的哈希表
public class HashMapOpenAddressing <K, V> {
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 100;
    private static final double LOAD_FACTOR = 0.75;
    private static final int extendRatio = 2;
    private Pair[] buckets;
    private static final Pair TOMBSTONE = new Pair(null, null);

    public HashMapOpenAddressing() {
        capacity = DEFAULT_CAPACITY;
        buckets = new Pair[capacity];
        size = 0;
    }

    public HashMapOpenAddressing(int capacity) {
        this.capacity = capacity;
        buckets = new Pair[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    private int hash(K key) {
        return key.hashCode() % capacity;
    }

    private double loadFactor() {
        return (double) size / capacity;
    }

    private void extend() {
        capacity *= extendRatio;
        Pair[] bucketsTmp = buckets;
        buckets = new Pair[capacity];
        size = 0;
        for (int i = 0; i < bucketsTmp.length; i++) {
            if (bucketsTmp[i] != null && bucketsTmp[i] != TOMBSTONE) {
                put((K) bucketsTmp[i].key, (V) bucketsTmp[i].value);
            }
        }
    }

    // 搜索key对应的索引
    private int findIndex(K key)  {
        // 计算key对应的索引
        int index = hash(key);
        // 线性探测, 遇到空桶或者key相同的桶就停止
        int firstTombstoneIndex = -1;
        while (buckets[index] != null) {
            // 若找到了key对应的索引
            if (buckets[index].key.equals(key)) {
                // 如果遇到一个被删除的桶，则把当前桶的值赋给它，并返回它的索引
                if (firstTombstoneIndex != -1) {
                    // 把当前桶的值赋给被删除的桶
                    buckets[firstTombstoneIndex] = buckets[index];
                    // 把当前桶的值设为被删除的桶
                    buckets[index] = TOMBSTONE;
                    return firstTombstoneIndex;
                }
                // 如果没有遇到被删除的桶，则返回当前桶的索引
                return index;
            }
            // 若遇到一个被删除的桶，则记录它的索引,若已经遇到过被删除的桶，则不记录，确保是第一个被删除的桶
            if (firstTombstoneIndex == -1 && buckets[index] == TOMBSTONE) {
                firstTombstoneIndex = index;
            }
            // 循环到下一个桶，若越过最后一个桶，则从第一个桶开始
            index = (index + 1) % capacity;
        }
        // 若循环结束，没有找到key，则返回第一个被删除的桶的索引或当前的空桶的索引
        return firstTombstoneIndex == -1 ? index : firstTombstoneIndex;
    }

    public void put(K key, V value) {
        // 判断是否需要扩容
        if (loadFactor() > LOAD_FACTOR) {
            extend();
        }
        // 搜索key对应的索引
        int index = findIndex(key);
        // 若找到key，则更新value
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            buckets[index].value = value;
            return;
        }
        // 若没有找到key，则插入
        buckets[index] = new Pair(key, value);
        size++;
    }

    public V remove(K key) {
        int index = findIndex(key);
        if (buckets[index] == null || buckets[index] == TOMBSTONE) {
            return null;
        }
        V value = (V) buckets[index].value;
        buckets[index] = TOMBSTONE;
        size--;
        return value;
    }

    public V get(K key) {
        int index = findIndex(key);
        if (buckets[index] == null || buckets[index] == TOMBSTONE) {
            return null;
        }
        return (V) buckets[index].value;
    }

    public void Print() {
        if (size == 0) {
            System.out.println("{}");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null && buckets[i] != TOMBSTONE) {
                sb.append(buckets[i].key + " -> " + buckets[i].value + ", ");
            }
        }
        sb.delete(sb.length() - 3, sb.length()-1);
        sb.append(" }");
        System.out.println(sb.toString());
    }
}
