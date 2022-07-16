package com.leetcode.qiaok.practice2;

/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 * @since 2020-07-31
 * @author qiaok
 */
public class IntegerBreak {

    public static void main(String[] args){
        IntegerBreak test = new IntegerBreak();
        int n = 2 ;
        long start = System.currentTimeMillis();
        int res = test.integerBreak(n);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);

    }

    /**
     * 动态规划
     * 时间复杂度：
     * 空间复杂度：
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if(n < 4){
            return n-1;
        }
        int[] dp = new int[n+1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(Math.max(2 * (i - 2), 2 * dp[i - 2]), Math.max(3 * (i - 3), 3 * dp[i - 3]));
        }

        return dp[n];
    }
}
