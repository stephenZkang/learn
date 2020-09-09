package com.leetcode.qiaok.practice3;


import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * https://leetcode-cn.com/problems/combination-sum/
 *
 * @since 2020-09-16
 * @author qiaok
 */
public class CombinationSum {

    public static void main(String[] args){
        CombinationSum test = new CombinationSum();
        int[] candidates = {
                2,3,6,7
        };

        int target = 7;
        long start = System.currentTimeMillis();
        List<List<Integer>> res = test.combinationSum(candidates,target);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res.toString());

    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        return ans;
    }


}
