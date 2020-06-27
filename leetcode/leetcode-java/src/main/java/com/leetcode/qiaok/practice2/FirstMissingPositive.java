package com.leetcode.qiaok.practice2;

/**
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 * 提示：
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 *
 * https://leetcode-cn.com/problems/first-missing-positive/
 *
 * @since 2020-06-28
 * @author qiaok
 */
public class FirstMissingPositive {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        FirstMissingPositive test = new FirstMissingPositive();
        int[] nums = { };
        long start = System.currentTimeMillis();
        int res  = test.firstMissingPositive(nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 时间复杂度：
     * 空间复杂度：
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        
        return 0;
    }
}
