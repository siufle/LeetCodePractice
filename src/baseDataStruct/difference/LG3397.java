/**
 * 洛谷3397. 地毯
 */

package baseDataStruct.difference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LG3397 {
    static int[][] a = new int[1005][1005];//原数组
    static int[][] diff = new int[1005][1005];//差分数组

    public static void main(String[] args) throws IOException {
        // 输入正方形的边长
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        // 输入地毯的数量
        int m = Integer.parseInt(st.nextToken());
        // 输入地毯的位置 左上角的右下角坐标点
        int lx,ly,rx,ry;
        for (int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            lx = Integer.parseInt(st.nextToken());
            ly = Integer.parseInt(st.nextToken());
            rx = Integer.parseInt(st.nextToken());
            ry = Integer.parseInt(st.nextToken());
            //更新差分数组
            diff[lx][ly]++;
            diff[lx][ry + 1]--;
            diff[rx + 1][ly]--;
            diff[rx + 1][ry + 1]++;
        }
        // 更新原数组
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                a[i][j] = a[i - 1][j] + a[i][j - 1] - a[i - 1][j - 1] + diff[i][j];
                sb.append(a[i][j]).append(" ");
            }
            sb.append("\n");
        }
        // 输出原数组
        System.out.print(sb);
    }
}

/*
输入
5 3
2 2 3 3
3 3 5 5
1 2 1 4
输出
0 1 1 1 0
0 1 1 0 0
0 1 2 1 1
0 0 1 1 1
0 0 1 1 1
*/