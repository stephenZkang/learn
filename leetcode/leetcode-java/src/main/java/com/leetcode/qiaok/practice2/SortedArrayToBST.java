package com.leetcode.qiaok.practice2;

import com.leetcode.qiaok.practice1.TreeNode;

/**
 * 108. 将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 *
 * @since 2020-07-04
 * @author qiaok
 */
public class SortedArrayToBST {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        SortedArrayToBST test = new SortedArrayToBST();
        int[] nums = {-10,-3,0,5,9};
        long start = System.currentTimeMillis();
        TreeNode rets = test.sortedArrayToBST(nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("ret"+rets.toString());
    }

    /**
     * 二分查找
     * 时间复杂度：
     * 空间复杂度：
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums,0,nums.length-1);
    }


    private TreeNode helper(int[] nums, int left, int right) {
        if(left > right){
            return null;
        }
        int mid = (left+right)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums,left,mid-1);
        root.right = helper(nums,mid+1,right);
        return root;
    }
}
