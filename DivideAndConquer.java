package Algorithm;

import Nonlinear.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DivideAndConquer {
    private DivideAndConquer() {

    }
    private int dfs (int[] num, int target, int i, int j) {
        if (i > j)
            return -1;
        int m = (i + j) / 2;
        if (num[m] < target) {
            return dfs(num, target, m + 1, j);
        }
        else if (num[m] > target) {
            return dfs(num, target, i, m - 1);
        }
        else {
            return m;
        }
    }

    public int binarySearch (int[] num, int target) {
        int n = num.length;
        return dfs(num, target, 0, n - 1);
    }

    private TreeNode dfs (int[] preorder, Map<Integer, Integer> inorderMap, int i, int l, int r) {
        if (r - l < 0)
            return null;
        TreeNode root = new TreeNode(preorder[i]);
        int m = inorderMap.get(preorder[i]);
        root.left = dfs(preorder, inorderMap, i + 1, l, m - 1);
        root.right = dfs(preorder, inorderMap, i + 1 + m - l, m + 1, r);
        return root;
    }

    public TreeNode buildTree (int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            inorderMap.put(inorder[i], i);
        }
        TreeNode root = dfs(preorder, inorderMap, 0, 0, inorder.length - 1);
        return root;
    }

    private void move(List<Integer> src, List<Integer> tar) {
        Integer pan = src.remove(src.size() - 1);
        tar.add(pan);
    }

    private void dfs(int i, List<Integer> src, List<Integer> buf, List<Integer> tar) {
        if (i == 1) {
            move(src, tar);
            return;
        }
        dfs(i - 1, src, tar, buf);
        move(src, tar);
        dfs(i - 1, buf, src, tar);
    }

    void solveHanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        int n = A.size();
        dfs(n, A, B, C);
    }
}
