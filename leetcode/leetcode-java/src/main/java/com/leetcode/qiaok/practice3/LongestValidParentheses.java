package com.leetcode.qiaok.practice3;


/**
 * 32. 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 * @since 2020-07-11
 * @author qiaok
 */
public class LongestValidParentheses {

    public static void main(String[] args){
        LongestValidParentheses test = new LongestValidParentheses();
        String s = "";
        long start = System.nanoTime();
        int res = test.longestValidParentheses(s);
        System.out.println("耗时"+(System.nanoTime() - start)+"毫秒");
        System.out.println("res="+res);

    }

    /**
     * 
     * 时间复杂度：
     * 空间复杂度：
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        return 0;
    }
}
