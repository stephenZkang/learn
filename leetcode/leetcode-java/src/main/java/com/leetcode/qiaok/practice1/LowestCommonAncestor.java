package com.leetcode.qiaok.practice1;

/**
 *  236. 二叉树的最近公共祖先
 *      给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *      百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 *      最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度
 *      尽可能大（一个节点也可以是它自己的祖先）。”
 *
 *      例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *           3
 *       5      1
 *     6   2  0   8
 *       7  4
 *
 *      示例 1:
 *
 *          输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *          输出: 3
 *          解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *      示例 2:
 *
 *          输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 *          输出: 5
 *          解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 *      说明:
 *          所有节点的值都是唯一的。
 *          p、q 为不同节点且均存在于给定的二叉树中。
 *
 * @since 2020-05-10
 * @author qiaok
 */
public class LowestCommonAncestor {


    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        LowestCommonAncestor test = new LowestCommonAncestor();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        System.out.println("root:"+root.toString());

        TreeNode p = new TreeNode(5);
        System.out.println("p:"+p.val);

        TreeNode q = new TreeNode(1);
        System.out.println("q:"+q.val);


        long start = System.currentTimeMillis();
        TreeNode res = test.lowestCommonAncestor1(root,p,q);
        System.out.println("耗时：" + (System.currentTimeMillis()-start) + "毫秒");
        System.out.println("res:"+res.val);

    }

    private TreeNode ans;

    /**
     *  方法一：递归
     *  时间复杂度：O(N)
     *  空间复杂度：O(N)
     *  https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/
     * @param root
     * @param p
     * @param q
     * @return
     */
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    /**
     *  方法一：递归
     *  时间复杂度：O(N)
     *  空间复杂度：O(N)
     *  https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/
     * @param root
     * @param p
     * @param q
     * @return
     */
    private TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        //结论0
        if (root == null || root.val == p.val || root.val == q.val)
            return root;
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        //结论1
        if (left != null && left.val != q.val && left.val != p.val)
            return left;
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        //结论3
        if (left != null && right != null)
            return root;
            //结论2 4
        else
            return left == null ? right : left;
    }

}
