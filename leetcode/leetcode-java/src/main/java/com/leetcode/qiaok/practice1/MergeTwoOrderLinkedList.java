package com.leetcode.qiaok.practice1;

/**
 *  21. 合并两个有序链表
 *  将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 *  示例：
 *
 *  输入：1->2->4, 1->3->4
 *  输出：1->1->2->3->4->4
 *
 * @since 2020-05-01
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
        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }else if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
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
        ListNode result = new ListNode(-1);

        ListNode pre = result;
        while (l1!=null&&l2!=null){
            if(l1.val <= l2.val){
                pre.next = l1;
                l1 = l1.next;
            }else{
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;
        return result.next;
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
