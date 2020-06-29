package com.leetcode.qiaok.practice2;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * @since 2020-06-30
 * @author qiaok
 */
public class FindKthLargest {

    public static void main(String[] args){
        FindKthLargest test = new FindKthLargest();
        int[] nums = { 3,2,1,5,6,4 };
        int k = 2;
        long start = System.currentTimeMillis();
        int res = test.findKthLarget(nums,k);
        System.out.println("耗时"+(System.currentTimeMillis() -start)+"毫秒");
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
    public int findKthLarget(int[] nums, int k) {
        return 0;
    }
}
