package com.leetcode.qiaok.practice5;

/**
 * 33. 搜索旋转排序数组
 *  假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *  ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *  搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *  你可以假设数组中不存在重复的元素。
 *  你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * @since 2020-05-09
 * @author qiaok
 */
public class SearchRotateArray {

    public static void main(String[] args){
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        SearchRotateArray test = new SearchRotateArray();
        long start = System.currentTimeMillis();
        int res = test.searchRotateArray(nums,target);
        System.out.println("耗时："+(System.currentTimeMillis() - start) +"毫秒");
        System.out.println("res:" + res);
    }

    /**
     * 二分查找
     * 时间复杂度：O(log n)
     * 空间复杂度：O(n)
     * @param nums
     * @param target
     * @return
     */
    private int searchRotateArray(int[] nums, int target) {
        int start =0, end = nums.length - 1;
        while(start<=end){
            int mid = start + (end - start) / 2;
            if(nums[mid] == target){
                return mid;
            }

            if(target > nums[0]){   //目标比第一元素大
                if(nums[mid] <= nums[0]){ //中间值小于第一元素
                    nums[mid] = Integer.MAX_VALUE;
                }
            }else{
                if(nums[mid] > nums[0]){
                    nums[mid] = Integer.MIN_VALUE;
                }
            }

            if(nums[mid] <= target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }
}
