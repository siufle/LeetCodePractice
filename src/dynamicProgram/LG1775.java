/*洛谷1775. 合并石子(弱化版)
https://www.luogu.com.cn/problem/P1775

题目描述
设有 N 堆石子排成一排，其编号为 1,2,3,⋯,N。每堆石子有一定的质量 mi。
现在要将这 N 堆石子合并成为一堆。每次只能合并相邻的两堆，
合并的代价为这两堆石子的质量之和，合并后与这两堆石子相邻的石子将和新堆相邻。
合并时由于选择的顺序不同，合并的总代价也不相同。试找出一种合理的方法，使总的代价最小，并输出最小代价。

输入格式
第一行，一个整数 N。
第二行，N 个整数 mi

输出格式
输出文件仅一个整数，也就是最小代价。

数据范围
(N≤300)
(mi≤1000)
*/

package dynamicProgram;

import java.io.BufferedInputStream;
import java.io.IOException;

public class LG1775 {
    private static class FastScanner {
        private final BufferedInputStream input = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int pointer;
        private int length;

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int value = 0;
            while (c > ' ') {
                value = value * 10 + c - '0';
                c = read();
            }
            return sign * value;
        }

        private int read() throws IOException {
            if (pointer >= length) {
                length = input.read(buffer);
                pointer = 0;
                if (length == -1) {
                    return -1;
                }
            }
            return buffer[pointer++];
        }
    }
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int[] m = new int[n + 1];
        int[] preSum = new int[n + 1];
        int[][] dp = new int[n + 1][n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            m[i] = sc.nextInt();
            preSum[i] = preSum[i - 1] + m[i];
        }
        for (int i = 1; i <= n; i++)
            dp[i][i] = 0;
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + preSum[j] - preSum[i - 1]);
                }
            }
        }
        System.out.println(dp[1][n]);
    }
}

/*
输入
4
2 5 3 1

输出
22
*/