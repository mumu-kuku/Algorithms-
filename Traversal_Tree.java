package Nonlinear;

import java.util.*;

public class Traversal_Tree {
    // 广度优先搜索（借助队列实现）
    // 层序遍历
    public List<TreeNode> levelOrder(TreeNode root) {
        // 初始化队列，添加根节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 初始化一个列表，用于接收遍历到的数据并返回
        List<TreeNode> list = new ArrayList<>();
        // 遍历队列
        while (!queue.isEmpty()) {
            // 出队并将节点存入列表
            TreeNode node = queue.poll();
            list.add(node);
            // 判断是否存在左节点
            if (node.left != null) {
                queue.offer(node.left);
            }
            // 再判断是否存在右节点，实现从左向右,先上后下遍历的逻辑
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return list;
        /*时间复杂度O(n)————空间复杂度O(n)*/
    }

    // 深度优先遍历（借助递归实现 ）
    // 前序遍历
     public List<TreeNode> inOrder(TreeNode root, List<TreeNode> list) {
            // 遍历到最底层就返回
            if (root == null) return null;
            // 遍历顺序根节点->左子树->右子树
            list.add(root);
            preOrder(root.left, list);
            preOrder(root.right, list);

            return list;
    }

    // 中序遍历
    public List<TreeNode> preOrder(TreeNode root, List<TreeNode> list) {
        // 遍历到最底层就返回
        if (root == null) return null;
        // 遍历顺序左子树->根节点->右子树
        preOrder(root.left, list);
        list.add(root);
        preOrder(root.right, list);

        return list;
    }

    // 后序遍历
    public List<TreeNode> postOrder(TreeNode root, List<TreeNode> list) {
        // 遍历到最底层就返回
        if (root == null) return null;
        // 遍历顺序左子树->右子树->根节点
        preOrder(root.left, list);
        preOrder(root.right, list);
        list.add(root);

        return list;
    }
    /*时间复杂度O(n)————空间复杂度O(n)*/

    // 借助栈实现深度优先遍历
    // 前序遍历
    public List<TreeNode> preOrder(TreeNode root) {
        // 初始化一个栈，用于存储节点
        Stack<TreeNode> stack = new Stack<>();
        // 初始化一个列表，用于接收遍历到的数据并返回
        List<TreeNode> list = new ArrayList<>();
        // 遍历栈
        while (root!= null || !stack.isEmpty()) {
            // 遍历到最底层就返回
            while (root!= null) {
                // 遍历顺序根节点->左子树->右子树
                list.add(root);
                stack.push(root);
                root = root.left;
            }
            // 弹出栈顶元素, 并将其右子树作为根节点
            root = stack.pop();
            root = root.right;
        }
        return list;
    }

    // 中序遍历
    public List<TreeNode> inOrder(TreeNode root) {
        // 初始化一个栈，用于存储节点
        Stack<TreeNode> stack = new Stack<>();
        // 初始化一个列表，用于接收遍历到的数据并返回
        List<TreeNode> list = new ArrayList<>();
        // 遍历栈
        while (root != null || !stack.isEmpty()) {
            // 遍历到最底层的左子节点
            while (root != null) {
                // 遍历顺序左子树->根节点->右子树
                stack.push(root);
                root = root.left;
            }
            // 弹出栈顶元素,添加到列表中，并将其右子树作为根节点
            root = stack.pop();
            list.add(root);
            root = root.right;
        }
        return list;
    }

    // 后序遍历
    public List<TreeNode> postOrder(TreeNode root) {
        // 初始化两个栈
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        // 初始化一个列表，用于接收遍历到的数据并返回
        List<TreeNode> list = new ArrayList<>();

        // 将根节点压入第一个栈
        if (root != null) {
            stack1.push(root);
        }

        // 遍历第一个栈
        while (!stack1.isEmpty()) {
            // 弹出栈顶元素, 并将其压入第二个栈
            root = stack1.pop();
            stack2.push(root);

            // 先将左子节点压入栈1
            if (root.left != null) {
                stack1.push(root.left);
            }
            // 再将右子节点压入栈1
            if (root.right != null) {
                stack1.push(root.right);
            }
        }

        // 将第二个栈中的元素依次弹出并加入列表
        while (!stack2.isEmpty()) {
            list.add(stack2.pop());
        }

        return list;
    }
    /*时间复杂度O(n)————空间复杂度O(n)*/
}
