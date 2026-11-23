/*56. 合并区间
https://leetcode.cn/problems/merge-intervals/description/?envType=study-plan-v2&envId=top-100-liked

以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

示例 1：
输入：
int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

示例 2：
输入：
int[][] intervals = new int[][]{{1, 4}, {4, 5}};
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。

示例 3：
输入：
int[][] intervals = new int[][]{{4, 7}, {1, 4}};
输出：[[1,7]]
解释：区间 [1,4] 和 [4,7] 可被视为重叠区间。

提示：
1 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti <= endi <= 10^4
*/

package baseDataStruct.array;

import java.util.Arrays;

public class NO56 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals = new int[][]{{4, 7}, {1, 4}};
        int[][] result = solution.merge(intervals);
        System.out.println(Arrays.deepToString(result));
    }
    static class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
            int n = intervals.length;
            int[][] res = new int[n][2];
            int index = 0;
            for (int i = 0; i < n; i++) {
                if (i < n - 1 && intervals[i][1] >= intervals[i + 1][0]) {
                    res[index][0] = intervals[i][0];
                    res[index][1] = Math.max(intervals[i][1], intervals[i + 1][1]);
                    i++;
                    while (i < n - 1 && res[index][1] >= intervals[i + 1][0]) {
                        i++;
                        res[index][1] = Math.max(res[index][1], intervals[i][1]);
                    }
                }
                else {
                    res[index][0] = intervals[i][0];
                    res[index][1] = intervals[i][1];
                }
                index++;
            }
            return Arrays.copyOfRange(res, 0, index);
        }
    }
}
