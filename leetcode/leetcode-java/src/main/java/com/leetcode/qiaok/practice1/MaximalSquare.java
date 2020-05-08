package com.leetcode.qiaok.practice1;

/**
 *  221. 最大正方形
 *      在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *      示例:
 *          输入:
 *              1 0 1 0 0
 *              1 0 1 1 1
 *              1 1 1 1 1
 *              1 0 0 1 0
 *          输出: 4
 */
public class MaximalSquare {

    /**
     *  动态规划
     *
     *  时间复杂度：O(mn)
     *  空间复杂度：O(mn)
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }

    /**
     *
     * @param matrix
     * @return
     */
    public int maximalSquare1(char[][] matrix) {
        // base condition
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;

        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;

        // 相当于已经预处理新增第一行、第一列均为0
        //  int[][] dp = new int[height + 1][width + 1];
        int[] dp = new int[width + 1];
        int northwest = 0; // 西北角、左上角

        //  for (int row = 0; row < height; row++) {
        for (char[] chars : matrix) {
            northwest = 0; // 遍历每行时，还原回辅助的原值0
            for (int col = 0; col < width; col++) {
                int nextNorthwest = dp[col + 1];
                if (chars[col] == '1') {
                    //              dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]), dp[row][col]) + 1;
                    dp[col + 1] = Math.min(Math.min(dp[col], dp[col + 1]), northwest) + 1;

                    //              maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                    maxSide = Math.max(maxSide, dp[col + 1]);
                } else {
                    dp[col + 1] = 0;
                }
                northwest = nextNorthwest;
            }
        }
        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        char[][] nums = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        MaximalSquare test = new MaximalSquare();
        int result = test.maximalSquare(nums);
        System.out.println(result);
    }
}
