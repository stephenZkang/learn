package com.leetcode.qiaok.practice1;

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
 * @since 2020-05-21
 * @author qiaok
 */
public class LongestPalindrome {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        LongestPalindrome test = new LongestPalindrome();
        String s = "babad";
        long start = System.currentTimeMillis();
        String res = test.longestPalinedrome1(s);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 动态规划
     * 时间复杂度：O(n²）
     * 空间复杂度：O(1）
     * @param s
     * @return
     */
    private String longestPalinedrome1(String s) {
        if (s.isEmpty()) {
            return "";
        }
        int len = s.length();
        // 定义二维数组记录原字符串 i 到 j 区间是否为回文子串。
        boolean[][] dp = new boolean[len][len];
        // 记录遍历过的最长回文子串。
        String ans = "";
        // 记录遍历过的最长回文子串长度。
        int maxLen = 0;
        // 遍历元素并得到包含当前元素之前字符串的最大回文子串。
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                // 状态转移，判断记录 i 到 j 位置是否为回文子串。
                dp[i][j] = s.charAt(i) == s.charAt(j)
                        && ((j - i <= 2) || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    // 判断更新记录遍历过的最长回文子串。
                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        ans = s.substring(i, j + 1);
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 扩展中心算法
     * 时间复杂度：O(n²）
     * 空间复杂度：O(1）
     * @param s
     * @return
     */
    private String longestPalinedrome(String s) {
        if(s ==null||s.length() < 1){return "";}
        int start = 0; int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s,i,i);
            int len2 = expandAroundCenter(s,i,i+1);
            int len = Math.max(len1,len2);
            if(len > end- start){
                start = i -(len-1)/2;
                end = i + len /2;
            }
        }
        return s.substring(start,end + 1);
    }

    private int expandAroundCenter(String s, int start, int end) {
        int L = start;int R = end;
        while(L>=0 && R < s.length()&&s.charAt(L) == s.charAt(R)){
            L--;
            R++;
        }
        return R-L-1;
    }
}
