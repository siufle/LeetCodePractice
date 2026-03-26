/*560. 和为K的子数组
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
子数组是数组中元素的连续非空序列。

示例 1：
输入：nums = [1,1,1], k = 2
输出：2

示例 2：
输入：nums = [1,2,3], k = 3
输出：2

提示：
1 <= nums.length <= 2 * 10^4
-1000 <= nums[i] <= 1000
-10^7 <= k <= 10^7
*/

package baseDataStruct.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class NO560 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subarraySum(new int[]{1,2,3},3));
    }
    static class Solution {
        public int subarraySum(int[] nums, int k) {
            Map<Long, Integer> map = new HashMap<>();
            int ans = 0;
            map.put(0L,1);
            long prefixSum = 0L;
            for (int num : nums) {
                prefixSum += num;
                ans += map.getOrDefault(prefixSum - k, 0);
                map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
            }
            return ans;
        }
    }
}
