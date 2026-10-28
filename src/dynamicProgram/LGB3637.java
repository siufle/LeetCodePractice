/*洛谷B3637. 最长上升子序列
https://www.luogu.com.cn/problem/B3637

题目描述
给出一个由 n(n≤5000) 个不超过 10^6的正整数组成的序列。请输出这个序列的最长上升子序列的长度。
最长上升子序列是指，从原序列中按顺序尽可能多取出一些数字排在一起，这些数字是逐渐增大的。

输入格式
第一行，一个整数 n，表示序列长度。
第二行有 n 个整数，表示这个序列。

输出格式
一个整数表示答案。

数据范围
n≤5000

*/

package dynamicProgram;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

public class LGB3637 {
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
        int[] nums = new int[n];
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i])  dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);
    }
}

/*
输入
6
1 2 4 1 3 4

输出
4
*/