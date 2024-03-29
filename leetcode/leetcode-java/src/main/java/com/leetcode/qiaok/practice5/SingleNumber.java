package com.leetcode.qiaok.practice5;

/**
 *      136. 只出现一次的数字
 *          给定一个非空整数数组，除了某个元素只出现一次以外，
 *          其余每个元素均出现两次。找出那个只出现了一次的元素。
 *      说明：
 *          你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *          示例 1:
 *           输入: [2,2,1]
 *           输出: 1
 *
 *          示例 2:
 *           输入: [4,1,2,1,2]
 *           输出: 4
 * @since 2020-06-14
 * @author qiaok
 */
public class SingleNumber {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {2, 2, 1,1,3};
        SingleNumber test = new SingleNumber();
        long start = System.currentTimeMillis();
        int res = test.singleNumber1(nums);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "毫秒");
        System.out.println("res=" + res);
    }

    /**
     * 位运算
     * 时间复杂度：O（n）
     * 空间复杂度：O（1）
     * @param nums
     * @return
     */
    private int singleNumber(int[] nums) {
        return 0;
    }


    /**
     * Hashmap实现
     * 时间复杂度：O（n）
     * 空间复杂度：O（1）
     * @param nums
     * @return
     */
    private int singleNumber1(int[] nums) {
        return 0;
    }



}