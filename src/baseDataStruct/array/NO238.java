/*238. 除自身以外数组的乘积
https://leetcode.cn/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=top-100-liked

给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除了 nums[i] 之外其余各元素的乘积 。
题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
请 不要使用除法，且在 O(n) 时间复杂度内完成此题。

示例 1:
输入: nums = [1,2,3,4]
输出: [24,12,8,6]

示例 2:
输入:
int[] nums = new int[]{-1, 1, 0, -3, 3};
输出: [0,0,9,0,0]

提示：
2 <= nums.length <= 10^5
-30 <= nums[i] <= 30
输入 保证 数组 answer[i] 在  32 位 整数范围内
*/

package baseDataStruct.array;

public class NO238 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{-1, 1, 0, -3, 3};
        int[] result = solution.productExceptSelf(nums);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
    static class Solution {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] answer = new int[n];
            answer[0] = 1;
            for (int i = 1; i < n; i++) {
                answer[i] = answer[i - 1] * nums[i - 1];
            }
            int right = 1;
            for (int i = n - 1; i >= 0; i--) {
                answer[i] = answer[i] * right;
                right *= nums[i];
            }
            return answer;
        }
    }
}
