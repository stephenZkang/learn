package com.leetcode.qiaok.practice4;

import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * @since 2020-09-26
 * @author qiaok
 */
public class LetterCombination {

    public static void main(String[] args){
        LetterCombination test = new LetterCombination();
        String digits = "";
        long start = System.currentTimeMillis();
        List<String> res = test.letterCombiantion(digits);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+ res.toString());
    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param digits
     * @return
     */
    public List<String> letterCombiantion(String digits) {
        return null;
    }
}
