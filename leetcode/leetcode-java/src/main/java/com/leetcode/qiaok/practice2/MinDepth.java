package com.leetcode.qiaok.practice2;

import com.leetcode.qiaok.practice1.TreeNode;

/**
 *  111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 * 
 * @since 2020-08-22
 * @author qiaok
 */
public class MinDepth {

    public static void main(String[] args){
        MinDepth test = new MinDepth();
        TreeNode root = new TreeNode(1);
        long start = System.currentTimeMillis();
        int res = test.minDepth(root);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res=" + res);
    }

    /**
     * 时间复杂度：
     * 空间复杂度：
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        return 0;
    }
}
