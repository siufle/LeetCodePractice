/**
 * 洛谷1382. 最大正方形
 */

package baseDataStruct.prefixSum;

import java.util.Scanner;

public class LG1387 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 输入矩阵的行数和列数
        int n = sc.nextInt();
        int m = sc.nextInt();
        // 输入矩阵的元素 从1开始存储
        int[][] matrix = new int[n + 1][m + 1];
        // 初始化矩阵的前缀和
        int[][] prefixSum = new int[n + 1][m + 1];
        //初始化矩阵第0行和第0列的前缀和
        for(int i = 0; i <= n; i++){
            prefixSum[i][0] = 0;
        }
        for(int i = 0; i <= m; i++){
            prefixSum[0][i] = 0;
        }
        // 接收矩阵的元素并计算前缀和
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                matrix[i][j] = sc.nextInt();
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + matrix[i][j];
            }
        }
        int ans = 0;
        //枚举正方形边长
        int l = 1;
        while(l <= Math.min(n,m)){
            //枚举正方形右下角
            for(int x = l; x <= n; x++){
                for(int y = l; y <= m; y++){
                    //计算当前正方形的面积
                    int area = prefixSum[x][y] - prefixSum[x - l][y] - prefixSum[x][y - l] + prefixSum[x - l][y - l];
                    if (area == l * l) {
                        ans = l;
                        break;
                    }
                }
            }
            l++;
        }
        System.out.println(ans);
    }
}


/*
输入
4 4
0 1 1 1
1 1 1 0
0 1 1 0
1 1 0 1
输出
2
*/