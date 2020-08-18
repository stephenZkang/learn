package com.leetcode.qiaok.practice2;

import com.leetcode.qiaok.practice1.ListNode;
import com.leetcode.qiaok.practice1.TreeNode;

/**
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *  
 * @since 2020-08-19
 * @author qiaok
 */
public class SortedListToBST {

    public static void main(String[] args){
        SortedListToBST test = new SortedListToBST();
        ListNode node = new ListNode(1);
        long start = System.currentTimeMillis();
        TreeNode tree = test.sortedListToBST(node);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("tree=" + tree);
    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param node
     * @return
     */
    public TreeNode sortedListToBST(ListNode node) {
        return null;
    }
}

