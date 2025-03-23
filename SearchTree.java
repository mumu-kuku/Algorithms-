package Nonlinear;

// 手写实现二叉搜索树
public class SearchTree<E> {
    TreeNode<E> root;

    public SearchTree() {
        root = null;
    }

    public SearchTree(TreeNode<E> root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void insert(E e) {
        if (root == null) {
            root = new TreeNode<E>(e);
            return;
        }
        TreeNode cur = root, pre = null;
        while (cur != null) {
            if (e.equals(cur.val))
                return;
            pre = cur;
            if (cur.val.hashCode() < e.hashCode())
                cur = cur.right;
            else
                cur = cur.left;
        }
        TreeNode node = new TreeNode(e);
        if (pre.val.hashCode() < e.hashCode())
            pre.right = node;
        else
            pre.left = node;
    }

    public boolean remove(E e) {
        if (root == null)
            return false;
        TreeNode cur = root, pre = null;
        while (cur != null) {
            if (cur.val.equals(e))
                break;
            pre = cur;
            if (cur.val.hashCode() < e.hashCode())
                cur = cur.right;
            else
                cur = cur.left;
        }
        if (cur == null)
            return false;
        if (cur.left == null || cur.right == null) {
            TreeNode child = cur.left != null ? cur.left : cur.right;
            if (cur != root) {
                if (pre.left == cur) {
                    pre.left = child;
                    return true;
                } else {
                    pre.right = child;
                    return true;
                }
            } else {
                root = child;
                return true;
            }
        }
        else {
            TreeNode tmp = cur.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            remove((E) tmp.val);
            cur.val = tmp.val;
            return true;
        }
    }

    public TreeNode search(E e) {
        TreeNode cur = root;
        while (cur != null) {
            if (e.equals(cur.val)) {
                return cur;
            } else if (e.hashCode() < cur.val.hashCode()) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }
}
