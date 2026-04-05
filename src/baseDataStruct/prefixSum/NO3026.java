/*3026. 最大好子数组和
给你一个长度为 n 的数组 nums 和一个 正 整数 k 。
如果 nums 的一个子数组中，第一个元素和最后一个元素 差的绝对值恰好 为 k ，我们称这个子数组为 好 的。
换句话说，如果子数组 nums[i..j] 满足 |nums[i] - nums[j]| == k ，那么它是一个好子数组。
请你返回 nums 中 好 子数组的 最大 和，如果没有好子数组，返回 0 。

示例 1：
输入：nums = [1,2,3,4,5,6], k = 1
输出：11
解释：好子数组中第一个元素和最后一个元素的差的绝对值必须为 1 。
好子数组有 [1,2] ，[2,3] ，[3,4] ，[4,5] 和 [5,6] 。最大子数组和为 11 ，对应的子数组为 [5,6] 。

示例 2：
输入：nums = [-1,3,2,4,5], k = 3
输出：11
解释：好子数组中第一个元素和最后一个元素的差的绝对值必须为 3 。
好子数组有 [-1,3,2] 和 [2,4,5] 。最大子数组和为 11 ，对应的子数组为 [2,4,5] 。

示例 3：
输入：nums = [-1,-2,-3,-4], k = 2
输出：-6
解释：好子数组中第一个元素和最后一个元素的差的绝对值必须为 2 。
好子数组有 [-1,-2,-3] 和 [-2,-3,-4] 。最大子数组和为 -6 ，对应的子数组为 [-1,-2,-3] 。

提示：
2 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
1 <= k <= 10^9
*/

package baseDataStruct.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class NO3026 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumSubarraySum(new int[]{-1,-2,-3,-4}, 2));
    }
    static class Solution {
        public long maximumSubarraySum(int[] nums, int k) {
            long prefixSum = 0;
            long sum = Long.MIN_VALUE;
            Map<Integer, Long> map = new HashMap<>();

            for(int num : nums){
                Long temp = map.get(num - k);
                if(temp != null){
                    sum = Math.max(sum, prefixSum + num - temp);
                }

                temp = map.get(num + k);
                if(temp != null){
                    sum = Math.max(sum, prefixSum + num - temp);
                }

                map.merge(num, prefixSum, Math::min);
                prefixSum += num;
            }
            return sum == Long.MIN_VALUE ? 0 : sum;
        }
    }
}
