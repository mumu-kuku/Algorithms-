package Algorithm;

public class DynamicProgramming {
    private DynamicProgramming() {}

    // 动态规划爬楼梯问题
    public static int climbStairs(int n) {
        if (n == 1 || n == 2)
            return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        // 状态转移:从较小子问题逐步推导出较大子问题的解
        for (int i = 3; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 爬楼梯问题空间优化
    public static int climbStairs2(int n) {
        if (n == 1 || n == 2)
            return n;
        int a = 1;
        int b = 2;
        for (int i = 3; i <= n; i++) {
            int tmp = a;
            a = b;
            b = tmp + a;
        }
        return b;
    }

    // 最优子结构

    // 爬楼梯最小代价问题
    // 给定一个楼梯，每步可以走 1 级或 2 级，每级楼梯都有一个非负代价，求走完楼梯的最小代价
    // 给定一个非负整数数组 cost，其中 cost[i] 是从楼梯第 i 级台阶向上爬需要支付的费用，
    // cost[0] 是地面，cost[1] 是第 1 级台阶，cost[2] 是第 2 级台阶，以此类推
    // 返回最小代价
    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length - 1;
        if (n == 1 || n == 2)
            return cost[n];
        int[] dp = new int[n + 1];
        dp[1] = cost[1];
        dp[2] = cost[2];
        for (int i = 3; i <= n; ++i) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return dp[n];
    }

    // 爬楼梯最小代价问题空间优化
    public static int minCostClimbingStairs2(int[] cost) {
        int n = cost.length - 1;
        if (n == 1 || n == 2)
            return cost[n];
        int a = cost[1], b = cost[2];
        for (int i = 3; i <= n; ++i) {
            int tmp = a;
            a = b;
            b = Math.min(tmp, b) + cost[i];
        }
        return b;
    }

    // 无后效性

    // 带约束的爬楼梯问题
    // 给定一个楼梯，每步可以走 1 级或 2 级，但不能连续两轮 1 级，有多少种走法
    public static int climbStairsConstraint(int n) {
        if (n == 1 || n == 2)
            return 1;
        int[][] dp = new int[n + 1][3];
        dp[1][1] = 1;
        dp[1][2] = 0;
        dp[2][1] = 0;
        dp[2][2] = 1;
        for (int i = 3; i <= n; ++i) {
            dp[i][1] = dp[i - 1][2];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
        }
        return dp[n][1] + dp[n][2];
    }

    // 最小路径和问题
    // 给定一个非负整数二维网格 grid，网格中的每个单元格包含一个非负整数，代表该单元格的代价
    // 从左上角开始，每次只能向下或向右移动一步，求到达右下角的最小路径和

    // 方法一：暴力搜索
    public static int minPathSum1(int[][] grid, int i, int j) {
        if (i == 0 && j == 0)
            return grid[0][0];
        if (i < 0 || j < 0)
            return Integer.MAX_VALUE;
        int up = minPathSum1(grid, i - 1, j);
        int left = minPathSum1(grid, i, j - 1);
        return Math.min(up, left) + grid[i][j];
    }

    // 方法二：记忆化搜索
    public static int minPathSum2(int[][] grid, int[][] mem, int i, int j) {
        if (i == 0 && j == 0)
            return grid[0][0];
        if (i < 0 || j < 0)
            return Integer.MAX_VALUE;
        if (mem[i][j] != -1)
            return mem[i][j];
        int up = minPathSum2(grid, mem, i - 1, j);
        int left = minPathSum2(grid, mem, i, j - 1);
        mem[i][j] = Math.min(up, left) + grid[i][j];
        return mem[i][j];
    }

    // 方法三：动态规划
    public static int minPathSum3(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];
        for (int j = 1; j < m; ++j) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < n; ++i) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }

    // 方法四：动态规划空间优化
    public static int minPathSum4(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[] dp = new int[m];
        dp[0] = grid[0][0];
        for (int j = 1; j < m; ++j) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        for (int i = 1; i < n; ++i) {
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < m; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[n - 1];
    }

    // 0-1背包问题
    // 给定一个n个物品和一个容量为cap的背包，第i个物品的重量是wgt[i-1]，价值是val[i-1]
    // 求在不超过背包容量的前提下，能装入背包的最大价值,每个物品只能选择一次

    // 方法一：暴力搜索
    public static int knapsack(int[] wgt, int[] val, int i, int c) {
        if (i == 0 || c == 0)
            return 0;
        if (wgt[i - 1] > c)
            return knapsack(wgt, val, i - 1, c);
        int no = knapsack(wgt, val, i - 1, c);
        int yes = knapsack(wgt, val, i - 1, c - wgt[i - 1]) + val[i - 1];
        return Math.max(no, yes);
    }

    // 方法二：记忆化搜索
    public static int knapsack2(int[] wgt, int[] val, int[][] mem, int i, int c) {
        if (i == 0 || c == 0)
            return 0;
        if (mem[i][c]!= -1)
            return mem[i][c];
        if (wgt[i - 1] > c)
            mem[i][c] = knapsack2(wgt, val, mem, i - 1, c);
        int no = knapsack2(wgt, val, mem, i - 1, c);
        int yes = knapsack2(wgt, val, mem, i - 1, c - wgt[i - 1]) + val[i - 1];
        mem[i][c] = Math.max(no, yes);
        return mem[i][c];
    }

    // 方法三：动态规划
    public static int knapsack3(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        int[][] dp = new int[n + 1][cap + 1];
        for (int i = 1; i <= n; i++) {
            for (int c = 1; c <= cap; c++) {
                if (wgt[i - 1] > c)
                    dp[i][c] = dp[i - 1][c];
                else
                    dp[i][c] = Math.max(dp[i - 1][c], dp[i - 1][c - wgt[i - 1]] + val[i - 1]);
            }
        }
        return dp[n][cap];
    }

    // 空间优化
    public static int knapsack4(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        int[] dp = new int[cap + 1];
        for (int i = 1; i <= n; i++) {
            for (int c = cap; c >= 1; c--) {
                if (wgt[i - 1] <= c)
                    dp[c] = Math.max(dp[c], dp[c - wgt[i - 1]] + val[i - 1]);
            }
        }
        return dp[cap];
    }

    // 完全背包问题
    // 给定一个n个物品和一个容量为cap的背包，第i个物品的重量是wgt[i-1]，价值是val[i-1]
    // 求在不超过背包容量的前提下，能装入背包的最大价值,每个物品可以选择多次
    public static int unboundedKnapsack(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        int[][] dp = new int[n + 1][cap + 1];
        for (int i = 1; i <= n; i++) {
            for (int c = 1; c <= cap; c++) {
                if (wgt[i - 1] > c)
                    dp[i][c] = dp[i - 1][c];
                else
                    dp[i][c] = Math.max(dp[i - 1][c], dp[i][c - wgt[i - 1]] + val[i - 1]);
            }
        }
        return dp[n][cap];
    }
    
    // 完全背包问题空间优化
    public static int unboundedKnapsack2(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        int[] dp = new int[cap + 1];
        for (int i = 1; i <= n; i++) {
            for (int c = 1; c <= cap; c++) {
                if (wgt[i - 1] > c)
                    dp[c] = dp[c];
                else
                    dp[c] = Math.max(dp[c], dp[c - wgt[i - 1]] + val[i - 1]);
            }
        }
        return dp[cap];
    }
}
