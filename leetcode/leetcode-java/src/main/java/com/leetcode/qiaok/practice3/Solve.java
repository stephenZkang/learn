package com.leetcode.qiaok.practice3;

/**
 * 130. 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 *
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 通过次数46,964提交次数115,096
 * @since 2020-08-18
 * @author qiaok
 */
public class Solve {

    public static void main(String[] args){
        Solve test  = new Solve();
        char[][] board = {};
        long start = System.currentTimeMillis();
        test.solve(board);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
    }

    /**
     * 时间复杂度：
     * 空间复杂度：
     * @param board
     */
    public void solve(char[][] board) {

    }
}
