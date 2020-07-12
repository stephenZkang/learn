package com.leetcode.qiaok.practice4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * @since 2020-07-12
 * @author qiaok
 */
public class ThreeSum {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        ThreeSum test = new ThreeSum();
        int[] nums = { -1, 0, 1, 2, -1, -4 };
        long start = System.currentTimeMillis();
        List<List<Integer>> res = test.threeSum(nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res=" + res.toString());
    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums == null){
            return ans;
        }
        int len = nums.length;
        if(len<3){
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if(nums[i]>0)break;
            if(i>0&&nums[i] == nums[i-1])continue;
            int L = i+1;
            int R = len-1;
            while(L<R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }else if(sum<0) L++;
                else R--;
            }
        }
        return ans;
    }
}
