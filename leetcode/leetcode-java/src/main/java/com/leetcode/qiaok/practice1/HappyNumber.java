package com.leetcode.qiaok.practice1;

import java.util.HashSet;
import java.util.Set;

/**
 *  202. 快乐数
 *    编写一个算法来判断一个数 n 是不是快乐数。
 *
 *   「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 *    然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 *    如果 可以变为  1，那么这个数就是快乐数。
 *    如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *
 *  示例：
 *
 *      输入：19
 *      输出：true
 *      解释：
 *          12 + 92 = 82
 *          82 + 22 = 68
 *          62 + 82 = 100
 *          12 + 02 + 02 = 1
 *
 * @since 2020-05-01
 * @author qiaok
 */
public class HappyNumber {

    /**
     * 用 HashSet 检测循环
     *
     * 时间复杂度：O(log n)
     * 空间复杂度：O(log n)
     * @param n
     * @return
     */
    private boolean isHappy(int n){
        Set<Integer> set = new HashSet<>();
        set.add(n);
        while(true){
            n = create(n);
            if(n == 1){
                return true;
            }

            if(set.contains(n)){
                return false;
            }
            set.add(n);
        }
    }


    private int create(int n) {
        int sum = 0;
        while(n!=0){
            sum += n % 10 * (n % 10);
            n /= 10;
        }
        return sum;
    }

    /**
     * 快慢指针法
     *
     * 时间复杂度：O(log n)
     * 空间复杂度：O(1)
     * @param n
     * @return
     */
    private boolean isHappy1(int n){
        int slowRunner = n;
        int fastRunner = getNext(n);
        while(fastRunner != 1 && slowRunner != fastRunner){
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public static void main(String[] args) {
        int n = 19;
        HappyNumber test = new HappyNumber();
        boolean happy = test.isHappy1(n);
        System.out.println(happy);
    }
}
