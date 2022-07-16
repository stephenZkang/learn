package com.leetcode.qiaok.practice5;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 * 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 *
 * 难度：简单
 * @since 2020-07-22
 * @author qiaok
 */
public class MinArray {

    public static void main(String[] args){
        MinArray test = new MinArray();
        int[] numbers = {

        };
        long start = System.currentTimeMillis();
        int res = test.minArray(numbers);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);

    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        return 0;
    }
}
