/*3755. 最长平衡异或子数组的长度
给你一个整数数组 nums，返回同时满足以下两个条件的 最长子数组的长度 ：
子数组的按位异或（XOR）为 0。
子数组包含的 偶数 和 奇数 数量相等。
如果不存在这样的子数组，则返回 0。
子数组 是数组中的一个连续、非空 元素序列。

示例 1：
输入： nums = [3,1,3,2,0]
输出： 4
解释：
子数组 [1, 3, 2, 0] 的按位异或为 1 XOR 3 XOR 2 XOR 0 = 0，且包含 2 个偶数和 2 个奇数。

示例 2：
输入： nums = [3,2,8,5,4,14,9,15]
输出： 8
解释：
整个数组的按位异或为 0，且包含 4 个偶数和 4 个奇数。

示例 3：
输入： nums = [0]
输出： 0
解释：
没有非空子数组同时满足两个条件。

提示：
1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9
*/

package baseDataStruct.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class NO3755 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxBalancedSubarray(new int[]{0}));
    }
    static class Solution {
        public int maxBalancedSubarray(int[] nums) {
            int n = nums.length;
            int xor = 0;
            int ans = 0;
            int diff = n;
            Map<Long, Integer> map = new HashMap<>(n + 1,1);
            map.put((long)xor << 20 | diff, -1);
            for(int i = 0; i < n; i++){
                xor ^= nums[i];
                diff += nums[i] % 2 == 0 ? 1 : -1;
                long key = (long)xor << 20 | diff;
                Integer val = map.get(key);
                if(val != null){
                    ans = Math.max(ans, i - val);
                }else{
                    map.put(key, i);
                }
            }
            return ans;
        }
    }
}
