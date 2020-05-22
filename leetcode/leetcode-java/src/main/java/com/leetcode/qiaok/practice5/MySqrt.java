package com.leetcode.qiaok.practice5;

/**
 *   69. x 的平方根
 *      实现 int sqrt(int x) 函数。
 *      计算并返回 x 的平方根，其中 x 是非负整数。
 *      由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 *      示例 1:
 *
 *          输入: 4
 *          输出: 2
 *      示例 2:
 *
 *          输入: 8
 *          输出: 2
 *              说明: 8 的平方根是 2.82842...,
 *          由于返回类型是整数，小数部分将被舍去。
 *
 * @since 2020-06-09
 * @author qiaok
 */
public class MySqrt {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        int target = 8;
        MySqrt test = new MySqrt();
        long start = System.currentTimeMillis();
        int res = test.mysqrt2(target);
        System.out.println("耗时："+(System.currentTimeMillis()-start)+"秒");
        System.out.println("res="+res);

    }

    /**
     * 通过其他数学函数代替sqrt计算平方根，保留整数部分
     *
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     * @param target
     * @return
     */
    private int mysqrt(int target) {
        return 0;
    }

    /**
     * 通过数学方法得到近似结果，直接作为答案。
     *  二分查找
     * 时间复杂度：O(log x)
     * 空间复杂度：O(1)
     * @param target
     * @return
     */
    private int mysqrt1(int target) {
        return 0;
    }

    /**
     * 通过数学方法得到近似结果，直接作为答案。
     *  牛顿迭代
     * 时间复杂度：O(log x)
     * 空间复杂度：O(1)
     * @param x
     * @return
     */
    private int mysqrt2(int x) {
        return 0;
    }


}
