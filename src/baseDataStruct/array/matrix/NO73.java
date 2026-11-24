/*73. 矩阵置零
https://leetcode.cn/problems/set-matrix-zeroes/description/?envType=study-plan-v2&envId=top-100-liked

给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。

示例 1：
输入：
int[][] matrix = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
输出：[[1,0,1],[0,0,0],[1,0,1]]

示例 2：
输入：
int[][] matrix = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]

提示：
m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-2^31 <= matrix[i][j] <= 2^31 - 1
*/

package baseDataStruct.array.matrix;

import java.util.ArrayList;
import java.util.List;

public class NO73 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        Solution solution = new Solution();
        solution.setZeroes(matrix);
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
    static class Solution {
        public void setZeroes(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            List<int[]> zeroPositions = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        zeroPositions.add(new int[]{i, j});
                    }
                }
            }
            for (int[] zeroPosition : zeroPositions) {
                int row = zeroPosition[0];
                int col = zeroPosition[1];
                for (int j = 0; j < n; j++) {
                    matrix[row][j] = 0;
                }
                for (int i = 0; i < m; i++) {
                    matrix[i][col] = 0;
                }
            }
        }
    }
}
