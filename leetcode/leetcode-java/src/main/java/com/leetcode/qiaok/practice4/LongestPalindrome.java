package com.leetcode.qiaok.practice4;

/**
 *   5. 最长回文子串
 *    给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 *    示例 1：
 *
 *    输入: "babad"
 *    输出: "bab"
 *    注意: "aba" 也是一个有效答案。
 *    示例 2：
 *
 *    输入: "cbbd"
 *    输出: "bb"
 * @since 2020-06-21
 * @author qiaok
 */
public class LongestPalindrome {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        LongestPalindrome test = new LongestPalindrome();
        String s = "";
        long start = System.currentTimeMillis();
        String res = test.longestPalinedrome(s);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param s
     * @return
     */
    private String longestPalinedrome(String s) {
        return null;
    }
}
