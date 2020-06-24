package com.leetcode.qiaok.practice1;


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
 * @since 2020-06-19
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
     *
     * 时间复杂度：O(|s|)
     * 空间复杂度：O(1)
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        int start = 0;
        int end = s.length() - 1;

        while (start <= end) {
            char c1 = s.charAt(start);
            char c2 = s.charAt(end);
            if (Character.isLetterOrDigit(c1) && Character.isLetterOrDigit(c2)) {
                c1 = Character.toLowerCase(c1);
                c2 = Character.toLowerCase(c2);
                if (c1 != c2) return false;  //shout false if they don't match
                else {
                    start++;
                    end--;
                }
            } else if(!Character.isLetterOrDigit(c1)) start++; //if c1 neither character nor numeral go to next
            else end--; //if C2 is neither character nor numeral go to previous
        }
        return true;
    }
}
