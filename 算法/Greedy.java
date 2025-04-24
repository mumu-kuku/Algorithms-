package Algorithm;

import Nonlinear.Pair;

import java.util.Arrays;
import java.util.Comparator;

public class Greedy {
//    给定 𝑛 种硬币，第 𝑖 种硬币的面值为 𝑐𝑜𝑖𝑛𝑠[𝑖 − 1] ，目标金额为 𝑎𝑚𝑡 ，每种硬币可以重复选取，问
//    能够凑出目标金额的最少硬币数量。如果无法凑出目标金额，则返回 −1 。
    public static int coinChangeGreedy(int[] coins, int amt) {
    // 假设 coins 列表有序
        int i = coins.length - 1;
        int count = 0;
        while (amt > 0){
            while (i >= 0 && coins[i] > amt)
                i--;
            amt -= coins[i];
            count++;
        }
        return amt == 0 ? count : -1;
    }

//    给定 𝑛 个物品，第 𝑖 个物品的重量为 𝑤𝑔𝑡[𝑖 − 1]、价值为 𝑣𝑎𝑙[𝑖 − 1] ，和一个容量为 𝑐𝑎𝑝 的背包。每
//    个物品只能选择一次，但可以选择物品的一部分，价值根据选择的重量比例计算，问在限定背包容量
//    下背包中物品的最大价值。
    private class Item{
        int weight;
        int value;

        public Item(int w, int v){
            this.weight = w;
            this.value = v;
        }
    }

    public double fractionalKnapsack(int[] wgt, int[] val, int cap) {
        // 创建物品列表
        Item[] items = new Item[wgt.length];
        for (int i = 0; i < wgt.length; i++) {
            items[i] = new Item(wgt[i], val[i]);
        }
        // 按单位价值排序
        Arrays.sort(items, Comparator.comparingDouble(item -> -((double)item.value / item.weight )));
        // 遍历物品列表，计算最大价值
        double res = 0;
        for (Item item : items){
            if (cap >= item.weight){
                res += item.value;
                cap -= item.weight;
            }
            else{
                res += item.value * ((double)cap / item.weight);
                break;
            }
        }
        return res;
    }

//    输入一个数组 ℎ𝑡 ，其中的每个元素代表一个垂直隔板的高度。数组中的任意两个隔板，以及它们之
//    间的空间可以组成一个容器。
//    容器的容量等于高度和宽度的乘积（面积），其中高度由较短的隔板决定，宽度是两个隔板的数组索引
//    之差。
//    请在数组中选择两个隔板，使得组成的容器的容量最大，返回最大容量。
/* 最大容量：贪心 */
    int maxCapacity(int[] ht) {
        // 初始化 i, j，使其分列数组两端
        int i = 0, j = ht.length - 1;
        // 初始最大容量为 0
        int res = 0;
        //    循环贪心选择，直至两板相遇
        while (i < j) {
            // 更新最大容量
            int cap = Math.min(ht[i], ht[j]) * (j - i);
            res = Math.max(res, cap);
            // 向内移动短板
            if (ht[i] < ht[j]) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
//    给定一个正整数 𝑛 ，将其切分为至少两个正整数的和，求切分后所有整数的乘积最大是多少
    int maxProductCutting(int n) {
        if (n <= 3) {
            return n - 1;
        }
        // 贪心地切分出 3 ，a 为 3 的个数，b 为余数
        int a = n / 3;
        int b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        } else if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        } else {
            return (int) Math.pow(3, a) * 2;
        }
    }
}
