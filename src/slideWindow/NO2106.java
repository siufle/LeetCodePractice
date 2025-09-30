package slideWindow;

/*2106.摘水果
在一个无限的 x 坐标轴上，有许多水果分布在其中某些位置。给你一个二维整数数组 fruits ，
其中 fruits[i] = [positioni, amounti] 表示共有 amounti 个水果放置在 positioni 上。
fruits 已经按 positioni 升序排列 ，每个 positioni 互不相同 。
另给你两个整数 startPos 和 k 。最初，你位于 startPos 。
从任何位置，你可以选择 向左或者向右 走。在 x 轴上每移动 一个单位 ，就记作 一步 。你总共可以走 最多 k 步。
你每达到一个位置，都会摘掉全部的水果，水果也将从该位置消失（不会再生）。
返回你可以摘到水果的 最大总数 。

示例 1：
输入：fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
输出：9
解释：
最佳路线为：
- 向右移动到位置 6 ，摘到 3 个水果
- 向右移动到位置 8 ，摘到 6 个水果
移动 3 步，共摘到 3 + 6 = 9 个水果

示例 2：
输入：fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
输出：14
解释：
可以移动最多 k = 4 步，所以无法到达位置 0 和位置 10 。
最佳路线为：
- 在初始位置 5 ，摘到 7 个水果
- 向左移动到位置 4 ，摘到 1 个水果
- 向右移动到位置 6 ，摘到 2 个水果
- 向右移动到位置 7 ，摘到 4 个水果
移动 1 + 3 = 4 步，共摘到 7 + 1 + 2 + 4 = 14 个水果

示例 3：
输入：fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
输出：0
解释：
最多可以移动 k = 2 步，无法到达任一有水果的地方*/


public class NO2106 {
    public static void main(String[] args) {
        int[][] fruits = {{2,8},{6,3},{8,6}};
        int startPos = 5;
        int k = 4;
        Solution solution = new Solution();
        System.out.println(solution.maxTotalFruits(fruits, startPos, k));
    }
    static class Solution {
        public int maxTotalFruits(int[][] fruits, int startPos, int k) {
            int left = lowerBound(fruits, startPos - k);
            int right = left;
            int s = 0;
            while(right < fruits.length && fruits[right][0] <= startPos){
                s += fruits[right][1];
                right++;
            }
            int ans = s;
            for(; right < fruits.length && fruits[right][0] <= startPos + k; right++){
                s += fruits[right][1];
                while(fruits[right][0] * 2 - fruits[left][0] - startPos > k &&
                        fruits[right][0] - fruits[left][0] * 2 + startPos > k)
                {
                    s -= fruits[left][1];
                    left++;
                }
                ans = Math.max(ans, s);
            }
            return ans;
        }
    }
    private static int lowerBound(int[][] fruits, int target) {
        int left = -1;
        int right = fruits.length;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (fruits[mid][0] < target) {
                left = mid;
            }else{
                right = mid;
            }
        }
        return right;
    }
}
