/*2815. 数位上最大的数字相等的最大和
给你一个下标从 0 开始的整数数组 nums 。请你从 nums 中找出和 最大 的一对数，且这两个数数位上最大的数字相等。
返回最大和，如果不存在满足题意的数字对，返回 -1 。

示例 1：
输入：nums = [51,71,17,24,42]
输出：88
解释：
i = 1 和 j = 2 ，nums[i] 和 nums[j] 数位上最大的数字相等，且这一对的总和 71 + 17 = 88 。
i = 3 和 j = 4 ，nums[i] 和 nums[j] 数位上最大的数字相等，且这一对的总和 24 + 42 = 66 。
可以证明不存在其他数对满足数位上最大的数字相等，所以答案是 88 。

示例 2：
输入：nums = [1,2,3,4]
输出：-1
解释：不存在数对满足数位上最大的数字相等。

提示：
2 <= nums.length <= 100
1 <= nums[i] <= 10^4
*/

package baseDataStruct.enumeration;

public class NO2815 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3,4};
        int res = solution.maxSum(nums);
        System.out.println(res);
    }
    static class Solution {
        public int maxSum(int[] nums) {
            int[] mx = new int[10];
            int res = -1;
            for(int num : nums) {
                int d = -1;
                int temp = num;
                while(temp > 0) {
                    d = Math.max(d, temp%10);
                    temp /= 10;
                }
                if(mx[d] > 0) {
                    res = Math.max(res, mx[d] + num);
                }
                mx[d] = Math.max(mx[d], num);
            }
            return res;
        }
    }
}
