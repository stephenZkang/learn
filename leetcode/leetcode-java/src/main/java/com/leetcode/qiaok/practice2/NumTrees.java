package com.leetcode.qiaok.practice2;

/**
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *    难度：中等
 *    https://leetcode-cn.com/problems/unique-binary-search-trees/
 * @since 2020-07-16
 * @author qiaok
 */
public class NumTrees {

    public static void main(String[] args){
        NumTrees test = new NumTrees();
        int n = 0 ;
        long start = System.currentTimeMillis();
        int res = test.numTrees(n);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 
     * 时间复杂度：
     * 空间复杂度：
     * @param n
     * @return
     */
    public int numTrees(int n) {
        return 0;
    }
}
