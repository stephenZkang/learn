package com.leetcode.qiaok.practice4;

import com.leetcode.qiaok.practice1.TreeNode;

/**
 *    面试题 04.04. 检查平衡性
 *     实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
 *       示例 1:
 *     给定二叉树 [3,9,20,null,null,15,7]
 *         3
 *        / \
 *       9  20
 *         /  \
 *        15   7
 *     返回 true 。
 *     示例 2:
 *     给定二叉树 [1,2,2,3,3,null,null,4,4]
 *           1
 *         / \
 *         2   2
 *        / \
 *       3   3
 *      / \
 *     4   4
 *
 *     返回 false 。
 * @since 2020-06-15
 * @author qiaok
 */
public class IsBalanced {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        IsBalanced test = new IsBalanced();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left= new TreeNode(15);
        root.right.right = new TreeNode(7);
        long start = System.currentTimeMillis();
//        boolean res = test.isBalanced(root);
        System.out.println("耗时："+(System.currentTimeMillis() - start)+"毫秒");
//        System.out.println("res="+res);
    }

}
