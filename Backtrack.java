package Algorithm;

import Nonlinear.TreeNode;
import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class Backtrack {
    private Backtrack() {}

    // 回溯算法框架
//    private static void backtrack(State state, List<Choice> choices, List<State> res) {
//        if (isSolution(state)) {
//            res.add(state);
//            return;
//        }
//        for (Choice choice : choices) {
//            if (isValid(state, choice)) {
//                makeChoice(state, choice);
//                backtrack(state, choices, res);
//                undoChoice(state, choice);
//            }
//        }
//    }

    // 例题：在二叉树中搜索所有值为 7 的节点，返回这些节点的数量，并要求路径中不能包含值为 3 的节点
    private static boolean isSolution(List<TreeNode> state) {
        return !state.isEmpty() && (int)state.get(state.size() - 1).val == 7;
    }

    private static void recordSolution(List<TreeNode> state, List<List<TreeNode>> res) {
        res.add(new ArrayList<>(state));
    }

    private static boolean isValid(List<TreeNode> state, TreeNode choice) {
        return choice != null && (int)choice.val != 3;
    }

    private static void makeChoice(List<TreeNode> state, TreeNode choice) {
        state.add(choice);
    }

    private static void undoChoice(List<TreeNode> state, TreeNode choice) {
        state.remove(state.size() - 1);
    }

    public static void backtrack(List<TreeNode> state, List<TreeNode> choices, List<List<TreeNode>> res) {
        if (isSolution(state)) {
            recordSolution(state, res);
        }
        for (TreeNode choice : choices) {
            if (isValid(state, choice)) {
                makeChoice(state, choice);
                backtrack(state, choices, res);
                undoChoice(state, choice);
            }
        }

    }
}
