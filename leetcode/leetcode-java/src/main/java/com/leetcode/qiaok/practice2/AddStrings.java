package com.leetcode.qiaok.practice2;

/**
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 * 
 * @since 2020-08-04
 * @author qiaok
 */
public class AddStrings {

    public static void main(String[] args){
        AddStrings test = new AddStrings();
        String num1 = "111";
        String num2 = "2222";
        long start = System.currentTimeMillis();
        String res  = test.addStrings(num1,num2);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 模拟
     * 时间复杂度：
     * 空间复杂度：
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int add = 0;
        StringBuffer buffer = new StringBuffer();
        while(i>=0||j>=0||add!=0){
            int x = i>=0 ?num1.charAt(i) - '0' : 0 ;
            int y = j>=0 ?num2.charAt(j) - '0' : 0 ;
            int result = x + y + add;
            buffer.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        buffer.reverse();
        return buffer.toString();
    }
}
