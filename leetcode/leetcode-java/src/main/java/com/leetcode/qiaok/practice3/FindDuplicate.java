package com.leetcode.qiaok.practice3;

/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
 * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * 难度：中等
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * @since 2020-06-02
 * @author qiaok
 */
public class FindDuplicate {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        FindDuplicate test = new FindDuplicate();
        int[] nums = {1,3,4,2,2};
        long start = System.currentTimeMillis();
        int res = test.findDuplicate(nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param nums
     * @return
     */
    private int findDuplicate(int[] nums) {
        return 0;
    }
}
