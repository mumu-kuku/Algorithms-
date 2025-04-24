package Algorithm;

import Nonlinear.AVLTree;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * 这是一个实现各类查找算法的工具类
 * 包含以下查找算法：
 *  1.线性查找 LinearSearch(E[] elements, E target)
 *  2.二分查找(双闭区间) BinarySearch(E[] elements, E target)
 *  3.二分查找(左闭右开) BinarySearchLCRO(E[] elements, E target)
 *  4.二分查找插入点(无重复元素) BinarySearchInsertionSimple(E[] elements, E target)
 *  5.二分查找插入点(有重复元素) BinarySearchInsertion(E[] elements, E target)
 *  6.二分查找左边界 BinarySearchLeftBound(E[] elements, E target)
 *  7.二分查找右边界 BinarySearchRightBound(E[] elements, E target)
 *  8.哈希表查找 HashTableSearch(E[] elements, E target)
 *  9.二叉树查找 BinaryTreeSearch(E[] elements, E target)
 */
public class Find<E> {
    private Find() {}
    /**
     * 线性查找
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public static <E extends Comparable<E>> int LinearSearch(E[] elements, E target) {
        if (elements == null || elements.length == 0) return -1;
        for (int i = 0; i < elements.length; ++i) {
            if (elements[i].compareTo(target) == 0) return i;
        }
        return -1;
    }

    /**
     * 二分查找(双闭区间)
     * 时间复杂度O(logn)
     * 空间复杂度O(1)
     */
    public static <E extends Comparable<E>> int BinarySearch(E[] elements, E target) {
        if (elements == null || elements.length == 0) return -1;
        int left = 0, right = elements.length - 1;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            int cmp = elements[mid].compareTo(target);
            if (cmp == 0) {
                return mid;
            } else if (cmp > 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找(左闭右开)
     * 时间复杂度O(logn)
     * 空间复杂度O(1)
     */
    public static <E extends Comparable<E>> int BinarySearchLCRO(E[] elements, E target) {
        if (elements == null || elements.length == 0) return -1;
        int left = 0, right = elements.length;
        while (right > left) {
            int mid = left + (right - left) / 2;
            int cmp = elements[mid].compareTo(target);
            if (cmp == 0)
                return mid;
            else if (cmp > 0)
                left = mid + 1;
            else
                right = mid;
        }
        return -1;
    }

    /**
     * 二分查找插入点(无重复元素)
     * 时间复杂度O(logn)
     * 空间复杂度O(1)
     */
    public static <E extends Comparable<E>> int BinarySearchInsertionSimple(E[] elements, E target) {
        int left = 0, right = elements.length - 1;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            int cmp = elements[mid].compareTo(target);
            if (cmp == 0)
                return mid;
            else if (cmp > 0)
                left = mid - 1;
            else
                right = mid + 1;
        }
        return left;
    }

    /**
     * 二分查找插入点(有重复元素)
     * 时间复杂度O(logn)
     * 空间复杂度O(1)
     */
    public static <E extends Comparable<E>> int BinarySearchInsertion(E[] elements, E target) {
        int left = 0, right = elements.length - 1;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            int cmp = elements[mid].compareTo(target);
            if (cmp > 0)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }

    /**
     * 二分查找左边界
     * 时间复杂度O(logn)
     * 空间复杂度O(1)
     */
     public static <E extends Comparable<E>> int BinarySearchLeftBound(E[] elements, E target) {
         int res = BinarySearchInsertion(elements, target);
         if (res == elements.length || elements[res].compareTo(target) != 0) return -1;
         return res;
     }

    /**
     * 二分查找右边界
     * 时间复杂度O(logn)
     * 空间复杂度O(1)
     */
    public static <E extends Comparable<E>> int BinarySearchRightBound(E[] elements, E target) {
        int left = 0, right = elements.length - 1;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            int cmp = elements[mid].compareTo(target);
            if (cmp < 0)
                right = mid - 1;
            else
                left = mid + 1;
        }
        if (left == elements.length || elements[left].compareTo(target)!= 0) return -1;
        return left;
    }

    /**
     * 哈希表查找
     * 时间复杂度O(1)
     * 空间复杂度O(n)
     * 预处理：建立哈希表
     */
    public static <E extends Comparable<E>> int HashTableSearch(E[] elements, E target) {
        if (elements == null || elements.length == 0) return -1;
        HashMap<E, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < elements.length; ++i) {
            hashMap.put(elements[i], i);
        }
        if (!hashMap.containsKey(target)) return -1;
        return hashMap.get(target);
    }

    /**
     * 二叉树查找
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * 预处理：建立二叉树
     */
    public static <E extends Comparable<E>> int BinaryTreeSearch(E[] elements, E target) {
        if (elements == null || elements.length == 0) {
            return -1;
        }
        // 创建 TreeMap 并将元素放入其中
        TreeMap<E, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < elements.length; i++) {
            treeMap.put(elements[i], i);
        }
        // 查找目标元素
        if (treeMap.containsKey(target)) {
            return treeMap.get(target);
        }
        return -1;
    }
}
