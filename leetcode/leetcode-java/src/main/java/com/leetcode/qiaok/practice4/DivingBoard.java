package com.leetcode.qiaok.practice4;

import java.util.Arrays;

/**
 * 面试题 16.11. 跳水板
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。
 * 你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 *
 * 返回的长度需要从小到大排列。
 *
 * 示例：
 *
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： {3,4,5,6}
 * 提示：
 *
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 *
 * @since 2020-08-08
 * @author qiaok
 */
public class DivingBoard {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        DivingBoard test = new DivingBoard();
        int shorter = 0;
        int longer = 0;
        int k = 0;
        long start = System.currentTimeMillis();
        int[] res = test.divingBoard(shorter,longer,k);
        System.out.println("耗时"+(System.currentTimeMillis() -start)+"毫秒");
        System.out.println("res="+ Arrays.toString(res));
    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public int[] divingBoard(int shorter, int longer, int k) {

        return new int[0];
    }
}
