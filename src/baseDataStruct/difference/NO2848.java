/*2848. 与车相交的点
给你一个下标从 0 开始的二维整数数组 nums 表示汽车停放在数轴上的坐标。
对于任意下标 i，nums[i] = [starti, endi] ，其中 starti 是第 i 辆车的起点，endi 是第 i 辆车的终点。
返回数轴上被车 任意部分 覆盖的整数点的数目。

示例 1：
输入：nums = [[3,6],[1,5],[4,7]]
输出：7
解释：从 1 到 7 的所有点都至少与一辆车相交，因此答案为 7 。

示例 2：
输入：nums = [[1,3],[5,8]]
输出：7
解释：1、2、3、5、6、7、8 共计 7 个点满足至少与一辆车相交，因此答案为 7 。

提示：
1 <= nums.length <= 100
nums[i].length == 2
1 <= starti <= endi <= 100
*/

package baseDataStruct.difference;

import java.util.List;

public class NO2848 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numberOfPoints(List.of(List.of(1,3), List.of(5,8))));
    }
    static class Solution {
        public int numberOfPoints(List<List<Integer>> nums) {
            int[] diff = new int[102];
            for (List<Integer> num : nums) {
                diff[num.get(0)]++;
                diff[num.get(1) + 1]--;
            }
            int ans = 0;
            int[] prefixSum = new int[102];
            for (int i = 1; i < 102; i++) {
                prefixSum[i] = prefixSum[i - 1] + diff[i];
                if (prefixSum[i] > 0) {
                    ans++;
                }
            }
            return ans;
        }
    }
}
