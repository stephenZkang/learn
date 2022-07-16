package com.leetcode.qiaok.practice3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 350. 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 *
 * 说明：
 *
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * @since 2020-07-20
 * @author qiaok
 */
public class Intersect {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        Intersect test = new Intersect();
        int[] nums1 = {
                4,9,5
        };
        int[] nums2 = {
                9,4,9,8,4
        };

        long start = System.currentTimeMillis();
        int[] res = test.intersect(nums1,nums2);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+ Arrays.toString(res));

    }

    /**
     * 
     * 时间复杂度：
     * 空间复杂度：
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            intersect(nums2,nums1);
        }

        Map<Integer,Integer> temp = new HashMap<>();
        for (int num:nums1) {
            int count = temp.getOrDefault(num,0)+1;
            temp.put(num,count);
        }

        int[] ins = new int[nums1.length];
        int index =0;
        for (int num:nums2) {
            int count = temp.getOrDefault(num, 0);
            if (count > 0) {
                ins[index++] = num;
                count--;
                if (count > 0) {
                    temp.put(num, count);
                } else {
                    temp.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(ins, 0, index);

    }

    public int[] intersect1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);

    }
}
