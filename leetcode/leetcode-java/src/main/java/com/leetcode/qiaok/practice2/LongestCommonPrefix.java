package com.leetcode.qiaok.practice2;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * @since 2020-06-16
 * @author qiaok
 */
public class LongestCommonPrefix {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        LongestCommonPrefix test = new LongestCommonPrefix();
        String[] strs = { "flower","flow","flight" };
        long start = System.currentTimeMillis();
        String res = test.longestCommonPrefix(strs);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
       if(strs == null || strs.length == 0){
           return null;
       }

        int count = strs.length;
       String prefix = strs[0];
        for (int i = 0; i < count; i++) {
            prefix = longestCommonPrefix1(prefix,strs[i]);
            if(prefix.length() == 0){
                break;
            }
        }
        return prefix;
    }

    private String longestCommonPrefix1(String prefix, String str) {
        int length = Math.min(prefix.length(), str.length());
        int index = 0;
        while(index < length && prefix.charAt(index) == str.charAt(index)){
            index++;
        }
        return str.substring(0,index);
    }


}
