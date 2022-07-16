package com.leetcode.qiaok.practice4;


import java.util.List;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * @since 2020-10-10
 * @author qiaok
 */
public class CombinationSum2 {

    public static void main(String[] args){
        CombinationSum2 test = new CombinationSum2();
        int[] cadidates = {
                10,1,2,7,6,1,5
        };
        int target = 8 ;
        long start = System.currentTimeMillis();
        List<List<Integer>> res = test.combinationSum2(cadidates,target);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res.toString());
    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param cadidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] cadidates, int target) {
        return null;
    }

}
