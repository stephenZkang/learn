package com.leetcode.qiaok.practice3;

import java.util.List;

/**
 * 336. 回文对
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 *
 * 示例 1:
 *
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2:
 *
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 *
 * https://leetcode-cn.com/problems/palindrome-pairs/
 *
 * @since 2020-08-13
 * @author qiaok
 */
public class PalindromePair {
    public static void main(String[] args){
        PalindromePair test = new PalindromePair();
        String[] words = {

        };
        long start = System.currentTimeMillis();
        List<List<Integer>> res = test.palindromePair(words);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res=" + res.toString());
    }

    /**
     * 时间复杂度：
     * 空间复杂度：
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePair(String[] words) {
        return null;
    }
}
