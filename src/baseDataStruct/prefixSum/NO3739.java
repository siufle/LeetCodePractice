/*3739. 统计主要元素子数组数目Ⅱ
给你一个整数数组 nums 和一个整数 target。
返回数组 nums 中满足 target 是 主要元素 的 子数组 的数目。
一个子数组的 主要元素 是指该元素在该子数组中出现的次数 严格大于 其长度的 一半 。
子数组 是数组中的一段连续且 非空 的元素序列。

示例 1:
输入: nums = [1,2,2,3], target = 2
输出: 5
解释:
以 target = 2 为主要元素的子数组有:
nums[1..1] = [2]
nums[2..2] = [2]
nums[1..2] = [2,2]
nums[0..2] = [1,2,2]
nums[1..3] = [2,2,3]
因此共有 5 个这样的子数组。

示例 2:
输入: nums = [1,1,1,1], target = 1
输出: 10
解释:
所有 10 个子数组都以 1 为主要元素。

示例 3:
输入: nums = [1,2,3], target = 4
输出: 0
解释:
target = 4 完全没有出现在 nums 中。因此，不可能有任何以 4 为主要元素的子数组。故答案为 0。

提示:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= target <= 10^9
*/

package baseDataStruct.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class NO3739 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countMajoritySubarrays(new int[]{1,1,1,1}, 1));
    }
    static class Solution {
        public long countMajoritySubarrays(int[] nums, int target) {
            long ans = 0;
            Map<Integer, Integer> map = new HashMap<>();
            int sum = 0;
            int count = 0;
            map.put(0, 1);
            for(int num : nums) {
                if(num == target){
                    count += map.getOrDefault(sum, 0);
                    sum++;
                }else{
                    sum--;
                    count -= map.getOrDefault(sum, 0);
                }
                ans += count;
                map.merge(sum,1,Integer::sum);
            }
            return ans;
        }
    }
}
