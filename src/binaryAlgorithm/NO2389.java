/*2389.和有限的最长子序列
给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。
返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。
子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。

示例 1：
输入：nums = [4,5,2,1], queries = [3,10,21]
输出：[2,3,4]
解释：queries 对应的 answer 如下：
- 子序列 [2,1] 的和小于或等于 3 。可以证明满足题目要求的子序列的最大长度是 2 ，所以 answer[0] = 2 。
- 子序列 [4,5,1] 的和小于或等于 10 。可以证明满足题目要求的子序列的最大长度是 3 ，所以 answer[1] = 3 。
- 子序列 [4,5,2,1] 的和小于或等于 21 。可以证明满足题目要求的子序列的最大长度是 4 ，所以 answer[2] = 4 。

示例 2：
输入：nums = [2,3,4,5], queries = [1]
输出：[0]
解释：空子序列是唯一一个满足元素和小于或等于 1 的子序列，所以 answer[0] = 0 。

提示：
n == nums.length
m == queries.length
1 <= n, m <= 1000
1 <= nums[i], queries[i] <= 10^6
*/

package binaryAlgorithm;

import java.util.Arrays;

public class NO2389 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {4,5,2,1};
        int[] queries = {3,10,21};
        int[] res = solution.answerQueries(nums, queries);
        System.out.println(Arrays.toString(res));
    }
    /*暴力解法
    static class Solution {
        public int[] answerQueries(int[] nums, int[] queries) {
            Arrays.sort(nums);
            int[] ans = new int[queries.length];
            for (int i = 0; i < queries.length; i++) {
                int sum = 0;
                for(int j = 0; j < nums.length; j++){
                    sum += nums[j];
                    if(sum > queries[i]){
                        ans[i] = j;
                        break;
                    }
                    if(j == nums.length - 1){
                        ans[i] = nums.length;
                    }
                }
            }
            return ans;
        }
    }
    */
    static class Solution {
        public int[] answerQueries(int[] nums, int[] queries) {
            Arrays.sort(nums);
            int[] ans = new int[queries.length];
            for (int i = 1; i < nums.length; i++) {
                nums[i] = nums[i-1] + nums[i];
            }
            for (int i = 0; i < queries.length; i++) {
                int index = upperBound(nums,queries[i]);
                ans[i] = index;
            }
            return ans;
        }
        public int upperBound(int[] nums, int target) {
            int left = -1;
            int right = nums.length;
            while (left + 1 < right) {
                int mid = (left + right) / 2;
                if(nums[mid] > target){
                    right = mid;
                }else{
                    left = mid;
                }
            }
            return right;
        }
    }
}
