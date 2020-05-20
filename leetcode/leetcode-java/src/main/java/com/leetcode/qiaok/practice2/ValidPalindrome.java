package com.leetcode.qiaok.practice2;

/**
 * 680. 验证回文字符串 Ⅱ
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 *
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * 注意:
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 *
 * @since 2020-05-20
 * @author qiaok
 */
public class ValidPalindrome {

    public static void main(String[] args){
        ValidPalindrome test = new ValidPalindrome();
        String s = "abcaa";
        long start = System.currentTimeMillis();
        boolean res = test.validPalindrome(s);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 双指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param s
     * @return
     */
    private boolean validPalindrome(String s) {
        int start = 0;int end = s.length() - 1;
        while(start<end){
            if(s.charAt(start) == s.charAt(end)){
                start++;
                end--;
            }else{
                boolean flag1 = true;boolean flag2=true;
                for(int i = start,j=end-1;start<end;i++,j--){
                    if(s.charAt(i)!=s.charAt(j)){
                        flag1=false;
                        break;
                    }
                }
                for(int i = start+1,j=end;start<end;i++,j--){
                    if(s.charAt(i)!=s.charAt(j)){
                        flag2=false;
                        break;
                    }
                }
                return flag1||flag2;
            }
        }
        return true;
    }
}
