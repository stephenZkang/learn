package com.leetcode.qiaok.practice3;


/**
 * 剑指 Offer 20. 表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 * @since 2020-09-09
 * @author qiaok
 */
public class IsNumber {

    public static void main(String[] args){
        IsNumber test = new IsNumber();
        String s = "1a3.14";
        long start = System.currentTimeMillis();
        boolean res = test.isNumber(s);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res=" + res);
    }

    /**
     * 时间复杂度：
     * 空间复杂度：
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        return false;
    }


}
