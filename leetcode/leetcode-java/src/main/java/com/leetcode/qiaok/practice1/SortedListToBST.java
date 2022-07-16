package com.leetcode.qiaok.practice1;

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
 * @since 2020-08-18
 * @author qiaok
 */
public class SortedListToBST {

    public static void main(String[] args){
        SortedListToBST test = new SortedListToBST();
        ListNode node = new ListNode(-10);
        node.next = new ListNode(-3);
        node.next.next = new ListNode(0);
        node.next.next = new ListNode(5);
        node.next.next.next = new ListNode(9);
        long start = System.currentTimeMillis();
        TreeNode tree = test.sortedListToBST(node);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("tree=" + tree);
    }

    /**
     * 快慢指针
     * 时间复杂度：
     * 空间复杂度：
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        //边界条件的判断
        if (head == null)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);
        //这里通过快慢指针找到链表的中间结点slow，pre就是中间
        //结点slow的前一个结点
        ListNode slow = head, fast = head, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //链表断开为两部分，一部分是node的左子节点，一部分是node
        //的右子节点
        pre.next = null;
        //node就是当前节点
        TreeNode node = new TreeNode(slow.val);
        //从head节点到pre节点是node左子树的节点
        node.left = sortedListToBST(head);
        //从slow.next到链表的末尾是node的右子树的结点
        node.right = sortedListToBST(slow.next);
        return node;
    }
}

