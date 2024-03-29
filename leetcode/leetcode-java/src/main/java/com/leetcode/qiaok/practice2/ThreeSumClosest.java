package com.leetcode.qiaok.practice2;

/**
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
 * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * 提示：
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 * @since 2020-06-25
 * @author qiaok
 */
public class ThreeSumClosest {

    public static void main(String[] args){
        ThreeSumClosest test = new ThreeSumClosest();
        int[] nums = {};
        int target = 0;
        long start = System.currentTimeMillis();
        int res = test.threeSumCloset(nums,target);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 时间复杂度：
     * 空间复杂度：
     * @param nums
     * @param target
     * @return
     */
    public int threeSumCloset(int[] nums, int target) {
        return 0;
    }
}
