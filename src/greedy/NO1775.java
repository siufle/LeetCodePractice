/*1775. 通过操作使数组的和相等
给你两个长度可能不等的整数数组 nums1 和 nums2 。两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。
每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。
请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。

示例 1：
输入：nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
输出：3
解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
- 将 nums2[0] 变为 6 。 nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2] 。
- 将 nums1[5] 变为 1 。 nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2] 。
- 将 nums1[2] 变为 2 。 nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2] 。

示例 2：
输入：nums1 = [1,1,1,1,1,1,1], nums2 = [6]
输出：-1
解释：没有办法减少 nums1 的和或者增加 nums2 的和使二者相等。

示例 3：
输入：nums1 = [6,6], nums2 = [1]
输出：3
解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
- 将 nums1[0] 变为 2 。 nums1 = [2,6], nums2 = [1] 。
- 将 nums1[1] 变为 2 。 nums1 = [2,2], nums2 = [1] 。
- 将 nums2[0] 变为 4 。 nums1 = [2,2], nums2 = [4] 。

提示：
1 <= nums1.length, nums2.length <= 10^5
1 <= nums1[i], nums2[i] <= 6
*/

package greedy;

public class NO1775 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {6,6};
        int[] nums2 = {1};
        System.out.println(solution.minOperations(nums1, nums2));
    }
    static class Solution {
        public int minOperations(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            if(m > n * 6 || n > m * 6){
                return -1;
            }
            int[] cnt1 = new int[7], cnt2 = new int[7];
            int sum1 = 0, sum2 = 0;
            for (int num : nums1) {
                cnt1[num]++;
                sum1 += num;
            }
            for (int num : nums2) {
                cnt2[num]++;
                sum2 += num;
            }
            int diff = sum1 - sum2;
            if (diff > 0) {
                return help(cnt2, cnt1, diff);
            }else if (diff == 0) {
                return 0;
            }else{
                return help(cnt1, cnt2, -diff);
            }
        }
        private int help(int[] h1, int[] h2, int diff){
            int res = 0;
            int[] h = new int[7];
            for(int i = 1; i <= 6; i++){
                h[6 - i] += h1[i];
                h[i - 1] += h2[i];
            }
            for(int i = 5; i > 0 && diff > 0; i--){
                int t = Math.min((diff + i - 1) / i, h[i]);
                res += t;
                diff -= t * i;
            }
            return res;
        }
    }
}
