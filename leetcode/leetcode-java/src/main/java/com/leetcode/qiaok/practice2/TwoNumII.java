package com.leetcode.qiaok.practice2;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 * 难度：简单
 * @since 2020-07-21
 * @author qiaok
 */
public class TwoNumII {


    /**
     *
     * @param args
     */
    public static void main(String[] args){
        TwoNumII test = new TwoNumII();
        int[] numbers = { 2, 7, 11, 15 };
        int target = 9;
        long start = System.currentTimeMillis();
        int[] res  = test.twoNum(numbers,target);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+ Arrays.toString(res));
    }

    /**
     * 时间复杂度：
     * 空间复杂度：
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoNum(int[] numbers, int target) {

        throw new RuntimeException("");
    }
}
