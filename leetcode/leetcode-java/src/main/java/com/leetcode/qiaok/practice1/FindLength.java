package com.leetcode.qiaok.practice1;

import java.util.HashSet;
import java.util.Set;

/**
 * 718. 最长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * 示例 1:
 *
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 * 说明:
 *
 *  1 <= len(A), len(B) <= 1000
 *  0 <= A[i], B[i] < 100
 *
 * @since 2020-07-01
 * @author qiaok
 */
public class FindLength {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        FindLength test = new FindLength();
        int[] A = { 1,2,3,2,1 };
        int[] B = { 3,2,1,4,7 };
        long start = System.currentTimeMillis();
        int len = test.findLength2(A,B);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("len="+len);
    }

    /**
     * 动态规划
     * 时间复杂度：O(N×M)
     * 空间复杂度：O(N×M)
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        int n = A.length;int m = B.length;
        int[][] dp = new int[n+1][m+1];
        int ans = 0;
        for (int i = n-1; i >=0 ; i--) {
            for (int j = m - 1; j >=0 ; j--) {
                dp[i][j] = A[i] == B[j]?dp[i+1][j+1]+1:0;
                ans = Math.max(ans,dp[i][j]);
            }
        }
        return ans;
    }


    /**
     * 滑动窗口
     * 时间复杂度：O((N+M)×min(N,M))。
     * 空间复杂度： O(1)
     * @param A
     * @param B
     * @return
     */
    public int findLength1(int[] A, int[] B) {
        int n = A.length;int m = B.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxLen = maxLength(A,B,i,0, len);
            ret = Math.max(ret,maxLen);

        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxlen = maxLength(A, B, 0, i, len);
            ret = Math.max(ret, maxlen);
        }
        return ret;
    }

    public int maxLength(int[] A, int[] B, int addA, int addB, int len) {
        int ret = 0, k =0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }

    int mod = 1000000009;
    int base = 113;

    /**
     * 二分查找 + 哈希
     * 时间复杂度：O((M+N)log(min(M,N)))
     * 空间复杂度：O(N)
     * @param A
     * @param B
     * @return
     */
    public int findLength2(int[] A, int[] B) {
        int left = 1, right = Math.min(A.length, B.length) + 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(A, B, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    public boolean check(int[] A, int[] B, int len) {
        long hashA = 0;
        for (int i = 0; i < len; i++) {
            hashA = (hashA * base + A[i]) % mod;
        }
        Set<Long> bucketA = new HashSet<Long>();
        bucketA.add(hashA);
        long mult = qPow(base, len - 1);
        for (int i = len; i < A.length; i++) {
            hashA = ((hashA - A[i - len] * mult % mod + mod) % mod * base + A[i]) % mod;
            bucketA.add(hashA);
        }
        long hashB = 0;
        for (int i = 0; i < len; i++) {
            hashB = (hashB * base + B[i]) % mod;
        }
        if (bucketA.contains(hashB)) {
            return true;
        }
        for (int i = len; i < B.length; i++) {
            hashB = ((hashB - B[i - len] * mult % mod + mod) % mod * base + B[i]) % mod;
            if (bucketA.contains(hashB)) {
                return true;
            }
        }
        return false;
    }

    // 使用快速幂计算 x^n % mod 的值
    public long qPow(long x, long n) {
        long ret = 1;
        while (n != 0) {
            if ((n & 1) != 0) {
                ret = ret * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return ret;
    }

}
