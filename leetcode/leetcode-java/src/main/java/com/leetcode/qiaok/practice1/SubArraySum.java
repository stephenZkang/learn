package com.leetcode.qiaok.practice1;

import java.util.HashMap;

/**
 *  560. 和为K的子数组
 *      给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *      示例 1 :
 *
 *          输入:nums = [1,1,1], k = 2
 *          输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 *      说明 :
 *
 *          数组的长度为 [1, 20,000]。
 *          数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 * @since 2020-05-15
 * @author qiaok
 */
public class SubArraySum {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        int[] nums = {1,1,1};
        int k = 2;
        SubArraySum test = new SubArraySum();
        long start = System.currentTimeMillis();
        int res = test.subArraySum1(nums,k);
        System.out.println("耗时："+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 枚举
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param nums
     * @param k
     * @return
     */
    private int subArraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            int sum = 0;
            for (int j = i; j >= 0; --j) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * 前缀和 + 哈希表优化
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @param k
     * @return
     */
    private int subArraySum1(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap< Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k))
                count += mp.get(pre - k);
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }


}
