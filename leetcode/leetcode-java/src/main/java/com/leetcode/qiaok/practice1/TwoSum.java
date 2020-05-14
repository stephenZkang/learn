package com.leetcode.qiaok.practice1;

import java.util.HashMap;
import java.util.Map;

/**
 *  1. 两数之和
 *      给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那
 *      两个 整数，并返回他们的数组下标。
 *      你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *      示例:
 *
 *          给定 nums = [2, 7, 11, 15], target = 9
 *          因为 nums[0] + nums[1] = 2 + 7 = 9
 *          所以返回 [0, 1]
 *
 *
 * @since 2020-05-14
 * @author qiaok
 */
public class TwoSum {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        int[] nums = {2, 7, 11, 15}; int target = 9;
        TwoSum test = new TwoSum();
        long start = System.currentTimeMillis();
        int[] res = test.twoNum(nums,target);
        System.out.println("耗时："+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res.toString());
    }

    /**
     * 暴力
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * 利用hashmap
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    private int[] twoNum(int[] nums,int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ;i< nums.length;i++){
            map.put(nums[i],i);
        }
        for(int i = 0 ;i< nums.length;i++){
            int c = target-nums[i];
            if(map.containsKey(c)&&map.get(c)!=i){
                return new int[]{i,map.get(c)};
            }
        }
        return new int[0];
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }



}
