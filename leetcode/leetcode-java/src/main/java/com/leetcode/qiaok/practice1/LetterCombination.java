package com.leetcode.qiaok.practice1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
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
 * @since 2020-08-26
 * @author qiaok
 */
public class LetterCombination {

    public static void main(String[] args){
        LetterCombination test = new LetterCombination();
        String digits = "23";
        long start = System.currentTimeMillis();
        List<String> res = test.letterCombiantion(digits);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+ res.toString());
    }

    /**
     * 回溯
     * 时间复杂度：
     * 空间复杂度：
     * @param digits
     * @return
     */
    public List<String> letterCombiantion(String digits) {
        List<String> combinations = new ArrayList<String>();
        if(digits.length() == 0){
            return combinations;
        }

        Map<Character,String> phoneMap = new HashMap<Character,String>(){{
            put('2',"abc");
            put('3',"def");
            put('4',"ghi");
            put('5',"jkl");
            put('6',"mno");
            put('7',"pqrs");
            put('8',"tuv");
            put('9',"wxyz");
        }};
        backtrack(combinations,phoneMap,digits,0,new StringBuffer());
        return combinations;
    }

    private void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }
}
