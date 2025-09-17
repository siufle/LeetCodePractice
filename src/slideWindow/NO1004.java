package slideWindow;

/*1004.最大连续1的个数
给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。

示例 1：
输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
输出：6
解释：[1,1,1,0,0,1,1,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 6。

示例 2：
输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
输出：10
解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 10。*/

public class NO1004 {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k = 3;
        Solution solution = new Solution();
        System.out.println(solution.longestOnes(nums, k));
    }
    static class Solution {
        public int longestOnes(int[] nums, int k) {
            int left = 0;
            int maxCount = 0;
            int[] res = {0, 0};
            for(int right = 0; right < nums.length; right++) {
                int num = nums[right];
                res[num]++;
                while(res[0] > k){
                    int temp = nums[left];
                    res[temp]--;
                    left++;
                }
                maxCount = Math.max(maxCount, right - left + 1);
            }
            return maxCount;
        }
    }
}
