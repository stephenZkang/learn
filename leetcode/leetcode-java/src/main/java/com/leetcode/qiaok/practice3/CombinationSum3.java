package com.leetcode.qiaok.practice3;

import java.util.List;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * @since 2020-09-18
 * @author qiaok
 */
public class CombinationSum3 {

    public static void main(String[] args){
        CombinationSum3 test = new CombinationSum3();
        int k = 3; int n = 7;
        long start = System.currentTimeMillis();
        List<List<Integer>> res = test.combinationSum3(k,n);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res.toString());
    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        return null;
    }
}
