package com.leetcode.qiaok.practice1;

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
 * @since 2020-06-04
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
        int[] res = test.productExceptSelf2(nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+ Arrays.toString(res));
    }

    /**
     * 暴力破解双重循环
     * 超出时间限制
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    private int[] productExceptSelf(int[] nums) {
        if(nums ==null||nums.length ==0){
            return null;
        }
        int len = nums.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = 1;
            for(int j =0;j < len;j++){
                if(i!=j){
                    res[i] *= nums[j];
                }
            }
        }
        return res;
    }


    /**
     * 左右乘积法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    private int[] productExceptSelf1(int[] nums) {
        int len = nums.length;
        int[] L = new int[len];
        int[] R = new int[len];
        int[] answer = new int[len];
        L[0] = 1;
        for (int i = 1; i < len; i++) {
            L[i] = nums[i-1]*L[i-1];
        }
        R[len-1] = 1;
        for (int i = len -2 ; i >=0 ; i--) {
            R[i] = nums[i+1]*R[i+1];
        }
        for (int i = 0; i < len; i++) {
           answer[i] = L[i]* R[i];
        }
        return answer;
    }



    /**
     * 空间复杂度 O(1) 的方法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    private int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] answer = new int[len];
        answer[0] = 1;
        for (int i = 1; i < len; i++) {
            answer[i] = nums[i-1]*answer[i-1];
        }
        int R = 1;
        for (int i = len -1 ; i >=0 ; i--) {
            answer[i] = answer[i] * R;
            R *= nums[i];
        }
        return answer;
    }


}
