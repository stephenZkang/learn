package com.leetcode.qiaok.practice2;

/**
 *      974. 和可被 K 整除的子数组
 *      给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 *
 *      示例：
 *      输入：A = [4,5,0,-2,-3,1], K = 5
 *      输出：7
 *      解释：
 *      有 7 个子数组满足其元素之和可被 K = 5 整除：
 *      [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 *      提示：
 *
 *      1 <= A.length <= 30000
 *      -10000 <= A[i] <= 10000
 *      2 <= K <= 10000
 *
 * @since 2020-05-28
 * @author qiaok
 */
public class SubArraysDivByK {

    /**
     * 测试
     * @param args
     */
    public static  void  main(String[] args){
        SubArraysDivByK test = new SubArraysDivByK();
        int[] nums = {};
        int k = 0;
        long start = System.currentTimeMillis();
        int res = test.subArraysDivByK(nums,k);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);

    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param nums
     * @param k
     * @return
     */
    private int subArraysDivByK(int[] nums, int k) {
        return 0;
    }
}
