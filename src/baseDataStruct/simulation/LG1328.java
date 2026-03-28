/**
 * 洛谷1328. 生活大爆炸版石头剪刀布
 */

package baseDataStruct.simulation;

import java.util.Scanner;

public class LG1328 {
    public static void main(String[] args) {
        //得分表
        int[][] scores = {
                {0,0,1,1,0},
                {1,0,0,1,0},
                {0,1,0,0,1},
                {0,0,1,0,1},
                {1,1,0,0,0}
        };
        Scanner sc = new Scanner(System.in);
        //比赛进行局数
        int n = sc.nextInt();
        //a出拳周期
        int an = sc.nextInt();
        int[] a = new int[an];
        //b出拳周期
        int bn = sc.nextInt();
        int[] b = new int[bn];
        for(int i = 0; i < an; i++){
            a[i] = sc.nextInt();
        }
        for(int i = 0; i < bn; i++){
            b[i] = sc.nextInt();
        }
        //计算得分
        int aScore = 0;
        int bScore = 0;
        int x = 0;
        int y = 0;
        for(int i = 0; i < n; i++){
            aScore += scores[a[x]][b[y]];
            bScore += scores[b[y]][a[x]];
            x = (x + 1) % an;
            y = (y + 1) % bn;
        }
        System.out.println(aScore + " " + bScore);
    }
}

/*
输入
10 5 6
0 1 2 3 4
0 3 4 2 1 0
输出
6 2

输入
9 5 5
0 1 2 3 4
1 0 3 2 4
输出
4 4
*/
