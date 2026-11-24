/*41. 缺失的第一个正整数
https://leetcode.cn/problems/first-missing-positive/description/?envType=study-plan-v2&envId=top-100-liked

给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。

示例 1：
输入：
int[] nums = new int[]{1, 2, 0};
输出：3
解释：范围 [1,2] 中的数字都在数组中。

示例 2：
输入：
int[] nums = new int[]{3, 4, -1, 1};
输出：2
解释：1 在数组中，但 2 没有。

示例 3：
输入：
int[] nums = new int[]{7, 8, 9, 11, 12};
输出：1
解释：最小的正数 1 没有出现。

提示：
1 <= nums.length <= 10^5
-2^31 <= nums[i] <= 2^31 - 1
*/

package baseDataStruct.array;

public class NO41 {
    public static void main(String[] args) {
        int[] nums = new int[]{7, 8, 9, 11, 12};
        Solution solution = new Solution();
        int result = solution.firstMissingPositive(nums);
        System.out.println(result);
    }
    static class Solution {
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= 0) {
                    nums[i] = n + 1;
                }
            }
            for (int i = 0; i < n; i++) {
                int num = nums[i];
                if (Math.abs(num) <= n) {
                    nums[Math.abs(num) - 1] = -Math.abs(nums[Math.abs(num) - 1]);
                }
            }
            for (int i = 0; i < n; i++) {
                if (nums[i] > 0) return i + 1;
            }
            return n + 1;
        }
    }
}
