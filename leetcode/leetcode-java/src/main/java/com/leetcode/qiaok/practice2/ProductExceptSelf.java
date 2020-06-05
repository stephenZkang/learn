package com.leetcode.qiaok.practice2;

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
 * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * 示例:
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 *
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？
 * （ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * @since 2020-06-05
 * @author qiaok
 */
public class ProductExceptSelf {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        ProductExceptSelf test = new ProductExceptSelf();
        int[] nums = { 1,2,3,4 };
        long start = System.currentTimeMillis();
        int[] res = test.productExceptSelf(nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+ Arrays.toString(res));
    }

    /**
     * 左右乘积法
     * 时间复杂度：
     * 空间复杂度：
     * @param nums
     * @return
     */
    private int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] L = new int[length];
        L[0] = 1;
        for (int i = 1; i < length; i++) {
            L[i] = nums[i-1] * L[i-1];
        }
        int[] R = new int[length];
        R[length-1] = 1;
        for (int i = length - 2; i >=0; i--) {
            R[i] = nums[i+1] * R[i+1];
        }
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            answer[i] = L[i]*R[i];
        }

        return answer;
    }


}
