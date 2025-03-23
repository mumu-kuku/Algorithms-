package Nonlinear;

public class AVLTree <E>{
    AVLNode<E> root;

    public int height (AVLNode node) {
     return node == null ? -1 : node.height;
    }

    private void updateHeight (AVLNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private int balanceFactor (AVLNode node) {
        if (node == null)
            return 0;
        return height((node.left)) - height((node.right));
    }

    private AVLNode RightRotate (AVLNode node) {
        AVLNode child = node.left;
        AVLNode grandChild = child.right;
        child.right = node;
        node.left = grandChild;
        updateHeight(node);
        updateHeight(child);
        return child;
    }

    private AVLNode LeftRotate (AVLNode node) {
        AVLNode child = node.right;
        AVLNode grandChild = child.left;
        child.left = node;
        node.right = grandChild;
        updateHeight(node);
        updateHeight(child);
        return child;
    }

    private AVLNode LeftRightRotate (AVLNode node) {
        node.left = LeftRotate(node.left);
        return RightRotate(node);
    }

    private AVLNode RightLeftRotate (AVLNode node) {
        node.right = RightRotate(node.right);
        return LeftRotate(node);
    }

    private AVLNode rotate (AVLNode node) {
        int balance = balanceFactor(node);
        if (balance > 1) {
            if (balanceFactor(node.left) < 0) {
                return LeftRightRotate(node);
            }
            else {
                return LeftRotate(node);
            }
        }
        else if (balance < -1) {
            if (balanceFactor(node.right) > 0) {
                return RightLeftRotate(node);
            }
            else {
                return RightRotate(node);
            }
        }
        return node;
    }

    public void insert(E val) {
        root = insertHelper(root, val);
    }

    private AVLNode insertHelper(AVLNode node, E val) {
        if (node == null)
            return new AVLNode(val);
        if (val.hashCode() < node.val.hashCode())
            node.left = insertHelper(node.left, val);
        else if (val.hashCode() > node.val.hashCode())
            node.right = insertHelper(node.right, val);
        else
            return node;
        updateHeight(node);
        node = rotate(node);
        return node;
    }

    public void remove (E val) {
        root = removeHelper(root, val);
    }

    private AVLNode removeHelper(AVLNode node, E val) {
        if (node == null)
            return null;
        if (val.hashCode() < node.val.hashCode())
            node.left = removeHelper(node.left, val);
        else if (val.hashCode() > node.val.hashCode())
            node.right = removeHelper(node.right, val);
        else {
            if (node.left == null || node.right == null) {
                AVLNode child = node.left != null ? node.left : node.right;
                if (child == null)
                    return null;
                else
                    node = child;
            } else {
                AVLNode temp = node.right;
                while (temp.left != null){
                    temp = temp.left;
                }
                node.right = removeHelper(node.right, (E) temp.val);
                node.val = temp.val;
            }
        }
        return node;
    }
}