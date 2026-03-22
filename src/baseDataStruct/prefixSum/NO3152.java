/*3152. 特殊数组Ⅱ
如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 特殊数组 。
你有一个整数数组 nums 和一个二维整数矩阵 queries，
对于 queries[i] = [fromi, toi]，请你帮助你检查 子数组 nums[fromi..toi] 是不是一个 特殊数组 。
返回布尔数组 answer，如果 nums[fromi..toi] 是特殊数组，则 answer[i] 为 true ，否则，answer[i] 为 false 。

示例 1：
输入：nums = [3,4,1,2,6], queries = [[0,4]]
输出：[false]
解释：
子数组是 [3,4,1,2,6]。2 和 6 都是偶数。

示例 2：
输入：nums = [4,3,1,6], queries = [[0,2],[2,3]]
输出：[false,true]
解释：
子数组是 [4,3,1]。3 和 1 都是奇数。因此这个查询的答案是 false。
子数组是 [1,6]。只有一对：(1,6)，且包含了奇偶性不同的数字。因此这个查询的答案是 true。

提示：
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
1 <= queries.length <= 10^5
queries[i].length == 2
0 <= queries[i][0] <= queries[i][1] <= nums.length - 1
*/

package baseDataStruct.prefixSum;

import java.util.Arrays;

public class NO3152 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3,4,1,2,6};
        int[][] queries = {{0,4}};
        System.out.println(Arrays.toString(solution.isArraySpecial(nums, queries)));
    }
    static class Solution {
        public boolean[] isArraySpecial(int[] nums, int[][] queries) {
            boolean[] ans = new boolean[queries.length];
            int[] temp = new int[nums.length - 1];
            for(int i = 0; i < nums.length - 1; i++) {
                if(nums[i] % 2 != nums[i + 1] % 2) {
                    temp[i] = 1;
                }else{
                    temp[i] = 0;
                }
            }
            int[] pre = new int[nums.length];
            pre[0] = 0;
            for(int i = 1; i < nums.length; i++) {
                pre[i] = pre[i - 1] + temp[i - 1];
            }
            for(int i = 0; i < queries.length; i++) {
                int fromi = queries[i][0];
                int toi = queries[i][1];
                if(pre[toi] - pre[fromi] == toi - fromi) {
                    ans[i] = true;
                }else {
                    ans[i] = false;
                }
            }
            return ans;
        }
    }
}
