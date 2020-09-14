package com.leetcode.qiaok.practice1;


import java.util.ArrayList;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @since 2020-09-14
 * @author qiaok
 */
public class InOrderTraversal {

    public static void  main(String[] args){
        InOrderTraversal test = new InOrderTraversal();
        TreeNode root = new TreeNode(1);
        long start = System.currentTimeMillis();
        List<Integer> rest = test.inorderTraversal(root);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res"+rest.toString());

    }

    /**
     * 递归
     * 时间复杂度：
     * 空间复杂度：
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> arr = new ArrayList<>();
        inOrder(root,arr);
        return arr;
    }

    private void inOrder(TreeNode root, List<Integer> arr) {
        if(root!=null){
            inOrder(root.left,arr);
            arr.add(root.val);
            inOrder(root.right,arr);
        }
    }

}
