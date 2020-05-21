package com.leetcode.qiaok.practice3;

import com.leetcode.qiaok.practice1.TreeNode;

/**
 *  102. 二叉树的层序遍历
 *      给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *      示例：
 *      二叉树：[3,9,20,null,null,15,7],
 *
 *             3
 *            / \
 *           9  20
 *             /  \
 *            15   7
 *         返回其层次遍历结果：
 *
 *         [
 *           [3],
 *           [9,20],
 *           [15,7]
 *         ]
 * @since 2020-05-20
 * @author qiaok
 */
public class LevelOrder {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        LevelOrder test = new LevelOrder();
        long start = System.currentTimeMillis();
//        List<List<Integer>> res = test.levelOrder1(root);
        System.out.println("耗时："+ (System.currentTimeMillis() - start)+"");
//        System.out.println("res=" + res);
    }




}
