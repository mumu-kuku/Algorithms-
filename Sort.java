package Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 这是一个实现各种排序算法的工具类
 * 1. 选择排序 SelectionSort(E[] arr)
 * 2. 冒泡排序 BubbleSort(E[] arr)
 * 3. 插入排序 InsertionSort(E[] arr)
 * 4. 快速排序 QuickSort(E[] arr)
 * 5. 归并排序 MergeSort(E[] arr)
 * 6. 堆排序 HeapSort(E[] arr)
 * 7. 桶排序 BucketSort(E[] arr)
 * 8. 计数排序 CountingSort(E[] arr)
 * 9. 基数排序 RadixSort(E[] arr)
 */
public class Sort {
    private Sort() {}

    /**
     * 选择排序 SelectionSort(E[] arr)
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public static <E extends Comparable<E>> void SelectionSort(E[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int k = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[k]) < 0)
                    k = j;
            }
            if (k != i) {
                E tmp = arr[i];
                arr[i] = arr[k];
                arr[k] = tmp;
            }
        }
    }

    /**
     * 冒泡排序 BubbleSort(E[] arr)
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public static <E extends Comparable<E>> void BubbleSort(E[] arr) {
        int n = arr.length;
        for (int i = n - 1; i > 0; i++) {
            boolean flag = true;
            for (int j = 0; j < i; j++) {
                // 使用 compareTo 方法比较元素大小
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    E tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    /**
     * 插入排序 InsertionSort(E[] arr)
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public static <E extends Comparable<E>> void InsertionSort(E[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            E base = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(base) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = base;
        }
    }

    /**
     * 快速排序 QuickSort(E[] arr)
     * 时间复杂度：O(n log n)
     * 空间复杂度：O(n)
     */
     public static <E extends Comparable<E>> void QuickSort(E[] arr) {
        int l = 0, r = arr.length-1;
        QuickSort(arr, l, r);
     }

//     private static <E extends Comparable<E>> void QuickSort(E[] arr, int l, int r) {
//         if (l >= r)
//             return;
//         int pivot = partition(arr, l, r);
//         QuickSort(arr, l, pivot-1);
//         QuickSort(arr, pivot+1, r);
//     }

    private static <E extends Comparable<E>> void QuickSort(E[] arr, int l, int r) {
        while (l < r) {
            int pivot = partition(arr, l, r);
            if (pivot - l < r - pivot) {
                QuickSort(arr, l, pivot-1);
                l = pivot+1;
            } else {
                QuickSort(arr, pivot+1, r);
                r = pivot-1;
            }
        }
    }

     private static <E extends Comparable<E>> int medianThree(E[] arr, int left, int mid, int right) {
         E l = arr[left], m = arr[mid], r = arr[right];
         if (l.compareTo(m) > 0 && l.compareTo(r) < 0 || l.compareTo(m) < 0 && l.compareTo(r) > 0)
             return left;
         else if (m.compareTo(l) > 0 && m.compareTo(r) < 0 || m.compareTo(l) < 0 && m.compareTo(r) > 0)
             return mid;
         return right;
     }

//     private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
//         int i = l, j = r;
//         while (i < j) {
//             while (i < j && arr[j].compareTo(arr[l]) >= 0)
//                 j--;
//             while (i < j && arr[i].compareTo(arr[l]) <= 0)
//                 i++;
//             swap(arr, i, j);
//         }
//         swap(arr, i, l);
//         return i;
//     }

        private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
            int med = medianThree(arr, l, (l+r)/2, r);
            swap(arr, l, med);
            int i = l, j = r;
            while (i < j) {
                while (i < j && arr[j].compareTo(arr[l]) >= 0)
                    j--;
                while (i < j && arr[i].compareTo(arr[l]) <= 0)
                    i++;
                swap(arr, i, j);
            }
            swap(arr, i, l);
            return i;
        }

     private static <E> void swap(E[] arr, int i, int j) {
         E tmp = arr[i];
         arr[i] = arr[j];
         arr[j] = tmp;
     }

     /**
      * 归并排序 MergeSort(E[] arr)
      * 时间复杂度：O(n log n)
      * 空间复杂度：O(n)
      */
     public static <E extends Comparable<E>> void MergeSort(E[] arr) {
         int l = 0, r = arr.length-1;
         MergeSort(arr, l, r);
     }

     private static <E extends Comparable<E>> void Merge(E[] arr, int l, int m, int r) {
         E[] tmp = (E[]) new Comparable[r-l+1];
         int i = l, j = m + 1, k = 0;
         while (i <= m && j <= r) {
             if (arr[i].compareTo(arr[j]) <= 0)
                 tmp[k++] = arr[i++];
             else
                 tmp[k++] = arr[j++];
         }
         while (i <= m)
             tmp[k++] = arr[i++];
         while (j <= r)
             tmp[k++] = arr[j++];
         for (k = 0; k < tmp.length; k++)
             arr[l+k] = tmp[k];
     }

     private static <E extends Comparable<E>> void MergeSort(E[] arr, int l, int r) {
         if (l >= r)
             return;
         int mid = l + (r-l)/2;
         MergeSort(arr, l, mid);
         MergeSort(arr, mid+1, r);
         Merge(arr, l, mid, r);
     }

     /**
      * 堆排序 HeapSort(E[] arr)
      * 时间复杂度：O(n log n)
      * 空间复杂度：O(1)
      */
     public static <E extends Comparable<E>> void HeapSort(E[] arr) {
         for (int i = arr.length/2-1; i >= 0; i--)
             siftDown(arr, arr.length, i);
         for (int i = arr.length-1; i > 0; i--) {
             E tmp = arr[0];
             arr[0] = arr[i];
             arr[i] = tmp;
             siftDown(arr, i, 0);
         }
     }

     private static <E extends Comparable<E>> void siftDown(E[] arr, int n, int i) {
         while (true) {
             int l = 2 * i + 1, r = 2 * i + 2;
             int max = i;
             if (l < n && arr[l].compareTo(arr[max]) > 0)
                 max = l;
             if (r < n && arr[r].compareTo(arr[max]) > 0)
                 max = r;
             if (max == i)
                 break;
             E tmp = arr[i];
             arr[i] = arr[max];
             arr[max] = tmp;
             i = max;
         }
     }

     /**
      * 桶排序 BucketSort(E[] arr)
      * 时间复杂度：O(n)
      * 空间复杂度：O(n)
      */
     public static <E extends Comparable<E>> void BucketSort(E[] arr) {
         int k = arr.length / 2;
         List<List<E>> buckets = new ArrayList<>();
         for (int i = 0; i < k; i++)
             buckets.add(new ArrayList<>());
         for (E e : arr) {
             int index = (int) ((double) e.hashCode() / Integer.MAX_VALUE * k);
             buckets.get(index).add(e);
         }
         for (List<E> bucket : buckets)
             Collections.sort(bucket);
         int i = 0;
         for (List<E> bucket : buckets) {
             for (E e : bucket)
                 arr[i++] = e;
         }
     }

     /**
      * 计数排序 CountingSort(E[] arr)
      * 时间复杂度：O(n)
      * 空间复杂度：O(n)
      */
     public static void CountingSort(int[] arr) {
         int m = 0;
         for (int i : arr)
             m = Math.max(m, i);
         int[] count = new int[m+1];
         for (int i : arr)
             count[i]++;
         for (int i = 0; i < m; i++)
             count[i + 1] += count[i];
         int n = arr.length;
         int[] tmp = new int[n];
         for (int i = n-1; i >= 0; i--) {
             int index = count[arr[i]] - 1;
             tmp[index] = arr[i];
             count[arr[i]]--;
         }
     }

    /**
     * 基数排序 RadixSort(E[] arr)
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static void RadixSort(int[] arr) {
        int m = Integer.MIN_VALUE;
        for (int num : arr)
            if (num > m)
                m = num;
        for (int exp = 1; exp <= m; exp *= 10) {
            countingSortDigit(arr, exp);
        }
    }

    private static void countingSortDigit(int[] arr, int exp) {
        int[] counter = new int[10];
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int d = (arr[i] / exp);
            counter[d]++;
        }
        for (int i = 1; i < 10; i++)
            counter[i] += counter[i-1];
        int[] res = new int[n];
        for (int i = n-1; i >= 0; i--) {
            int d = digit(arr[i], exp);
            int j = counter[d] - 1;
            res[j] = arr[i];
            counter[d]--;
        }
        for (int i = 0; i < n; i++)
            arr[i] = res[i];
    }

    private static int digit(int num, int exp) {
        return (num / exp) % 10;
    }
}