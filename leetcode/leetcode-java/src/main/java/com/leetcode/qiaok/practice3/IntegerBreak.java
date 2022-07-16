package com.leetcode.qiaok.practice3;

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
 * @since 2020-08-06
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
     * 时间复杂度：
     * 空间复杂度：
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        return 0;
    }
}
