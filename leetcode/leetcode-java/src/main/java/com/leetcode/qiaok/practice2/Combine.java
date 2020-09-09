package com.leetcode.qiaok.practice2;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *  https://leetcode-cn.com/problems/combinations/
 * @since 2020-09-09
 * @author qiaok
 */
public class Combine {

    public static void main(String[] args){
        Combine test = new Combine();
        int n = 4; int k = 2;

        long start = System.currentTimeMillis();
        List<List<Integer>> res = test.combine(n,k);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res=" + res.toString());
    }

    /**
     *
     * 时间复杂度：
     * 空间复杂度：
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(n<=0||n<k){
            return res;
        }

        Deque<Integer> path = new LinkedList<>();
        dfs(n,k,1,path,res);
        return res;
    }

    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        if(path.size() == k){
            res.add(new ArrayList<>(path));
        }
        for (int i = begin; i <= n; i++) {
            path.addLast(i);
            dfs(n,k,i+1,path,res);
            path.removeLast();
        }
    }
}
