/*930. 和相同的二元子数组
给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
子数组 是数组的一段连续部分。

示例 1：
输入：nums = [1,0,1,0,1], goal = 2
输出：4
解释：
有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]

示例 2：
输入：nums = [0,0,0,0,0], goal = 0
输出：15

提示：
1 <= nums.length <= 3 * 10^4
nums[i] 不是 0 就是 1
0 <= goal <= nums.length
*/

package baseDataStruct.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class NO930 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSubarraysWithSum(new int[]{0,0,0,0,0},0));
    }
    static class Solution {
        public int numSubarraysWithSum(int[] nums, int goal) {
            Map<Long, Integer> map = new HashMap<>();
            int ans = 0;
            map.put(0L, 1);
            long prefixSum = 0L;
            for (int i = 0; i < nums.length; i++) {
                prefixSum += nums[i];
                ans += map.getOrDefault(prefixSum - goal, 0);
                map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
            }
            return ans;
        }
    }
}
