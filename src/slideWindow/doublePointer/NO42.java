/*42. 接雨水
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

示例 1：
输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

示例 2：
输入：height = [4,2,0,3,2,5]
输出：9

提示：
n == height.length
1 <= n <= 2 * 10^4
0 <= height[i] <= 10^5
*/

package slideWindow.doublePointer;

public class NO42 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height = {4,2,0,3,2,5};
        System.out.println(solution.trap(height));
    }
    static class Solution {
        public int trap(int[] height) {
            int n = height.length;
            int left = 0;
            int right = n - 1;
            int leftMax = 0;
            int rightMax = 0;
            int ans = 0;
            while (left < right) {
                leftMax = Math.max(leftMax, height[left]);
                rightMax = Math.max(rightMax, height[right]);
                if(height[left] < height[right]) {
                    ans += leftMax - height[left];
                    left++;
                }else{
                    ans += rightMax - height[right];
                    right--;
                }
            }
            return ans;
        }
    }
}
