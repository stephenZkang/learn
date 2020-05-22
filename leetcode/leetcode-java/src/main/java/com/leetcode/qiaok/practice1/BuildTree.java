package com.leetcode.qiaok.practice1;

import java.util.Stack;

/**
 *      105. 从前序与中序遍历序列构造二叉树
 *     根据一棵树的前序遍历与中序遍历构造二叉树。
 *     难度：中等
 *     注意:
 *     你可以假设树中没有重复的元素。
 *     例如，给出
 *
 *     前序遍历 preorder = [3,9,20,15,7]
 *     中序遍历 inorder = [9,3,15,20,7]
 *     返回如下的二叉树：
 *
 *         3
 *        / \
 *       9  20
 *         /  \
 *        15   7
 * @since 2020-05-22
 * @author qiaok
 */
public class BuildTree {

    /**
     * 测试
     * @param args
     */
    public static  void main(String[] args){
        BuildTree test = new BuildTree();
        int[] preorder = { 3,9,20,15,7 };
        int[] inorder = { 9,3,15,20,7 };
        long start = System.currentTimeMillis();
        TreeNode res = test.buildTree(preorder,inorder);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res=" + res);
    }

    /**
     * 迭代
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param preorder
     * @param inorder
     * @return
     */
    private TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }
}
