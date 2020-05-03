package com.leetcode.qiaok.practice1;

/**
 *  53. 最大子序和
 *      给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 *      示例:
 *
 *      输入: [-2,1,-3,4,-1,2,1,-5,4],
 *      输出: 6
 *      解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *      进阶:
 *
 *          如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * @since 2020-05-03
 * @author qiaok
 */
public class MaxSubArray {

    /**
     * 动态规划
     *  我们要记住动态规划的解题四步骤：
     *      定义子问题
     *      写出子问题的递推关系
     *      确定 DP 数组的计算顺序
     *      空间优化（可选）
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    private int maxSubArray(int[] nums){
        if (nums == null||nums.length == 0) {
            return 0;
        }
        int ans = nums[0];
        int sum = 0;
        for (int num :nums){
            if(sum>0){
                sum+=num;
            }else{
                sum = num;
            }
            ans = Math.max(ans,sum);
        }
        return ans;
    }

    /**
     * 分治法
     * 连续子序列的最大和主要由这三部分子区间里元素的最大和得到：
     *
     * 第 1 部分：子区间 [left, mid]；
     * 第 2 部分：子区间 [mid + 1, right]；
     * 第 3 部分：包含子区间 [mid , mid + 1] 的子区间，即 nums[mid] 与 nums[mid + 1] 一定会被选取。
     *
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums) {
        return maxSubArrayDivideWithBorder(nums, 0, nums.length-1);
    }

    private int maxSubArrayDivideWithBorder(int[] nums, int start, int end) {
        if (start == end) {
            // 只有一个元素，也就是递归的结束情况
            return nums[start];
        }

        // 计算中间值
        int center = (start + end) / 2;
        int leftMax = maxSubArrayDivideWithBorder(nums, start, center); // 计算左侧子序列最大值
        int rightMax = maxSubArrayDivideWithBorder(nums, center + 1, end); // 计算右侧子序列最大值

        // 下面计算横跨两个子序列的最大值

        // 计算包含左侧子序列最后一个元素的子序列最大值
        int leftCrossMax = Integer.MIN_VALUE; // 初始化一个值
        int leftCrossSum = 0;
        for (int i = center ; i >= start ; i --) {
            leftCrossSum += nums[i];
            leftCrossMax = Math.max(leftCrossSum, leftCrossMax);
        }

        // 计算包含右侧子序列最后一个元素的子序列最大值
        int rightCrossMax = nums[center+1];
        int rightCrossSum = 0;
        for (int i = center + 1; i <= end ; i ++) {
            rightCrossSum += nums[i];
            rightCrossMax = Math.max(rightCrossSum, rightCrossMax);
        }

        // 计算跨中心的子序列的最大值
        int crossMax = leftCrossMax + rightCrossMax;

        // 比较三者，返回最大值
        return Math.max(crossMax, Math.max(leftMax, rightMax));
    }

    public static void main(String[] args) {
        MaxSubArray test = new MaxSubArray();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        long start = System.currentTimeMillis();
        int len = test.maxSubArray(nums);
        System.out.println("耗时="+(System.currentTimeMillis()-start)+"毫秒");
        System.out.println(len);
    }
}
