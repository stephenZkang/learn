package com.leetcode.qiaok.practice5;

import com.leetcode.qiaok.practice1.ListNode;

/**
 * 143. 重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * https://leetcode-cn.com/problems/reorder-list/
 * @since 2020-10-20
 * @author qiaok
 */
public class ReOrderList {

    public static void main(String[] args){
        ReOrderList test = new ReOrderList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        long start = System.currentTimeMillis();
        test.reorderList(head);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");

    }

    /**
     *
     * @param head
     */
    public void reorderList(ListNode head) {


    }
}
