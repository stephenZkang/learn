package com.leetcode.qiaok.practice1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *      136. 只出现一次的数字
 *          给定一个非空整数数组，除了某个元素只出现一次以外，
 *          其余每个元素均出现两次。找出那个只出现了一次的元素。
 *      说明：
 *          你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *          示例 1:
 *           输入: [2,2,1]
 *           输出: 1
 *
 *          示例 2:
 *           输入: [4,1,2,1,2]
 *           输出: 4
 * @since 2020-05-14
 * @author qiaok
 */
public class SingleNumber {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        int[] nums = { 2,2,1 };
        SingleNumber test = new SingleNumber();
        long start = System.currentTimeMillis();
        int res = test.singleNumber4(nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 位运算，x ^ x = 1  1 ^ x = x
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    private int singleNumber(int[] nums) {
        int single = 0;
        for(int i =0;i < nums.length;i++){
            single ^= nums[i];
        }
        return single;
    }


    /**
     * 利用hash
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    private int singleNumber1(int[] nums) {
        HashMap<Integer,Integer> temp = new HashMap<>();
        for (int i : nums){
            Integer count = temp.get(i);
            count = count == null ? 1 : ++count;
            temp.put(i,count);
        }
        for (Integer i : temp.keySet()) {
            Integer count = temp.get(i);
            if (count == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 暴力破解
     * 时间复杂度：O(n2)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    private int singleNumber3(int[] nums) {
        for (int i = 0; i < nums.length;i++){
            int equalCount = 0;
            for(int j = 0; j < nums.length;j++){
                if(nums[i] == nums[j]){
                    equalCount += 1;
                }
            }
            // 除本身外没有其他相同的数字了
            if (equalCount == 1)
                return nums[i];
        }
        return -1;
    }

    /**
     * 快速排序
     * 时间复杂度：O(n2)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    private int  singleNumber5(int[] nums) {
        Arrays.sort(nums);
        for(int i=0;i<nums.length - 1;i+=2){//主要边界问题，i+=2，其中有i+1
            if(nums[i] != nums[i+1]){
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    /**
     * 2*（a+b+c） - (a+a+b+b+c) = c - 未成功
     * 时间复杂度：O(n2)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    private int singleNumber4(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
            sum += nums[i];
        }
        int sumMul = 0;
        for (int n : set) {
            sumMul += n;
        }
        sumMul = sumMul * 3;
        return (sumMul - sum) / 2;
    }



    /**
     * 分治算法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    private int singleNumber2(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    // T(n) = T(n/2) + O(n) + O(n/2)
    private int search(int[] arr, int l, int r) {
        // base case
        if (l >= r) return arr[r];
        // O(n) 快排分区
        int p = quickPartition(arr, l, r);

        // T(n/2) 若左侧奇数，则肯定在左侧，递归搜左侧
        if ((p - l) % 2 == 1) return search(arr, l, p - 1);

        // O(n/2) 相同的一定在右侧
        for (int i = p + 1; i < r + 1; i++) {
            if (arr[i] == arr[p]) {
                // 换到p旁边，下次搜右侧时可排除
                int swap = arr[i];
                arr[i] = arr[p + 1];
                arr[p + 1] = swap;
                break;
            }
        }
        // lucky，右侧没有相同的
        if (p + 1 > r || arr[p] != arr[p + 1]) return arr[p];

        // T(n/2) unlucky, 右侧有相同的，递归搜右侧
        return search(arr, p + 2, r); // p + 2 排除了 arr[p], arr[p+1]
    }

    private int quickPartition(int[] arr, int l, int r) {
        int pivot = r, counter = l;
        for (int i = l; i < r; i++) {
            if (arr[i] < arr[pivot]) { // 小的都在左侧，相等的在右侧
                int swap = arr[i];
                arr[i] = arr[counter];
                arr[counter++] = swap;
            }
        }
        int swap = arr[counter];
        arr[counter] = arr[pivot];
        arr[pivot] = swap;
        return counter;
    }
}
