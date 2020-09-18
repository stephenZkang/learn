package com.leetcode.qiaok.practice4;

import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 * https://leetcode-cn.com/problems/permutations-ii/
 * @since 2020-10-18
 * @author qiaok
 */
public class PremuteUnique {

    public static void main(String[] args){
        PremuteUnique test = new PremuteUnique();
        int[] nums = {

        };
        long start = System.currentTimeMillis();
        List<List<Integer>> res = test.premuteUnique(nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res.toString());
    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param nums
     * @return
     */
    public List<List<Integer>> premuteUnique(int[] nums) {
        return null;
    }
}
