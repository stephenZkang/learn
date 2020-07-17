package com.leetcode.qiaok.practice1;

/**
 *1248. 统计「优美子数组」
 * 给你一个整数数组 nums 和一个整数 k。
 *
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 *
 * 请返回这个数组中「优美子数组」的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 *
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 *
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 *
 * @since 2020-04-20
 * @author qiaok
 *
 */
public class BeautifulArray {

    /**
     *  双指针，数组可以分成三个部分：
     *
     *      包含 k 个奇数的区间（左右边界都是奇数）
     *      k 个奇数前面的偶数
     *      k 个奇数后面的偶数
     *      有种思路是分别计算前后的偶数个数，相乘加到结果里面。
     *      我的做法是先计算前面的偶数个数，后面遍历时遇到偶数直接加进来就可以了
     *      判断奇偶的操作可以继续用位运算来优化。。。
     *
     *  作者：kelly2018
     *  链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays/solution/java-shuang-zhi-zhen-by-kelly2018/
     *  来源：力扣（LeetCode）
     */
    public int numberOfSubarrays1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) return 0;
        // 双指针
        int left = 0, right = 0;
        int count = 0; // 连续子数组中奇数的个数
        int res = 0;
        int preEven = 0; // 记录第一个奇数前面的偶数个数
        while (right < nums.length){
            // 连续子数组中奇数个数不够
            if (count < k){
                if (nums[right] % 2 != 0) count++;
                right++; // 移动右侧指针
            }
            // 连续子数组中奇数个数够了，看第一个奇数前面有多少个偶数
            if (count == k) {
                preEven = 0;
                while (count == k){
                    res++;
                    if (nums[left] % 2 != 0) count--;
                    left++;
                    preEven++;
                }
            } else res += preEven; // 每次遇到 right 为偶数的时候就进行累加 相当于区间前面偶数个数 * 后面偶数个数
        }
        return res;
    }

    /**
     *  一、滑动窗口
     *      1、不断右移 right 指针来扩大滑动窗口，使其包含 k 个奇数；
     *      2、若当前滑动窗口包含了 k 个奇数，则如下「计算当前窗口的优美子数组个数」：
     *          统计第 1 个奇数左边的偶数个数 leftEvenCnt。 这 leftEvenCnt 个偶数都可以作为「优美子数组」的起点，
     *          因此起点的选择有 leftEvenCnt + 1 种（因为可以一个偶数都不取，因此别忘了 +1 喔）。
     *          统计第 k 个奇数右边的偶数个数 rightEvenCnt 。 这 rightEvenCnt 个偶数都可以作为「优美子数组」的终点，
     *          因此终点的选择有 rightEvenCnt + 1 种（因为可以一个偶数都不取，因此别忘了 +1 喔）。
     *          因此「优美子数组」左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)。
     *
     *      时间复杂度 O(N)，
     *      空间复杂度 O(1)
     *
     */
    public int numberOfSubarrays5(int[] nums, int k) {
        int left = 0, right = 0, oddCnt = 0, res = 0;
        while (right < nums.length) {
            // 右指针先走，每遇到一个奇数则 oddCnt++。
            if ((nums[right++] & 1) == 1) {
                oddCnt++;
            }

            //  若当前滑动窗口 [left, right) 中有 k 个奇数了，进入此分支统计当前滑动窗口中的优美子数组个数。
            if (oddCnt == k) {
                // 先将滑动窗口的右边界向右拓展，直到遇到下一个奇数（或出界）
                // rightEvenCnt 即为第 k 个奇数右边的偶数的个数
                int tmp = right;
                while (right < nums.length && (nums[right] & 1) == 0) {
                    right++;
                }
                int rightEvenCnt = right - tmp;
                // leftEvenCnt 即为第 1 个奇数左边的偶数的个数
                int leftEvenCnt = 0;
                while ((nums[left] & 1) == 0) {
                    leftEvenCnt++;
                    left++;
                }
                // 第 1 个奇数左边的 leftEvenCnt 个偶数都可以作为优美子数组的起点
                // (因为第1个奇数左边可以1个偶数都不取，所以起点的选择有 leftEvenCnt + 1 种）
                // 第 k 个奇数右边的 rightEvenCnt 个偶数都可以作为优美子数组的终点
                // (因为第k个奇数右边可以1个偶数都不取，所以终点的选择有 rightEvenCnt + 1 种）
                // 所以该滑动窗口中，优美子数组左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)
                res += (leftEvenCnt + 1) * (rightEvenCnt + 1);

                // 此时 left 指向的是第 1 个奇数，因为该区间已经统计完了，因此 left 右移一位，oddCnt--
                left++;
                oddCnt--;
            }

        }

        return res;
    }

    /**
     *  二、前缀和
     *      1、计算前缀和数组 arr：遍历原数组，每遍历一个元素，计算当前的前缀和（即到当前元素为止，数组中有多少个奇数）；
     *      2、对上述前缀和数组，双重循环统计 arr[j] - arr[i] == k 的个数，这样做是 O(N^2)O(N) 的（这里会超时哦）。
     *      优化：
     *          因此，我们可以像「1. 两数之和」那样使用 HashMap 优化到 O(N)，键是「前缀和」，
     *          值是「前缀和的个数」（下面代码中具体使用的是 int[] prefixCnt 数组，下标是「前缀和」，值是「前缀和的个数」），
     *          因此我们可以遍历原数组，每遍历到一个元素，计算当前的前缀和 sum，就在 res 中累加上前缀和为 sum - k 的个数。
     *  时间复杂度 O(N)，
     *  空间复杂度 O(N)
     *

     */
    public int numberOfSubarrays(int[] nums, int k) {
        // 数组 prefixCnt 的下标是前缀和（即当前奇数的个数），值是前缀和的个数。
        int[] prefixCnt = new int[nums.length + 1];
        prefixCnt[0] = 1;
        // 遍历原数组，计算当前的前缀和，统计到 prefixCnt 数组中，
        // 并且在 res 中累加上与当前前缀和差值为 k 的前缀和的个数。
        int res = 0, sum = 0;
        for (int num: nums) {
            sum += num & 1;
            prefixCnt[sum]++;
            if (sum >= k) {
                res += prefixCnt[sum - k];
            }
        }
        return res;
    }

    /**
     *  方法一：动态规划
     *  思路：
     *      (1) 看到题首先想到通过动态规划记录两个索引之间的奇数个数。
     *      (2) 运行LeetCode超时，可以想到两种优化方式就是使用哈希表或者双指针减少一层循环。
     *
     *  时间复杂度
     *  空间复杂度
     */
    public int numberOfSubarrays2(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k < 1) {
            return 0;
        }
        int ans = 0;
        // 定义 dp 数组记录两个索引之间的奇数个数。
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            if (nums[i] % 2 == 1) {
                dp[i][i] = 1;
                if (k == 1) {
                    ans++;
                }
            }
        }
        // 双层循环遍历，如果符合 k 个奇数则计数 +1 。
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = dp[i][j-1] + dp[j][j];
                if (dp[i][j] == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     *  方法二：数学组合
     *  思路：
     *      (1) 记录每个奇数位置的角标。
     *      (2) 根据两个相邻奇数之间的位置数计算组合方式的个数。
     *
     *  时间复杂度
     *  空间复杂度
     */
    public int numberOfSubarrays3(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k < 1) {
            return 0;
        }
        int ans = 0;
        // 定义数组保存第几个奇数的角标。
        int[] d = new int[len + 2];
        // 初始化 d[0] 表示第一个奇数之前有角标 +1 个位置。
        d[0] = -1;
        // 初始化从第一个奇数开始记录角标。
        int counts = 1;
        for (int i = 0; i < len; i++) {
            if (nums[i] % 2 == 1) {
                d[counts++] = i;
            }
        }
        // 存完奇数角标后赋值 d[counts] 为 len ，表示最后一个奇数之后有 len 减角标个位置。
        d[counts++] = len;
        // 遍历奇数角标计算「优美子数组」的个数。
        for (int i = 1; i + k < counts; i++) {
            // 当前奇数到前一个奇数之间位置个数乘以第 k 个奇数到后一个奇数之间位置个数得出组合个数。
            ans += (d[i] - d[i - 1]) * (d[i + k] - d[i + k - 1]);
        }
        return ans;
    }


    /**
     *  方法三：前缀统计 + 差分运算
     *  思路：
     *      (1) 定义数组建立前缀奇数个数和可以出现的次数之间的映射关系。
     *      (2) 记录每个相差 k 个奇数的前缀奇数出现的次数。
     *      (3) 返回记录的总次数。
     *
     *  时间复杂度
     *  空间复杂度
     */
    public int numberOfSubarrays4(int[] nums, int k) {
        // 定义数组，下标是前缀奇数的个数，值是出现的次数。
        int[] countsTimes = new int[nums.length + 1];
        // 初始化当前角标前面有 0 个奇数的情况出现一次。
        countsTimes[0] = 1;
        int ans = 0, counts = 0;
        for (int num: nums) {
            // 每遇到一个奇数则个数 +1 。
            counts += num & 1;
            // 每遇到一个偶数则出现次数 +1 。
            countsTimes[counts]++;
            if (counts >= k) {
                // 每次累加到当前位置 k 个奇数的出现次数。
                ans += countsTimes[counts - k];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        BeautifulArray test = new BeautifulArray();
        int[] nums = { 1, 2, 3, 1, 1, 2, 33 };
        int k = 3;
        long start = System.currentTimeMillis();
        System.out.println("start="+start);
        int res = test.numberOfSubarrays(nums,k);
        System.out.println("res:"+res);
    }
}
