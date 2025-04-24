package Algorithm;

import Nonlinear.TreeNode;

import java.util.*;

// 回溯算法
public class Backtrack {
    private Backtrack() {}

    // 回溯算法框架（state 记录当前到根的路径， choices 剩余未遍历的内容， res 结果集）
//    private static void backtrack(State state, List<Choice> choices, List<State> res) {
//        // 判断是否满足解，如果满足添加如结果集，并回溯
//        if (isSolution(state)) {
//            res.add(state);
//            return;
//        }
//        // 遍历内容列表
//        for (Choice choice : choices) {
//            // 如果内容合法，向state添加choice，并进入choice的路线，否则剪枝
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

    // 例题：全排列问题，列表中元素不可以重复使用
    private static void backtrack(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
        if (state.size() == choices.length) {
            res.add(new ArrayList<>(state));
            return;
        }
        Set<Integer> duplicated = new HashSet<>();
        for (int i = 0; i < choices.length; ++i) {
            int choice = choices[i];
            if (!selected[i] && !duplicated.contains(choice)) {
                duplicated.add(choice);
                state.add(choice);
                selected[i] = true;
                backtrack(state, choices, selected, res);
                selected[i] = false;
                state.remove(state.size() - 1);
            }
        }
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(new ArrayList<>(), nums, new boolean[nums.length], res);
        return res;
    }

    // 例题：子集和为target的所有子集,子集和1，列表中元素可以重复使用
    private static void backtrack(List<Integer> state, int target, int[] choices, int start, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        for (int i = start; i < choices.length; ++i) {
            if (target - choices[i] < 0)  break;
            state.add(choices[i]);
            backtrack(state, target - choices[i], choices, i, res);
            state.remove(state.size() - 1);
        }
    }

    public static List<List<Integer>> subsetSum(int[] nums, int target) {
       List<Integer> state = new ArrayList<>();
       Arrays.sort(nums);
       int start = 0;
       List<List<Integer>> res = new ArrayList<>();
       backtrack(state, target, nums, start, res);
       return res;
    }
    // 例题：子集和为target的所有子集,子集和2，列表中元素不可以重复使用
    private static void backtrack2(List<Integer> state, int target, int[] choices, int start, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        for (int i = start; i < choices.length; ++i) {
            if (target - choices[i] < 0)  break;
            if (i > start && choices[i] == choices[i - 1]) continue;
            state.add(choices[i]);
            backtrack2(state, target - choices[i], choices, i + 1, res);
            state.remove(state.size() - 1);
        }
    }

    public static List<List<Integer>> subsetSum2(int[] nums, int target) {
       List<Integer> state = new ArrayList<>();
       Arrays.sort(nums);
       int start = 0;
       List<List<Integer>> res = new ArrayList<>();
       backtrack2(state, target, nums, start, res);
       return res;
    }

    // n 皇后问题
    private static void backtrack3(int row, int n, List<List<String>> state, List<List<List<String>>> res, boolean[] cols, boolean[] dia1, boolean[] dia2) {
        if (row == n) {
            List<List<String>> copyState = new ArrayList<>();
            for (List<String> sRow : state) {
                copyState.add(new ArrayList<>(sRow));
            }
            res.add(copyState);
            return;
        }
        for (int col = 0; col < n; ++col) {
            int diag1 = row - col + n - 1;
            int diag2 = row + col;
            if (!cols[col] && !dia1[diag1] && !dia2[diag2]) {
                state.get(row).set(col, "Q");
                cols[col] = dia1[diag1] = dia2[diag2] = true;
                backtrack3(row + 1, n, state, res, cols, dia1, dia2);
                state.get(row).set(col, "#");
                cols[col] = dia1[diag1] = dia2[diag2] = false;
            }
        }
    }

    public static List<List<List<String>>> solveNQueens(int n) {
        List<List<String>> state = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                row.add("#");
            }
            state.add(row);
        }
        List<List<List<String>>> res = new ArrayList<>();
        boolean[] cols = new boolean[n];
        boolean[] dia1 = new boolean[2 * n - 1];
        boolean[] dia2 = new boolean[2 * n - 1];

        backtrack3(0, n, state, res, cols, dia1, dia2);
        return res;
    }
}
