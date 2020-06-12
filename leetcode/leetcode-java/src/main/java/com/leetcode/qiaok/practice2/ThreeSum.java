package com.leetcode.qiaok.practice2;

import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * @since 2020-06-13
 * @author qiaok
 */
public class ThreeSum {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        ThreeSum test = new ThreeSum();
        int[] nums = { -1, 0, 1, 2, -1, -4 };
        long start = System.currentTimeMillis();
        List<List<Integer>> res = test.threeSum(nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res=" + res.toString());
    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param nums
     * @return
     */
    private List<List<Integer>> threeSum(int[] nums) {
        return null;
    }
}
