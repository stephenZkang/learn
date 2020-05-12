package com.leetcode.qiaok.practice2;

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
 * @since 2020-05-12
 * @author qiaok
 */
public class HappyNumber {
    /**
     * 测试
     * @param args
     */
    public static  void main(String[] args){
        int n = 19;
        HappyNumber test = new HappyNumber();
        long start = System.currentTimeMillis();
        boolean rs = test.isHappyNumer(n);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res=" + rs);
    }

    /**
     * 快慢指针
     * 时间复杂度：O(log n)
     * 空间复杂度：O(1)
     * @param n
     * @return
     */
    private boolean isHappyNumer(int n) {
        int slowRunner = n;
        int quickRunner = getNext(n);
        while (quickRunner !=1 && quickRunner !=slowRunner){
            slowRunner = getNext(slowRunner);
            quickRunner = getNext(getNext(quickRunner));
        }
        return quickRunner == 1;
    }

    private int getNext(int n) {
        int total = 0;
        while(n>0){
            int d = n % 10;
            n = n /10;
            total +=  d*d;
        }
        return total;
    }


}
