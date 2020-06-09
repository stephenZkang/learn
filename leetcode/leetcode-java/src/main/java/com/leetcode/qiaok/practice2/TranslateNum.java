package com.leetcode.qiaok.practice2;

/**
 * 面试题46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，
 * 1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 * 提示：
 *  0 <= num < 231
 *
 * @since 2020-06-10
 * @author qiaok
 */
public class TranslateNum {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        TranslateNum test = new TranslateNum();
        int num = 12258;
        long start = System.currentTimeMillis();
        int res = test.translateNum(num);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 时间复杂度：
     * 空间复杂度：
     * @param num
     * @return
     */
    public int translateNum(int num) {
        return 0;
    }
}
