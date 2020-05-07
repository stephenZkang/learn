package com.leetcode.qiaok.practice1;

/**
 *  572. 另一个树的子树
 *  给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
 *  s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 *  示例 1:
 *      给定的树 s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *      给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 *      返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 *
 *   示例 2:
 *    给定的树 s：
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 *   给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 false。
 *
 * @since 2020-05-07
 * @author qiaok
 */
public class IsSubtree {

    /**
     * 双层递归
     *
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(n)
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null){
            return false;
        }
        if(isSame(s,t)){
            return true;
        }else{
            return isSubtree(s.left,t) || isSubtree(s.right,t);
        }
    }

    private boolean isSame(TreeNode s, TreeNode t) {
        if(s ==null&&t==null){
            return true;
        }
        if(s == null || t == null){
            return false;
        }
        if(s.val != t.val){
            return false;
        }else{
            return isSame(s.left,t.left) && isSame(s.right,t.right);
        }
    }

    boolean flag = false;

    /**
     * 基于深度优先
     *
     * 时间复杂度：O(log n)
     * 空间复杂度：O(log n)
     */
    public boolean isSubtree1(TreeNode s, TreeNode t) {
        if(s == null){
            return false;
        }
        flag = true;
        helper(s, t);

        if(!flag) isSubtree1(s.left, t);
        if(!flag) isSubtree1(s.right, t);

        return flag;
    }

    private void helper(TreeNode s, TreeNode t) {
        if(s == null && t == null) return;
        else if(s != null && t != null){
            if(s.val == t.val){
                helper(s.left, t.left);
                helper(s.right, t.right);
            }
            else flag = false;
        }
        else{
            flag = false;
            return;
        }
    }

    public static void main(String[] args) {
        IsSubtree test = new IsSubtree();
        TreeNode s = new TreeNode(3);
        s.left = new TreeNode(4);
        s.left.left = new TreeNode(1);
        s.left.right = new TreeNode(2);
        s.right = new TreeNode(5);
        System.out.println("s:"+s.toString());

        TreeNode t = new TreeNode(4);
        t.left = new TreeNode(1);
        t.right = new TreeNode(2);
        System.out.println("t:"+t.toString());

        boolean res = test.isSubtree1(s,t);
        System.out.println("res:"+res);
    }
}

class TreeNode {
   int val;
   TreeNode left;
   TreeNode right;
   TreeNode(int x) { val = x; }

}
