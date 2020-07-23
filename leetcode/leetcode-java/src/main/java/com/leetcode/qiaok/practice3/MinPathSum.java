package com.leetcode.qiaok.practice3;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * 难度：中等
 * @since 2020-07-30
 * @author qiaok
 */
public class MinPathSum {

    public static void main(String[] args){
        MinPathSum test = new MinPathSum();
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        long start = System.currentTimeMillis();
        int res = test.minPathSum(grid);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        return 0;
    }
}
