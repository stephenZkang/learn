package com.leetcode.qiaok.practice3;

import java.util.Stack;

/**
 *  155. 最小栈
 *      设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *          push(x) —— 将元素 x 推入栈中。
 *          pop() —— 删除栈顶的元素。
 *          top() —— 获取栈顶元素。
 *          getMin() —— 检索栈中的最小元素。
 *      示例:
 *
 *          输入：
 *              ["MinStack","push","push","push","getMin","pop","top","getMin"]
 *              [[],[-2],[0],[-3],[],[],[],[]]
 *      输出：
 *          [null,null,null,null,-3,null,0,-2]
 *
 *      解释：
 *          MinStack minStack = new MinStack();
 *          minStack.push(-2);
 *          minStack.push(0);
 *          minStack.push(-3);
 *          minStack.getMin();   --> 返回 -3.
 *          minStack.pop();
 *          minStack.top();      --> 返回 0.
 *          minStack.getMin();   --> 返回 -2.
 *      提示：
 *          pop、top 和 getMin 操作总是在 非空栈 上调用。
 *
 * @since 2020-05-19
 * @author qiaok
 */
public class MinStack {

    // 数据栈
    private Stack<Integer> data;
    // 辅助栈
    private Stack<Integer> helper;

    public MinStack() {

    }

    public void push(int x) {

    }

    public void pop() {

    }

    public int top() {

        throw new RuntimeException("栈中元素为空，此操作非法");
    }

    public int getMin() {

        throw new RuntimeException("栈中元素为空，此操作非法");
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args){

    }
}
