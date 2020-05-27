package com.leetcode.qiaok.practice2;

/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
 * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * 难度：中等
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * @since 2020-05-27
 * @author qiaok
 */
public class FindDuplicate {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        FindDuplicate test = new FindDuplicate();
        int[] nums = {1,3,4,2,2};
        long start = System.currentTimeMillis();
        int res = test.findDuplicate(nums);
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"毫秒");
        System.out.println("res="+res);
    }

    /**
     * 二分查找
     * 时间复杂度：
     * 空间复杂度：
     * @param nums
     * @return
     */
    private int findDuplicate(int[] nums) {
        int n = nums.length;int ans = -1;
        int l =0; int r = n-1;
        while(l<=r){
            int mid = (l+r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if(nums[i]<= nums[mid]){
                    cnt++;
                }
            }
            if(cnt<=mid){
                l = mid+1;
            }else{
                r = mid -1;
                ans = mid;
            }
        }
        return ans;
    }

    /**
     * 二进制
     * 时间复杂度：
     * 空间复杂度：
     * @param nums
     * @return
     */
    private int findDuplicate1(int[] nums) {
        int n = nums.length;int ans = 0;
        int bit_max = 31;
        while(((n-1)>>bit_max)==0){
            bit_max-=1;
        }

        for (int bit = 0; bit < bit_max; ++bit) {
            int x = 0;int y = 0;
            for (int i = 0; i < n; ++i) {
                if((nums[i]&(1<<bit))!=0){
                    x+=1;
                }
                if(i>=1&&(i&(1<<bit))!=0){
                    y+=1;
                }
            }
            if(x>y){
                ans |= 1 << bit;
            }
        }
        return ans;
    }

    /**
     * 快慢指针
     * 时间复杂度：
     * 空间复杂度：
     * @param nums
     * @return
     */
    private int findDuplicate2(int[] nums) {
        int slow = 0; int fast =0;
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow!=fast);
        slow=0;
        while (slow!=fast){
            slow=nums[slow];
            fast=nums[fast];
        }
        return slow;

    }
}
