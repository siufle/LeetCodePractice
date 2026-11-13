/*洛谷3374. 【模板】树状数组1
https://www.luogu.com.cn/problem/P3374

题目描述
如题，已知一个数列，你需要进行下面两种操作：
将某一个数加上 x；
求出某区间每一个数的和。

输入格式
第一行包含两个正整数 n,m，分别表示该数列数字的个数和操作的总个数。
第二行包含 n 个用空格分隔的整数，其中第 i 个数字表示数列第 i 项的初始值。
接下来 m 行每行包含 3 个整数，表示一个操作，具体如下：
1 x k 含义：将第 x 个数加上 k；
2 x y 含义：输出区间 [x,y] 内每个数的和。

输出格式
输出包含若干行整数，即为所有操作 2 的结果。

数据范围
对于 30% 的数据，1≤n≤8，1≤m≤10；
对于 70% 的数据，1≤n,m≤10^4；
对于 100% 的数据，1≤n,m≤5×10^5 ，1≤x≤y≤n，−2^31≤k<2^31
数据保证对于任意时刻，a 的任意子区间（包括长度为 1 和 n 的子区间）和均在 [−2^31,2^31) 范围内。
*/

package dynamicProgram;

import java.io.BufferedInputStream;
import java.io.IOException;

public class LG3374 {
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
        return x & (-x);
    }

    private static void add(long[] t, int i, int x) {
        for (int j = i; j < t.length; j += lowbit(j)) {
            t[j] += x;
        }
    }

    private static long query(long[] t, int x) {
        long ans = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            ans += t[i];
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n + 1];
        long[] t = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            a[i] = x;
            add(t, i, x);
        }
        for (int i = 1; i <=m; i++) {
            int op = sc.nextInt();
            if (op == 1) {
                int x = sc.nextInt();
                int k = sc.nextInt();
                a[x] += k;
                add(t, x, k);
            } else {
                int x = sc.nextInt();
                int y = sc.nextInt();
                System.out.println(query(t, y) - query(t, x - 1));
            }
        }
    }
}

/*
输入
5 5
1 5 4 2 3
1 1 3
2 2 5
1 3 -1
1 4 2
2 1 4

输出
14
16
*/