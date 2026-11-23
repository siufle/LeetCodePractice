/*189. 轮转数组
https://leetcode.cn/problems/rotate-array/description/?envType=study-plan-v2&envId=top-100-liked

给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。

示例 1:
输入:
int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
int k = 3;
输出: [5,6,7,1,2,3,4]
解释:
向右轮转 1 步: [7,1,2,3,4,5,6]
向右轮转 2 步: [6,7,1,2,3,4,5]
向右轮转 3 步: [5,6,7,1,2,3,4]

示例 2:
输入：
int[] nums = new int[]{-1, -100, 3, 99};
int k = 2;
输出：[3,99,-1,-100]
解释:
向右轮转 1 步: [99,-1,-100,3]
向右轮转 2 步: [3,99,-1,-100]

提示：
1 <= nums.length <= 10^5
-2^31 <= nums[i] <= 2^31 - 1
0 <= k <= 10^5
*/

package baseDataStruct.array;

public class NO189 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{-1, -100, 3, 99};
        int k = 2;
        solution.rotate(nums, k);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
    static class Solution {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            reverse(nums, 0, n - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, n - 1);
        }
        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }
}
