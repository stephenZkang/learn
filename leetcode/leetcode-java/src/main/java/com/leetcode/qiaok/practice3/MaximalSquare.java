package com.leetcode.qiaok.practice3;

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
 * @since  2020-05-10
 * @author qiaok
 */
public class MaximalSquare {
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        char[][] nums ={
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };

        MaximalSquare test = new MaximalSquare();
        long start = System.currentTimeMillis();
        int res = test.maximalSquare(nums);
        System.out.println("耗时："+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 动态规划
     * 1.定义子问题 dp[i]
     * 2.写出子问题的递推关系
     * 3.确定 DP 数组的计算顺序
     * 4.优化空间
     * @param nums
     * @return
     */
    private int maximalSquare(char[][] nums) {
        int maxside = 0;
        if(nums == null || nums.length == 0 || nums[0].length == 0){
            return 0 ;
        }
        int rows = nums.length,columns = nums[0].length;
        int[][] dp = new int[rows][columns];

        for(int i = 0 ;i < rows;i++){
            for (int j = 0; j < columns;j++){
                if(nums[i][j] == '1'){
                    if(i == 0||j==0){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]) + 1;
                    }
                    maxside = Math.max(maxside,dp[i][j]);
                }
            }
        }
        return maxside * maxside;
    }
}
