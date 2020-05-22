package com.leetcode.qiaok.practice4;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 说明：
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @since 2020-06-16
 * @author qiaok
 */
public class ReverserKGroup {

    public static void main(String[] args){
        ReverserKGroup test = new ReverserKGroup();
        com.leetcode.qiaok.practice1.ListNode root = new com.leetcode.qiaok.practice1.ListNode(1);
        root.next = new com.leetcode.qiaok.practice1.ListNode(2);
        root.next.next = new com.leetcode.qiaok.practice1.ListNode(3);
        root.next.next.next = new com.leetcode.qiaok.practice1.ListNode(4);
        root.next.next.next.next = new com.leetcode.qiaok.practice1.ListNode(5);
        int k = 2;
        long start = System.currentTimeMillis();
        com.leetcode.qiaok.practice1.ListNode res = test.reverseKGroup(root,k);
        System.out.println("耗时："+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 递归
     * 时间复杂度：
     * 空间复杂度：
     * @param head
     * @param k
     * @return
     */
    private com.leetcode.qiaok.practice1.ListNode reverseKGroup1(com.leetcode.qiaok.practice1.ListNode head, int k) {
        Deque<com.leetcode.qiaok.practice1.ListNode> stack = new ArrayDeque<com.leetcode.qiaok.practice1.ListNode>();
        com.leetcode.qiaok.practice1.ListNode dummy = new com.leetcode.qiaok.practice1.ListNode(0);

        return dummy.next;
    }

    /**
     * 时间复杂度：
     * 空间复杂度：
     * @param head
     * @param k
     * @return
     */
    private com.leetcode.qiaok.practice1.ListNode reverseKGroup(com.leetcode.qiaok.practice1.ListNode head, int k) {
        com.leetcode.qiaok.practice1.ListNode dummy = new com.leetcode.qiaok.practice1.ListNode(0);

        return dummy.next;
    }

    private com.leetcode.qiaok.practice1.ListNode reverse(com.leetcode.qiaok.practice1.ListNode head) {
        com.leetcode.qiaok.practice1.ListNode pre = null;
        com.leetcode.qiaok.practice1.ListNode curr = head;
        while (curr != null) {
            com.leetcode.qiaok.practice1.ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}
