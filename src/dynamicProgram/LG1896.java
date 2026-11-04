/*洛谷1896. 互不侵犯
https://www.luogu.com.cn/problem/P1896

题目描述
在 N×N 的棋盘里面放 K 个国王，使他们互不攻击，共有多少种摆放方案。
国王能攻击到它上下左右，以及左上左下右上右下八个方向上附近的各一个格子，共 8 个格子。

输入格式
只有一行，包含两个数 N,K。

输出格式
所得的方案数。

数据范围
对于全部数据，1≤N≤9，0≤K≤N×N。

*/

package dynamicProgram;

import java.util.Scanner;

public class LG1896 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[] ok = new long[1 << n];
        long[] cnt = new long[1 << n];
        int num = 0;
        long[][][] dp = new long[n + 1][k + 1][1 << n];
        for (int s = 0; s < 1 << n; s++) {
            if ((s & (s << 1)) == 0 && (s & (s >> 1)) == 0) {
                num++;
                ok[num] = s;
                int s1 = s;
                while (s1 > 0) {
                    if (s1 % 2 == 1) cnt[s]++;
                    s1 /= 2;
                }
            }
        }
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) { //枚举层数
            for (int l = 1; l <= num; l++) { //枚举第i层国王可以放置的状态
                int s = (int) ok[l];
                for (int r = 1; r <= num; r++) { //枚举第i - 1层国王可以放置的状态
                    int s1 = (int) ok[r];
                    if (((s & s1) == 0) && (((s<<1) & s1) == 0) && (((s>>1) & s1) == 0)) {
                        for (int j = 0; j <= k; j++) { //枚举国王数量
                            if (j >= cnt[s]) dp[i][j][s] += dp[i-1][(int)(j-cnt[s])][s1];
                        }
                    }
                }
            }

        }
        long ans = 0;
        for (int i = 1; i <= num; i++) {
            int s = (int) ok[i];
            ans += dp[n][k][s];
        }
        System.out.println(ans);
    }
}

/*
输入
3 2

输出
16
*/