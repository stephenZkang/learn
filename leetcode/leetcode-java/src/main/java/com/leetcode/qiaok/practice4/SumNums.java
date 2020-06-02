package com.leetcode.qiaok.practice4;

/**
 *      面试题64. 求1+2+…+n
 *      求 1+2+...+n ，
 *      要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *      示例 1：
 *      输入: n = 3
 *      输出: 6
 *
 *      示例 2：
 *      输入: n = 9
 *      输出: 45
 *
 *      限制：
 *          1 <= n <= 10000
 *
 * @since 2020-07-02
 * @author qiaok
 */
public class SumNums {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        SumNums test = new SumNums();
        int n = 3;
        long start = System.currentTimeMillis();
        int res = test.sumNums(n);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 循环
     * 时间复杂度：
     * 空间复杂度：
     * @param n
     * @return
     */
    private int sumNums(int n) {
        return 0;
    }

}
