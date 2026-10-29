/*洛谷2734. 游戏 A Game
https://www.luogu.com.cn/problem/P2734

题目描述
有如下一个双人游戏：N 个正整数的序列放在一个游戏平台上，
由先手开始，先手和后手轮流从序列的任意一端取一个数，取数后从序列中去掉，并累加到取者的得分中，
当数取尽时，游戏结束。以最终得分高者为胜。

编一个执行最优策略的程序计算最终得分。
最优策略就是使玩家在与最好的对手对弈时，能得到的在当前情况下最大的可能的总分的策略。
你的程序要始终为两位玩家执行最优策略。

输入格式
第一行：正整数 N，表示序列中正整数的个数。
第二行至末尾：用空格分隔的 N 个正整数（大小为 1∼200）。

输出格式
只有一行，用空格分隔的两个整数：依次为先手和后手最终的得分。

数据范围
(2≤N≤100)
*/

package dynamicProgram;

import java.io.BufferedInputStream;
import java.io.IOException;

public class LG2734 {
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
        int[] nums = new int[n + 1];
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            nums[i] = sc.nextInt();
            preSum[i] = preSum[i - 1] + nums[i];
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++)
            dp[i][i] = nums[i];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                dp[i][j] = Math.max(preSum[j] - preSum[i - 1] - dp[i + 1][j], preSum[j] - preSum[i - 1] - dp[i][j - 1]);
            }
        }
        System.out.println(dp[1][n] + " " + (preSum[n] - dp[1][n]));
    }
}

/*
输入
6
4 7 2 9 5 2

输出
18 11
*/