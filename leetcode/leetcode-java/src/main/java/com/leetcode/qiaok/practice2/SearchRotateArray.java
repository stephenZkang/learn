package com.leetcode.qiaok.practice2;

import java.lang.invoke.SerializedLambda;

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
 *
 * @since 2020-05-01
 * @author qiaok
 */
public class SearchRotateArray {
    /**
     * 二分查找
     * 时间复杂度： O(log n)
     * 空间复杂度： n
     * @param nums
     * @param target
     * @return
     */
    private int search(int[] nums, int target){
       if(nums==null || nums.length==0){
           return -1;
       }
       int start = 0 ; int end = nums.length - 1;
       while(start <= end){
           int mid = start + (end - start) / 2;
           if(nums[mid] == target){
               return mid;
           }
           if(target >= nums[0]){
                if(nums[mid] < nums[0]){
                    nums[mid] = Integer.MAX_VALUE;
                }
           }else{
                if(nums[mid] >= nums[0]){
                    nums[mid] = Integer.MIN_VALUE;
                }
           }

           if(nums[mid] < target){
               start = mid + 1;
           }else{
               end = mid - 1;
           }

       }
       return -1;
    }
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        SearchRotateArray searchRotateArray = new SearchRotateArray();
        int result = searchRotateArray.search(nums, target);
        System.out.println(result);

    }
}
