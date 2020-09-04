package com.leetcode.qiaok.practice3;

import com.leetcode.qiaok.practice1.TreeNode;

import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 * https://leetcode-cn.com/problems/binary-tree-paths/
 *
 * @since 2020-09-11
 * @author qiaok
 */
public class BinaryTreePaths {

    public static void main(String[] args){
        BinaryTreePaths test = new BinaryTreePaths();
        TreeNode root = new TreeNode(1);
        long start = System.currentTimeMillis();
        List<String> res = test.binaryTreePaths(root);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+ res.toString());

    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param root
     * @return
     */
    private List<String> binaryTreePaths(TreeNode root) {
        return null;
    }
}
