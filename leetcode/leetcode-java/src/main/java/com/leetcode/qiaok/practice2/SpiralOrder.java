package com.leetcode.qiaok.practice2;

import java.util.Arrays;

/**
 * 面试题29. 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 * 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
 * @since 2020-06-06
 * @author qiaok
 */
public class SpiralOrder {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        SpiralOrder test = new SpiralOrder();
        int[][] nums = new int[3][3];
        nums[0][0] = 1;nums[0][1] = 2;nums[0][2] = 3;
        nums[1][0] = 4;nums[1][1] = 5;nums[1][2] = 6;
        nums[2][0] = 7;nums[2][1] = 8;nums[2][2] = 9;

        long start = System.currentTimeMillis();
        int[] res =  test.spiralOrder(nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+ Arrays.toString(res));
    }

    /**
     * 时间复杂度：
     * 空间复杂度：
     * @param nums
     * @return
     */
    private int[] spiralOrder(int[][] nums) {
        return new int[0];
    }
}
