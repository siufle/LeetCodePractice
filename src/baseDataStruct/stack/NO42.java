/*42. 接雨水(单调栈)
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

package baseDataStruct.stack;

public class NO42 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.trap(new int[]{4,2,0,3,2,5}));
    }
    static class Solution {
        public int trap(int[] height) {
            int n = height.length;
            if (n == 0) return 0;
            int[] stack = new int[n];
            int top = 0;
            int ans = 0;
            for (int i = 0; i < n; i++) {
                while(top > 0 && height[i] > height[stack[top - 1]]){
                    int s = height[stack[top - 1]];
                    top--;
                    if(top > 0){
                        int h = Math.min(height[i], height[stack[top - 1]]) - s;
                        int w = i - stack[top - 1] - 1;
                        ans += h * w;
                    }
                }
                stack[top++] = i;
            }
            return ans;
        }
    }
}
