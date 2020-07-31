package com.leetcode.qiaok.practice4;

/**
 * 面试题 08.03. 魔术索引
 * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，
 * 编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。
 * 若有多个魔术索引，返回索引值最小的一个。
 *
 * 示例1:
 *
 *  输入：nums = [0, 2, 3, 4, 5]
 *  输出：0
 *  说明: 0下标的元素为0
 * 示例2:
 *
 *  输入：nums = [1, 1, 1]
 *  输出：1
 * 提示:
 *
 * nums长度在[1, 1000000]之间
 *
 * @since 2020-08-30
 * @author qiaok
 */
public class FindMagicIndex {

    public static void main(String[] args){
        FindMagicIndex test = new FindMagicIndex();
        int[] nums = {
                0, 2, 3, 4, 5
        };
        long start = System.currentTimeMillis();
        int res = test.findMagicIndex(nums);
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
    public int findMagicIndex(int[] nums) {
        return -1;
    }
}
