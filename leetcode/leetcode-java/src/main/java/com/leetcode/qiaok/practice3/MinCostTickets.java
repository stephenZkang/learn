package com.leetcode.qiaok.practice3;

import java.util.HashSet;
import java.util.Set;

/**
 * 983. 最低票价
 *  在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。
 *  每一项是一个从 1 到 365 的整数。
 *
 *  火车票有三种不同的销售方式：
 *
 *     一张为期一天的通行证售价为 costs[0] 美元；
 *     一张为期七天的通行证售价为 costs[1] 美元；
 *     一张为期三十天的通行证售价为 costs[2] 美元。
 *     通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，
 *     那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
 *
 *     返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
 *
 * 示例 1：
 *
 * 输入：days = [1,4,6,7,8,20], costs = [2,7,15]
 * 输出：11
 * 解释：
 * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
 * 在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
 * 在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
 * 在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
 * 你总共花了 $11，并完成了你计划的每一天旅行。
 * 示例 2：
 *
 * 输入：days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * 输出：17
 * 解释：
 * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
 * 在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30 天生效。
 * 在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。
 * 你总共花了 $17，并完成了你计划的每一天旅行。
 *
 *
 * 提示：
 *
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days 按顺序严格递增
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 *
 *
 * @since 2020-05-13
 * @author qiaok
 */
public class MinCostTickets {

    int[] days,costs;
    Integer[] memo;
    Set<Integer> dayset;
    int[] durations = new int[]{1, 7, 30};


    /**
     * 记忆化搜索（日期变量型）
     *
     * 时间复杂度：O(W)，其中 W = 365W=365 是旅行计划中日期的最大值，我们需要计算 WW 个解，而每个解最多需要查询 33 个其他的解，
     * 因此计算量为 O(3 * W)=O(W)O(3∗W)=O(W)。
     * 空间复杂度：O(W)，我们需要长度为 O(W)O(W) 的数组来存储所有的解。
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {
        return 0;
    }

    public int dp(int i) {
        return 0;
    }

    /**
     * 记忆化搜索（窗口变量型）
     *  
     * 时间复杂度：O(N)，其中 NN 是出行日期的数量，我们需要计算 NN 个解，
     * 而计算每个解的过程中最多将指针挪动 3030 步，计算量为 O(30 * N)=O(N)O(30∗N)=O(N)。
     * 空间复杂度：O(N)，我们需要长度为 O(N)O(N) 的数组来存储所有的解
     *
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets1(int[] days, int[] costs) {
        return 0;
    }

    public int dp1(int i) {
        return 0;
    }



    public static void main(String[] args) {
        MinCostTickets test = new MinCostTickets();
        int[] days = { 1,4,6,7,8,20 };
        int[] costs = { 2,7,15 };
        long start = System.currentTimeMillis();
        int len = test.mincostTickets1(days,costs);
        System.out.println("耗时="+(System.currentTimeMillis()-start)+"毫秒");
        System.out.println(len);
    }
}
