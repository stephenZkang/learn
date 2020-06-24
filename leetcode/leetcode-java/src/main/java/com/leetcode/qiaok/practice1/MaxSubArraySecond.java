package com.leetcode.qiaok.practice1;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 *
 *
 * 示例1:
 *
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 * https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 * @since 2020-06-19
 * @author qiaok
 */
public class MaxSubArraySecond {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        MaxSubArraySecond test  = new MaxSubArraySecond();
        int[] nums = { -2,1,-3,4,-1,2,1,-5,4 };
        long start = System.currentTimeMillis();
        int res = test.maxSubArray(nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res=" + res);
    }

    /**
     * 动态规划
     * 1. 找出重复子问题
     * 2. 写出子问题的递推关系
     * 3. 确定DP数组的计算顺序
     * 4. 空间优化
     * 时间复杂度：
     * 空间复杂度:
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int ans = nums[0];
        int sum = 0;
        for (int num: nums) {
            if(sum>0){
                sum+=num;
            }else{
                sum=num;
            }
            ans = Math.max(sum,ans);
        }
        return ans;
    }
}
