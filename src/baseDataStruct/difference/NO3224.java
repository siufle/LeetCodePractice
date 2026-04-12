/*3224. 使差值相等的最少数组改动次数
给你一个长度为 n 的整数数组 nums ，n 是 偶数 ，同时给你一个整数 k 。
你可以对数组进行一些操作。每次操作中，你可以将数组中 任一 元素替换为 0 到 k 之间的 任一 整数。
执行完所有操作以后，你需要确保最后得到的数组满足以下条件：
存在一个整数 X ，满足对于所有的 (0 <= i < n) 都有 abs(a[i] - a[n - i - 1]) = X 。
请你返回满足以上条件 最少 修改次数。

示例 1：
输入：nums = [1,0,1,2,4,3], k = 4
输出：2
解释：
我们可以执行以下操作：
将 nums[1] 变为 2 ，结果数组为 nums = [1,2,1,2,4,3] 。
将 nums[3] 变为 3 ，结果数组为 nums = [1,2,1,3,4,3] 。
整数 X 为 2 。

示例 2：
输入：nums = [0,1,2,3,3,6,5,4], k = 6
输出：2
解释：
我们可以执行以下操作：
将 nums[3] 变为 0 ，结果数组为 nums = [0,1,2,0,3,6,5,4] 。
将 nums[4] 变为 4 ，结果数组为 nums = [0,1,2,0,4,6,5,4] 。
整数 X 为 4 。

提示：
2 <= n == nums.length <= 10^5
n 是偶数。
0 <= nums[i] <= k <= 10^5
*/

package baseDataStruct.difference;

public class NO3224 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,0,1,2,4,3};
        int k = 4;
        System.out.println(solution.minChanges(nums, k));
    }
    static class Solution {
        public int minChanges(int[] nums, int k) {
            int n = nums.length;
            int[] diff = new int[k + 2];
            for(int i = 0; i < n/2; i++){
                int p = nums[i];
                int q = nums[n-1-i];
                if(p > q){
                    int tmp = p;
                    p = q;
                    q = tmp;
                }
                int x = q - p;
                int mx = Math.max(q,k - p);
                //0到x-1要改变一次
                diff[0]++;
                diff[x]--;
                //x+1到mx要改变一次
                diff[x + 1]++;
                diff[mx + 1]--;
                //mx+1到k要改变两次
                diff[mx + 1] += 2;
            }
            int ans = n;
            int prefix = 0;
            for(int t : diff){
                prefix += t;
                ans = Math.min(ans, prefix);
            }
            return ans;
        }
    }
}
