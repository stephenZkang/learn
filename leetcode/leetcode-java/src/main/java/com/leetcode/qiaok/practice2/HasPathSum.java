package com.leetcode.qiaok.practice2;

import com.leetcode.qiaok.practice1.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 112. 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * @since 2020-07-08
 * @author qiaok
 */
public class HasPathSum {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        HasPathSum test = new HasPathSum();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        int sum = 22;
        long start = System.nanoTime();
        boolean res = test.hasPathSum1(root,sum);
        System.out.println("耗时"+(System.nanoTime()-start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 递归
     * 时间复杂度：
     * 空间复杂度：
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }

        if(root.left == null&&root.right == null){
            return root.val == sum;
        }
        return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val);
    }

    /**
     * 广度优先搜索
     * 时间复杂度：
     * 空间复杂度：
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum1(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> queTre = new LinkedList<>();
        Queue<Integer> queVal = new LinkedList<>();
        queTre.offer(root);
        queVal.offer(root.val);
        while(!queTre.isEmpty()){
            TreeNode now = queTre.poll();
            Integer temp = queVal.poll();
            if(now.left==null&&now.right==null){
                if(temp == sum){
                    return true;
                }
                continue;
            }

            if(now.left!=null){
                queTre.offer(now.left);
                queVal.offer(now.left.val+temp);
            }

            if(now.right!=null){
                queTre.offer(now.right);
                queVal.offer(now.right.val+temp);
            }

        }
        return false;
    }
}
