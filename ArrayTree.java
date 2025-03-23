package Nonlinear;

import Linear.MyArrayList;

import java.util.List;

// 手写实现基于数组实现的二叉树
public class ArrayTree<E> {
    // 映射公式：node[i], node.left[2i+1], node.right[2i+2]
     private MyArrayList<E> tree;

     public ArrayTree() {
         tree = new MyArrayList<>();
     }

     public ArrayTree(MyArrayList<E> tree) {
         this.tree = tree;
     }

     public ArrayTree(E root) {
         tree = new MyArrayList<>();
         tree.add(root);
     }

     public ArrayTree(E root, E left, E right) {
         tree = new MyArrayList<>();
         tree.add(root);
         tree.add(left);
         tree.add(right);
     }

     public int size() {
         return tree.size();
     }

     public Integer left(int index) {
         int leftIndex = 2 * index + 1;
         if (leftIndex >= tree.size()) {
             return null;
         }
         return leftIndex;
     }

     public Integer right(int index) {
         int rightIndex = 2 * index + 2;
         if (rightIndex >= tree.size()) {
             return null;
         }
         return rightIndex;
     }

     public Integer parent(int index) {
         if (index == 0) {
             return null;
         }
         return (index - 1) / 2;
     }

     public E get(int index) {
         if (index >= tree.size() || index < 0) {
             return null;
         }
         return tree.get(index);
     }

     public boolean set(int index, E element) {
         if (parent(index) != null || index == 0) {
             if (element == null) {
                 E flag = remove(index);
                 if (flag != null) {
                     return true;
                 }
                 return false;
             }
             if (index >= tree.size()) {
                 add(index, element);
                 return true;
             }
             tree.set(index, element);
             return true;
         }
          return false;
     }

     public boolean add(int index, E element) {
         // 父节点存在则添加
         if (parent(index) != null || index == 0) {
             // 添加前先判断是否超出数组长度，超出则扩容，并在中间添加null
             while (index >= tree.size()) {
                 tree.add(null);
             }
             tree.add(index, element);
             return true;
         }
         // 父节点不存在则添加失败
         return false;
     }

     public E remove(int index) {
         // 删除该节点，由该节点的左右节点替换
         if (get(index) != null) {
             E val = tree.get(index);
             // 若该节点有左节点，则将左节点替换到该节点位置，右节点替换到左节点位置
             if (left(index) != null) {
                 tree.set(index, tree.get(left(index)));
                 tree.set(left(index), tree.get(right(index)));
                 tree.set(right(index), null);
             }
             // 该节点没有左节点但有右节点，则将右节点替换到该节点位置
             else if (right(index) != null) {
                 tree.set(index, tree.get(right(index)));
                 tree.set(right(index), null);
             }
             // 该节点没有左右节点，则直接删除该节点
             else {
                 tree.set(index, null);
             }
             return val;
         }
         return null;
     }

     // 层序遍历
    // 由于数组实现二叉树，所以本身排列顺序就是层序遍历的顺序
     public MyArrayList<E> levelOrder() {
         MyArrayList<E> list = new MyArrayList<>();
         for (int i = 0; i < tree.size(); i++) {
             if (tree.get(i) == null) {
                 continue;
             }
             list.add(tree.get(i));
         }
         return list;
     }

     // 深度优先遍历
     public MyArrayList<E> dfs(Integer i, String order, MyArrayList<E> res) {
         if (get(i) == null) {
             return res;
         }
         if (order.equals("pre") || order.equals("preOrder"))
             // 添加当前节点到结果集
             res.add(get(i));
         dfs(left(i), order, res);

         if (order.equals("in") || order.equals("inOrder"))
             // 添加当前节点到结果集
             res.add(get(i));
         dfs(right(i), order, res);

         if (order.equals("post") || order.equals("postOrder"))
             // 添加当前节点到结果集
             res.add(get(i));
         return res;
     }

     // 前序遍历
     public MyArrayList<E> preOrder() {
         MyArrayList<E> res = new MyArrayList<>();
         return dfs(0, "pre", res);
     }

     // 中序遍历
     public MyArrayList<E> inOrder() {
         MyArrayList<E> res = new MyArrayList<>();
         return dfs(0, "in", res);
     }

     // 后序遍历
     public MyArrayList<E> postOrder() {
         MyArrayList<E> res = new MyArrayList<>();
         return dfs(0, "post", res);
     }
}
