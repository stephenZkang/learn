package com.leetcode.qiaok.practice2;

import com.leetcode.qiaok.practice1.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 * 示例：
 *
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *
 * 提示：
 *
 * 0 <= n <= 8
 * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
 * 难度：中等
 * @since 2020-07-22
 * @author qiaok
 */
public class GenerateTrees {

    public static void main(String[] args){
        GenerateTrees test = new GenerateTrees();
        int n = 3;
        long start = System.currentTimeMillis();
        List<TreeNode>  res = test.generateTrees(n);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res"+res);
    }

    /**
     * 递归
     * 时间复杂度：
     * 空间复杂度：
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if(n == 0){
            return new LinkedList<>();
        }

        return generateTrees(1,n);
    }

    public List<TreeNode> generateTrees(int start,int end) {
        List<TreeNode> temp = new LinkedList<>();
        if(start>end){
            temp.add(null);
            return temp;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, end);
            for (TreeNode ln :leftTrees) {
                for (TreeNode rn: rightTrees) {
                    TreeNode cur = new TreeNode(i);
                    cur.left = ln;
                    cur.right = rn;
                    temp.add(cur);
                }
            }


        }

        return temp;
    }
}
