package com.leetcode.qiaok.practice1;

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
 *          n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 * @since 2020-05-11
 * @author qiaok
 */
public class MyPow {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        MyPow test = new MyPow();
        double x = 2.000;
        int n = 10;
        long start = System.currentTimeMillis();
        double res = test.myPow1(x,n);
        System.out.println("耗时："+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     *  快速幂 + 递归
     * 时间复杂度：O(log n)
     * 空间复杂度：O(log n)
     * @param x
     * @param n
     * @return
     */
    private double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x,N) : 1.0/quickMul(x,-N);
    }

    private double quickMul(double x, long N) {
        if(N == 0 ){
            return 1.0;
        }
        double y = quickMul(x,N/2);
        return N % 2 ==0 ? y * y:y * y * x ;
    }

    /**
     *  快速幂 + 迭代
     * 时间复杂度：O(log n)
     * 空间复杂度：1
     * @param x
     * @param N
     * @return
     */
    double quickMul1(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    public double myPow1(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul1(x, N) : 1.0 / quickMul1(x, -N);
    }


}
