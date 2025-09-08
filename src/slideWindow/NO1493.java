package slideWindow;

/*
 1493 删掉一个元素以后全为1的最长子数组

给你一个二进制数组 nums ，你需要从中删掉一个元素。
请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
如果不存在这样的子数组，请返回 0 。

示例 1：
输入：nums = [1,1,0,1]
输出：3
解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。

示例 2：
输入：nums = [0,1,1,1,0,1,1,0,1]
输出：5
解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。

示例 3：
输入：nums = [1,1,1]
输出：2
解释：你必须要删除一个元素。
*/
public class NO1493 {
    public static void main(String[] args) {
        int[] nums = {1,1,1};
        Solution solution = new Solution();
        System.out.println(solution.longestSubarray(nums));
    }

    static class Solution {
        public int longestSubarray(int[] nums) {
            int left = 0;
            int maxLen = 0;
            int[] dp = new int[2];
            for(int right = 0; right < nums.length; right++) {
                int temp = nums[right];
                dp[temp]++;
                while(dp[0] > 1) {
                    dp[nums[left]]--;
                    left++;
                }
                maxLen = Math.max(maxLen, right - left);
            }
            return maxLen;
        }
    }
}
