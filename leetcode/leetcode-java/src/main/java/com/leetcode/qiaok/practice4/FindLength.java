package com.leetcode.qiaok.practice4;

/**
 * 718. 最长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * 示例 1:
 *
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 * 说明:
 *
 *  1 <= len(A), len(B) <= 1000
 *  0 <= A[i], B[i] < 100
 *
 * @since 2020-08-01
 * @author qiaok
 */
public class FindLength {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        FindLength test = new FindLength();
        int[] A = {};
        int[] B = {};
        long start = System.currentTimeMillis();
        int len = test.findLength(A,B);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("len="+len);
    }

    /**
     * 时间复杂度：
     * 空间复杂度：
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        return 0;
    }
}
