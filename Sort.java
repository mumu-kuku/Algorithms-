package Algorithm;


/**
 * 这是一个实现各种排序算法的工具类
 * 1. 选择排序 SelectionSort(E[] arr)
 * 2. 冒泡排序 BubbleSort(E[] arr)
 * 3. 插入排序 InsertionSort(E[] arr)
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
}