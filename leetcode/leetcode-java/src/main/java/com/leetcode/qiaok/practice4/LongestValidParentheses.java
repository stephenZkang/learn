package com.leetcode.qiaok.practice4;


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
 * @since 2020-08-04
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
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == ')'){
                if(s.charAt(i-1) == '('){
                    dp[i] = (i>=2?dp[i-2]:0)+2;
                }else if(i - dp[i-1]>0 && s.charAt(i-dp[i-1]-1)=='('){
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}
