package com.leetcode.qiaok.practice3;

import java.util.Arrays;

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
 * @since 2020-07-06
 * @author qiaok
 */
public class FindKthLargest {

    public static void main(String[] args){
        FindKthLargest test = new FindKthLargest();
        int[] nums = { 3,2,1,5,6,4 };
        int k = 2;
        long start = System.currentTimeMillis();
        int res = test.findKthLarget1(nums,k);
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
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    /**
     * 基于堆排序的选择方法
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     * @param nums
     * @param k
     * @return
     */
    public int findKthLarget1(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    private void buildMaxHeap(int[] nums, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(nums, i, heapSize);
        }
    }


    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
