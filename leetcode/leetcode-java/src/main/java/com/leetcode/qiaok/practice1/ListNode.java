package com.leetcode.qiaok.practice1;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

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