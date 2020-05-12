package com.leetcode.qiaok.practice2;

/**
 *  50. Pow(x, n)
 *      实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *      示例 1:
 *
 *          输入: 2.00000, 10
 *          输出: 1024.00000
 *      示例 2:
 *          输入: 2.10000, 3
 *          输出: 9.26100
 *      示例 3:
 *
 *          输入: 2.00000, -2
 *          输出: 0.25000
 *          解释: 2-2 = 1/22 = 1/4 = 0.25
 *      说明:
 *          -100.0 < x < 100.0
 * @since 2020-05-12
 * @author qiaok
 */
public class MyPow {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        double x = 2.00;
        int n = -10;
        MyPow test = new MyPow();
        long start = System.currentTimeMillis();
        double res = test.myPow(x,n);
        System.out.println("耗时："+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 递归
     * @param x
     * @param n
     * @return
     */
    private double myPow(double x, int n) {
        long N = n;
        return N > 0 ? quickMul(x,N):1/quickMul(x,-N);
    }

    private double quickMul(double x, long N) {
        if(N == 0){
            return 1.0;
        }
        double y = quickMul(x, N/2);
        return (N % 2 == 0)?y*y:y*y*x;
    }

}
