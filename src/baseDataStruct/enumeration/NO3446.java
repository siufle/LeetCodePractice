/*3446. 按对角线进行矩阵排列
给你一个大小为 n x n 的整数方阵 grid。返回一个经过如下调整的矩阵：
左下角三角形（包括中间对角线）的对角线按 非递增顺序 排序。
右上角三角形 的对角线按 非递减顺序 排序。

示例 1：
输入： grid = [[1,7,3],[9,8,2],[4,5,6]]
输出： [[8,2,3],[9,6,7],[4,5,1]]
解释：
标有黑色箭头的对角线（左下角三角形）应按非递增顺序排序：
[1, 8, 6] 变为 [8, 6, 1]。
[9, 5] 和 [4] 保持不变。
标有蓝色箭头的对角线（右上角三角形）应按非递减顺序排序：
[7, 2] 变为 [2, 7]。
[3] 保持不变。

示例 2：
输入： grid = [[0,1],[1,2]]
输出： [[2,1],[1,0]]
解释：
标有黑色箭头的对角线必须按非递增顺序排序，因此 [0, 2] 变为 [2, 0]。其他对角线已经符合要求。

示例 3：
输入： grid = [[1]]
输出： [[1]]


提示：
grid.length == grid[i].length == n
1 <= n <= 10
-105 <= grid[i][j] <= 10^5
*/

package baseDataStruct.enumeration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NO3446 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {{1,7,3},{9,8,2},{4,5,6}};
        grid = solution.sortMatrix(grid);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
    static class Solution {
        public int[][] sortMatrix(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            for(int k = 1; k < m + n; k++){
                int minJ = Math.max(0, n - k);
                int maxJ = Math.min(m + n - 1 - k, n - 1);
                List<Integer> list = new ArrayList<>();
                for(int j = minJ; j <= maxJ; j++){
                    list.add(grid[k + j - n][j]);
                }
                list.sort(minJ > 0 ? null : Comparator.reverseOrder());
                for(int j = minJ; j <= maxJ; j++){
                    grid[k + j - n][j] = list.get(j - minJ);
                }
            }
            return grid;
        }
    }
}
