/*239. 滑动窗口最大值
https://leetcode.cn/problems/sliding-window-maximum/description/?envType=study-plan-v2&envId=top-100-liked

给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
返回 滑动窗口中的最大值 。

示例 1：
输入：
int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
int k = 3;
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

示例 2：
输入：
int[] nums = new int[]{1};
int k = 1;
输出：[1]

提示：
1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length
*/

package string.substring;

import java.util.Comparator;
import java.util.PriorityQueue;

public class NO239 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{3,1,1,3};
        int k = 3;
        int[] result = solution.maxSlidingWindow(nums, k);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
    static class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[0] != b[0] ? b[0] - a[0] : b[1] - a[1];
                }
            });
            for (int i = 0; i < k; i++) {
                pq.add(new int[]{nums[i], i});
            }
            int[] result = new int[n - k + 1];
            result[0] = pq.peek()[0];
            for (int i = k; i < n; i++) {
                pq.add(new int[]{nums[i], i});
                while (pq.peek()[1] <= i - k) {
                    pq.poll();
                }
                result[i - k + 1] = pq.peek()[0];
            }
            return result;
        }
    }
}
