package com.leetcode.qiaok.practice1;

/**
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 *
 * 提示：
 *
 * 输入的字符串长度不会超过 1000 。
 *
 * @since 2020-08-19
 * @author qiaok
 */
public class CountSubstrings {

    public static void main(String[] args){
        CountSubstrings test = new CountSubstrings();
        String s  = "abc";
        long start = System.currentTimeMillis();
        int res = test.countSubstrings2(s);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);

    }

    /**
     * 中心拓展
     *
     *  计算有多少个回文子串的最朴素方法就是枚举出所有的回文子串，而枚举出所有的回文字串又有两种思路，分别是：
     *      枚举出所有的子串，然后再判断这些子串是否是回文；
     *      枚举每一个可能的回文中心，然后用两个指针分别向左右两边拓展，当两个指针指向的元素相同的时候就拓展，否则停止拓展
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param s
     * @return
     */
    public int countSubstrings2(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

    /**
     * 动态规划
     * 时间复杂度：
     * 空间复杂度：
     * @param s
     * @return
     */
    public int countSubstrings1(String s) {
        if(s == null || s.equals("")){
            return 0;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int result = s.length();
        for(int i = 0; i<n; i++) dp[i][i] = true;
        for(int i = n-1; i>=0; i--){
            for(int j = i+1; j<n; j++){
                if(s.charAt(i) == s.charAt(j)) {
                    if(j-i == 1){
                        dp[i][j] = true;
                    }
                    else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }else{
                    dp[i][j] = false;
                }
                if(dp[i][j]){
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 最笨的办法
     * 超出时间限制
     * 时间复杂度：
     * 空间复杂度：
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int length = s.length();
        int sum = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j <= length; j++) {
                String sub = s.substring(i, j);
                if(isCount(sub)){
                    sum++;
                }
            }
        }
        return sum;
    }

    private boolean isCount(String sub) {
        if(sub.length() == 1){
            return true;
        }
        int start = 0, end = sub.length()-1;
        while(start<end){
            if(sub.charAt(start)!=sub.charAt(end)){
                return false;
            }
        }
        return true;
    }
}
