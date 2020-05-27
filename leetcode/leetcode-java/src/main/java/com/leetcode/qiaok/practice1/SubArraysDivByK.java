package com.leetcode.qiaok.practice1;

import java.util.HashMap;
import java.util.Map;

/**
 *      974. 和可被 K 整除的子数组
 *      给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 *
 *      示例：
 *      输入：A = [4,5,0,-2,-3,1], K = 5
 *      输出：7
 *      解释：
 *      有 7 个子数组满足其元素之和可被 K = 5 整除：
 *      [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 *      提示：
 *
 *      1 <= A.length <= 30000
 *      -10000 <= A[i] <= 10000
 *      2 <= K <= 10000
 *
 *      通常，涉及连续子数组问题的时候，我们使用前缀和来解决。
 *
 * @since 2020-05-27
 * @author qiaok
 */
public class SubArraysDivByK {

    /**
     * 测试
     * @param args
     */
    public static  void  main(String[] args){
        SubArraysDivByK test = new SubArraysDivByK();
        int[] nums = { 4,5,0,-2,-3,1 };
        int k = 5;
        long start = System.currentTimeMillis();
        int res = test.subArraysDivByK1(nums,k);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);

    }

    /**
     * 哈希表 + 逐一统计
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(min(N,K))
     * @param nums
     * @param k
     * @return
     */
    private int subArraysDivByK(int[] nums, int k) {
        Map<Integer,Integer> record = new HashMap<Integer,Integer>();
        record.put(0,1);
        int sum = 0, ans =0;
        for (int elem :nums) {
            sum += elem;
            int modulus = (sum % k + k) % k;
            int same = record.getOrDefault(modulus,0);
            ans += same;
            record.put(modulus,same + 1);
        }

        return ans;
    }


    /**
     * 哈希表 + 单次统计
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(min(N,K))
     * @param nums
     * @param k
     * @return
     */
    private int subArraysDivByK1(int[] nums, int k) {
        Map<Integer,Integer> record = new HashMap<Integer,Integer>();
        record.put(0,1);
        int sum = 0;
        for (int elem :nums) {
            sum += elem;
            int modulus = (sum % k + k) % k;
            record.put(modulus,record.getOrDefault(modulus, 0) + 1);
        }

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry: record.entrySet()) {
            ans += entry.getValue() * (entry.getValue() - 1) / 2;
        }
        return ans;
    }
}
