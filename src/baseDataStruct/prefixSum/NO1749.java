/*1749. 任意子数组和的绝对值的最大值
给你一个整数数组 nums 。
一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
abs(x) 定义如下：
如果 x 是负整数，那么 abs(x) = -x 。
如果 x 是非负整数，那么 abs(x) = x 。

示例 1：
输入：nums = [1,-3,2,3,-4]
输出：5
解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。

示例 2：
输入：nums = [2,-5,1,-4,3,-2]
输出：8
解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。

提示：
1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
*/

package baseDataStruct.prefixSum;

public class NO1749 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,-5,1,-4,3,-2};
        System.out.println(solution.maxAbsoluteSum(nums));
    }
    static class Solution {
        public int maxAbsoluteSum(int[] nums) {
            int n = nums.length;
            int[] prefixSum = new int[n + 1];
            prefixSum[0] = 0;
            for (int i = 1; i <= n; i++) {
                prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            }
            int[] rMax = new int[n + 1];
            rMax[n] = prefixSum[n];
            int[] rMin = new int[n + 1];
            rMin[n] = prefixSum[n];
            for (int i = n - 1; i >= 0; i--) {
                rMax[i] = Math.max(rMax[i + 1], prefixSum[i]);
                rMin[i] = Math.min(rMin[i + 1], prefixSum[i]);
            }
            int min = prefixSum[0];
            int max = prefixSum[0];
            int res = Math.max(Math.abs(rMax[0] - min), Math.abs(rMin[0] - max));
            for (int i = 1; i <= n; i++) {
                min = Math.min(min, prefixSum[i]);
                max = Math.max(max, prefixSum[i]);
                res = Math.max(res, Math.abs(rMax[i] - min));
                res = Math.max(res, Math.abs(rMin[i] - max));
            }
            return res;
        }
    }
}
