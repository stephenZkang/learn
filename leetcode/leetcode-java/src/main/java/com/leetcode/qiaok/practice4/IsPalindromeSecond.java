package com.leetcode.qiaok.practice4;


/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 * https://leetcode-cn.com/problems/valid-palindrome/
 * @since 2020-07-19
 * @author qiaok
 */
public class IsPalindromeSecond {

    public static void main(String[] args){
        IsPalindromeSecond test = new IsPalindromeSecond();
        String s = "A man, a plan, a canal: Panama";
        long start = System.currentTimeMillis();
        boolean res = test.isPalindrome(s);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);

    }

    /**
     * 时间复杂度：
     * 空间复杂度：
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        return false;
    }
}
