/*3128. 直角三角形
给你一个二维 boolean 矩阵 grid 。
如果 grid 的 3 个元素的集合中，一个元素与另一个元素在 同一行，并且与第三个元素在 同一列，则该集合是一个 直角三角形。3 个元素 不必 彼此相邻。
请你返回使用 grid 中的 3 个元素可以构建的 直角三角形 数目，且满足 3 个元素值 都 为 1 。

示例 1：
输入：grid = [[0,1,0],[0,1,1],[0,1,0]]
输出：2
解释：
有 2 个值为 1 的直角三角形。注意蓝色的那个 没有 组成直角三角形，因为 3 个元素在同一列。

示例 2：
输入：grid = [[1,0,0,0],[0,1,0,1],[1,0,0,0]]
输出：0
解释：
没有值为 1 的直角三角形。注意蓝色的那个 没有 组成直角三角形。

示例 3：
输入：grid = [[1,0,1],[1,0,0],[1,0,0]]
输出：2
解释：
有两个值为 1 的直角三角形。

提示：
1 <= grid.length <= 1000
1 <= grid[i].length <= 1000
0 <= grid[i][j] <= 1
*/

package baseDataStruct.enumeration;

import java.util.Arrays;

public class NO3128 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = new int[][]{{1,0,1}, {1,0,0}, {1,0,0}};
        System.out.println(solution.numberOfRightTriangles(grid));
    }
    static class Solution {
        public long numberOfRightTriangles(int[][] grid) {
            int n = grid[0].length;
            int[] colSum = new int[n];
            Arrays.fill(colSum, -1);
            for (int[] row : grid) {
                for (int j = 0; j < n; j++) {
                    colSum[j] += row[j];
                }
            }
            long ans = 0;
            for(int[] row:grid){
                int rowSum = -1;
                for (int x : row) {
                    rowSum += x;
                }
                for (int j = 0; j < row.length; j++) {
                    if (row[j] == 1) {
                        ans += (long) rowSum * colSum[j];
                    }
                }
            }
            return ans;
        }
    }
}
