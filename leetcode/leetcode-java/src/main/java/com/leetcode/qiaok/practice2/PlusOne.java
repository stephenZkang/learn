package com.leetcode.qiaok.practice2;

import java.util.Arrays;

/**
 *  66. 加一
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 * @since 2020-05-20
 * @author qiaok
 *
 */
public class PlusOne {
    /**
     * 测试
     * @param args
     */
    public static  void main(String[] args){
        int[] nums = {1,2,3};
        PlusOne test =new PlusOne();
        long start = System.currentTimeMillis();
        int[] res = test.plusOne(nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+ Arrays.toString(res));
    }

    /**
     * 数学方法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    private int[] plusOne(int[] nums) {
        //循环加一,直到不为10
        for (int i = nums.length-1; i >0; i--) {
            nums[i]++;
            nums[i] = nums[i] %10;
            if(nums[i]!=0)return nums;
        }
        nums = new int[nums.length+1];
        nums[0] = 1;
        return  nums;
    }
}
