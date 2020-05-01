package com.leetcode.qiaok.practice1;

import java.util.*;

/**
 * 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 */
public class RightSideView {

    /**
     * 方法一：深度优先搜索
     * 思路
     *
     * 我们对树进行深度优先搜索，在搜索过程中，我们总是先访问右子树。那么对于每一层来说，我们在这层见到的第一个结点一定是最右边的结点。
     *
     * 算法
     *
     * 这样一来，我们可以存储在每个深度访问的第一个结点，一旦我们知道了树的层数，就可以得到最终的结果数组。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view/solution/er-cha-shu-de-you-shi-tu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     */
    public List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
        int max_depth = -1;

        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        Stack<Integer> depthStack = new Stack<Integer>();
        nodeStack.push(root);
        depthStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            if (node != null) {
                // 维护二叉树的最大深度
                max_depth = Math.max(max_depth, depth);

                // 如果不存在对应深度的节点我们才插入
                if (!rightmostValueAtDepth.containsKey(depth)) {
                    rightmostValueAtDepth.put(depth, node.val);
                }

                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth+1);
                depthStack.push(depth+1);
            }
        }

        List<Integer> rightView = new ArrayList<Integer>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;

    }

    /**
     *方法二：广度优先搜索
     * 思路
     *
     * 我们可以对二叉树进行层次遍历，那么对于每层来说，最右边的结点一定是最后被遍历到的。二叉树的层次遍历可以用广度优先搜索实现。
     *
     * 算法
     *
     * 执行广度优先搜索，左结点排在右结点之前，这样，我们对每一层都从左到右访问。因此，只保留每个深度最后访问的结点，我们就可以在遍历完整棵树后得到每个深度最右的结点。除了将栈改成队列，并去除了rightmost_value_at_depth之前的检查外，算法没有别的改动。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view/solution/er-cha-shu-de-you-shi-tu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     */
    public List<Integer> rightSideView1(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
        int max_depth = -1;

        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<Integer> depthQueue = new LinkedList<Integer>();
        nodeQueue.add(root);
        depthQueue.add(0);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            int depth = depthQueue.remove();

            if (node != null) {
                // 维护二叉树的最大深度
                max_depth = Math.max(max_depth, depth);

                // 由于每一层最后一个访问到的节点才是我们要的答案，因此不断更新对应深度的信息即可
                rightmostValueAtDepth.put(depth, node.val);

                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
                depthQueue.add(depth+1);
                depthQueue.add(depth+1);
            }
        }

        List<Integer> rightView = new ArrayList<Integer>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;
    }

    public static void main(String[] args) {
        RightSideView test = new RightSideView();
        TreeNode nums = new TreeNode(1);
        List<Integer> rightView = test.rightSideView(nums);
        System.out.println("res:"+rightView.toString());
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
