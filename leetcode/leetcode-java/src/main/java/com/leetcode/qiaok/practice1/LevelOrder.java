package com.leetcode.qiaok.practice1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
 * @since 2020-05-13
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
        List<List<Integer>> res = test.levelOrder1(root);
        System.out.println("耗时："+ (System.currentTimeMillis() - start)+"");
        System.out.println("res=" + res);
    }

    /**
     * 广度优先 - 迭代实现
     *      用广度优先处理是很直观的，
     * 时间复杂度：O(N)
     * 空间复杂度：O(h)，h是树的高度
     * @param root
     * @return
     */
    private List<List<Integer>> levelOrder1(TreeNode root) {
        if(root==null) {
            return new ArrayList<List<Integer>>();
        }
        //用来存放最终结果
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(queue.size() > 0){
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            for (int i =0;i<size;i++){
                TreeNode t = queue.remove();
                tmp.add(t.val);
                if(t.left!=null){
                    queue.add(t.left);
                }
                if(t.right!=null){
                    queue.add(t.right);
                }
            }
            res.add(tmp);
        }
        return res;
    }

    /**
     * 广度优先 - 递归实现
     *      用广度优先处理是很直观的，
     * 时间复杂度：O(N)
     * 空间复杂度：O(h)，h是树的高度
     * @param root
     * @return
     */
    private List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null) {
            return new ArrayList<List<Integer>>();
        }
        //用来存放最终结果
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(1,root,res);
        return res;
    }

    void dfs(int index,TreeNode root, List<List<Integer>> res) {
        //假设res是[ [1],[2,3] ]， index是3，就再插入一个空list放到res中
        if(res.size()<index) {
            res.add(new ArrayList<Integer>());
        }
        //将当前节点的值加入到res中，index代表当前层，假设index是3，节点值是99
        //res是[ [1],[2,3] [4] ]，加入后res就变为 [ [1],[2,3] [4,99] ]
        res.get(index-1).add(root.val);
        //递归的处理左子树，右子树，同时将层数index+1
        if(root.left!=null) {
            dfs(index+1, root.left, res);
        }
        if(root.right!=null) {
            dfs(index+1, root.right, res);
        }
    }



}
