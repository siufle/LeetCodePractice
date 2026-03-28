/*59. 螺旋矩阵Ⅱ
给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。

示例 1：
输入：n = 3
输出：[[1,2,3],[8,9,4],[7,6,5]]

示例 2：
输入：n = 1
输出：[[1]]


提示：
1 <= n <= 20
*/

package baseDataStruct.simulation;

public class NO59 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = solution.generateMatrix(3);
        for(int[] ints : matrix) {
            for(int i : ints) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    static class Solution {
        public int[][] generateMatrix(int n) {
            int[][] matrix = new int[n][n];
            int num = 1;
            int t = 0, b = n - 1, l = 0, r = n - 1;
            while(num <= n * n) {
                //先从左到右
                for(int i = l; i <= r; i++){
                    matrix[t][i] = num;
                    num++;
                }
                t++;
                //再从上到下
                for(int i = t; i <= b; i++){
                    matrix[i][r] = num;
                    num++;
                }
                r--;
                //再从右到左
                for(int i = r; i >= l; i--){
                    matrix[b][i] = num;
                    num++;
                }
                b--;
                //再从下到上
                for(int i = b; i >= t; i--){
                    matrix[i][l] = num;
                    num++;
                }
                l++;
            }
            return matrix;
        }
    }
}
