package com.leetcode.qiaok.practice1;

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
 * @since 2020-07-08
 * @author qiaok
 */
public class DivingBoard {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        DivingBoard test = new DivingBoard();
        int shorter = 1;
        int longer = 2;
        int k = 3;
        long start = System.currentTimeMillis();
        int[] res = test.divingBoard1(shorter,longer,k);
        System.out.println("耗时"+(System.currentTimeMillis() -start)+"毫秒");
        System.out.println("res="+ Arrays.toString(res));
    }

    /**
     * 数学
     * 时间复杂度：O(k)
     * 空间复杂度：O(1)
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if(k == 0){
            return new int[0];
        }

        if(shorter == longer){
            return new int[]{shorter*k};
        }

        int[] lengths = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            lengths[i] = shorter*(k-i) +longer*i;
        }

        return lengths;
    }

    /**
     * 数学
     * 时间复杂度：O(k)
     * 空间复杂度：O(1)
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public int[] divingBoard1(int shorter, int longer, int k) {
        if(k==0) return new int[]{};
        if(shorter==longer) return new int[]{shorter*k};
        int[] dp=new int[k+1];
        //这个地方要乘以k
        dp[0]=shorter*k;
        for (int i = 1; i <=k ; i++) {
            dp[i]=dp[i-1]+longer-shorter;
        }
        return dp;
    }
}
