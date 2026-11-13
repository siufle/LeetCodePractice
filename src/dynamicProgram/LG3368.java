/*洛谷3368. 【模板】树状数组2
https://www.luogu.com.cn/problem/P3368

题目描述
如题，已知一个数列，你需要进行下面两种操作：
1.将某区间每一个数加上 x；
2.求出某一个数的值。

输入格式
第一行包含两个整数 N、M，分别表示该数列数字的个数和操作的总个数。
第二行包含 N 个用空格分隔的整数，其中第 i 个数字表示数列第 i 项的初始值。
接下来 M 行每行包含 2 或 4 个整数，表示一个操作，具体如下：
操作 1： 格式：1 x y k 含义：将区间 [x,y] 内每个数加上 k；
操作 2： 格式：2 x 含义：输出第 x 个数的值。

输出格式
输出包含若干行整数，即为所有操作 2 的结果。

数据范围
对于 30% 的数据：N≤8，M≤10
对于 70% 的数据：N≤10^4，M≤10^4
对于 100% 的数据：1≤N,M≤5×10^5，1≤x,y≤n，保证任意时刻序列中任意元素的绝对值都不大于 2^30
*/

package dynamicProgram;

import java.io.BufferedInputStream;
import java.io.IOException;

public class LG3368 {
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

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long value = 0;
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

    private static int lowbit(int x) {
        return x & -x;
    }

    private static void add(long[] t, int i, long x) {
        for (int j = i; j < t.length; j += lowbit(j)) {
            t[j] += x;
        }
    }

    private static long query(long[] t, int i) {
        long ans = 0;
        for (int j = i; j > 0; j -= lowbit(j)) {
            ans += t[j];
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n + 1];
        int[] d = new int[n + 1];
        long[] t = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        d[1] = a[1];
        for (int i = 2; i <= n; i++) {
            d[i] = a[i] - a[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            add(t, i, d[i]);
        }
        for (int i = 1; i <= m; i++) {
            int op = sc.nextInt();
            if (op == 1) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int k = sc.nextInt();
                add(t, x, k);
                add(t, y + 1, -k);
            } else {
                int x = sc.nextInt();
                System.out.println(query(t, x));
            }
        }
    }
}

/*
输入
5 5
1 5 4 2 3
1 2 4 2
2 3
1 1 5 -1
1 3 5 7
2 4

输出
6
10
*/