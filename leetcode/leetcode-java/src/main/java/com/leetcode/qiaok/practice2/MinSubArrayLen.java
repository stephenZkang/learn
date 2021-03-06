package com.leetcode.qiaok.practice2;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，
 * 并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例:
 *
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 *
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * @since 2020-06-29
 * @author qiaok
 */
public class MinSubArrayLen {

    public static void main(String[] args){
        MinSubArrayLen test = new MinSubArrayLen();
        int s = 7;
        int[] nums = { 2,3,1,2,4,3 };
        long start = System.currentTimeMillis();
        int res = test.minSubArrayLen(s,nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res=" + res);
    }

    /***
     * 双指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if(n == 0){
            return 0;
        }

        int start =0,end=0;
        int sum =0;
        int ans = Integer.MAX_VALUE;
        while(end<n){
            sum += nums[end];
            while(sum>=s){
                ans = Math.min(ans, end - start + 1);
                sum-=nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ?0 :ans;
    }


}
