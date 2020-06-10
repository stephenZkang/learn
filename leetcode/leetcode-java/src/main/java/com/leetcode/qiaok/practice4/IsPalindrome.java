package com.leetcode.qiaok.practice4;

/**
 * 9. 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * @since 2020-07-10
 * @author qiaok
 */
public class IsPalindrome {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        IsPalindrome test = new IsPalindrome();
        int x = -121;
        long start = System.currentTimeMillis();
        boolean res = test.isPalindrome(x);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        return true;
    }

    /**
     * 时间复杂度：O(logN)
     * 空间复杂度：O(1)
     * @param x
     * @return
     */
    public boolean isPalindrome1(int x) {
        //思考：这里大家可以思考一下，为什么末尾为 0 就可以直接返回 false
       return false;
    }

}
