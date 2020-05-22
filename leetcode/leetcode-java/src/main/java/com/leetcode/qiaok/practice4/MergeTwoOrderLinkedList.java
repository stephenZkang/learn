package com.leetcode.qiaok.practice4;

/**
 *  21. 合并两个有序链表
 *  将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 *  示例：
 *
 *  输入：1->2->4, 1->3->4
 *  输出：1->1->2->3->4->4
 *
 * @since 2020-06-01
 * @author qiaok
 */
public class MergeTwoOrderLinkedList {

    /**
     * 递归
     *  O(n+m)
     *  时间复杂度：  O(n+m)
     *  空间复杂度：  O(n+m)
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        return null;
    }

    /**
     * 迭代
     *  O(n+m)
     *  时间复杂度：  O(n+m)
     *  空间复杂度：  O(1)
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        return null;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        System.out.println(l1.toString());
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        System.out.println(l2.toString());

        MergeTwoOrderLinkedList test = new MergeTwoOrderLinkedList();
        ListNode result = test.mergeTwoLists2(l1, l2);
        System.out.println(result);

    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(val);
        ListNode temp = next;
        while(temp!=null){
            buffer.append(",").append(temp.val);
            temp = temp.next;
        }
        return buffer.toString();
    }
}
