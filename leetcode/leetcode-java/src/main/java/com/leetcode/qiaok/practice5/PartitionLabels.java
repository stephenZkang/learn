package com.leetcode.qiaok.practice5;

import java.util.List;

/**
 * 763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *
 *
 * 提示：
 *
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 *
 * @author QIAOK
 * @title: PartitionLabels
 * @projectName leetcode-java
 * @description:
 * @date 2020/10/22 8:41
 */
public class PartitionLabels {

    public static void main(String[] args){
        PartitionLabels test = new PartitionLabels();
        String S = "";
        long start = System.currentTimeMillis();
        List<Integer> res = test.partitionLabels(S);
        System.out.println("耗时："+(System.currentTimeMillis() - start));
        System.out.println("res="+res.toString());
    }

    /**
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        return null;
    }
}
