package com.leetcode.qiaok.practice2;

import java.util.Arrays;

/**
 * 378. 有序矩阵中第K小的元素
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 *
 * 示例：
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * 返回 13。
 *
 *
 * 提示：
 * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。
 *
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
 *
 * @since 2020-07-03
 * @author qiaok
 */
public class KthSmallest {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        KthSmallest test = new KthSmallest();
        int[][] matrix = {
            {1,  5,  9},
            {10, 11, 13},
            {12, 13, 15}
        };
        int k = 8;
        long start = System.currentTimeMillis();
        int res = test.kthSmallest(matrix,k);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);

    }

    /**
     * 暴力破解
     * 时间复杂度：
     * 空间复杂度：
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] nums = new int[n*m];
        int x = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nums[x] = matrix[i][j];
                x++;
            }

        }
        Arrays.sort(nums);
        return nums[k-1];
    }

    /**
     * 二分查找
     * 时间复杂度：
     * 空间复杂度：
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest1(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }
}
