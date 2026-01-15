/*2342. 数位和相等数对的最大和
给你一个下标从 0 开始的数组 nums ，数组中的元素都是 正 整数。
请你选出两个下标 i 和 j（i != j），且 nums[i] 的数位和 与  nums[j] 的数位和相等。
请你找出所有满足条件的下标 i 和 j ，找出并返回 nums[i] + nums[j] 可以得到的 最大值。
如果不存在这样的下标对，返回 -1。

示例 1：
输入：nums = [18,43,36,13,7]
输出：54
解释：满足条件的数对 (i, j) 为：
- (0, 2) ，两个数字的数位和都是 9 ，相加得到 18 + 36 = 54 。
- (1, 4) ，两个数字的数位和都是 7 ，相加得到 43 + 7 = 50 。
所以可以获得的最大和是 54 。

示例 2：
输入：nums = [10,12,19,14]
输出：-1
解释：不存在满足条件的数对，返回 -1 。

提示：
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
*/

package baseDataStruct.enumeration;

import java.util.*;

public class NO2342 {
    public static void main(String[] args) {
        int[] nums = {10,12,19,14};
        Solution solution = new Solution();
        int result = solution.maximumSum(nums);
        System.out.println(result);
    }
    static class Solution {
        public int maximumSum(int[] nums) {
            int res = -1;
            int[] mx = new int[82];
            Arrays.fill(mx, Integer.MIN_VALUE);
            for (int num : nums) {
                int sum = 0;
                int temp = num;
                while (temp > 0){
                    sum += temp % 10;
                    temp /= 10;
                }
                res = Math.max(res,mx[sum] + num);
                mx[sum] = Math.max(mx[sum], num);
            }
            return res;
        }
    }
}
