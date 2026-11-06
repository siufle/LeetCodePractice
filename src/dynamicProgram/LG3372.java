/*洛谷3372. 【模板】线段树1
https://www.luogu.com.cn/problem/P3372

题目描述
如题，已知一个数列 {ai}，你需要进行下面两种操作：
1.将某区间每一个数加上 k。
2.求出某区间每一个数的和。

输入格式
第一行包含两个整数 n,m，分别表示该数列数字的个数和操作的总个数。
第二行包含 n 个用空格分隔的整数 ai，其中第 i 个数字表示数列第 i 项的初始值。
接下来 m 行每行包含 3 或 4 个整数，表示一个操作，具体如下：
1 x y k：将区间 [x,y] 内每个数加上 k。
2 x y：输出区间 [x,y] 内每个数的和

输出格式
输出包含若干行整数，即为所有操作 2 的结果。

数据范围
对于 15% 的数据：n≤8，m≤10。
对于 35% 的数据：n≤10^3，m≤10^4
对于 100% 的数据：1≤n,m≤10^5，ai,k 为正数，且任意时刻数列的和不超过 2×10^18
*/

package dynamicProgram;

import java.io.BufferedInputStream;
import java.io.IOException;

public class LG3372 {
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

    private static class Node {
        int l, r;
        long sum, tag;
        Node(int l, int r, long sum, long tag) {
            this.l = l;
            this.r = r;
            this.sum = sum;
            this.tag = tag;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] a = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextLong();
        }
        Node[] tree = new Node[4 * n];
        buildTree(tree, a, 1, n, 1);
        for (int i = 0; i < m; i++) {
            int op = sc.nextInt();
            if (op == 1) {
                int le = sc.nextInt();
                int ri = sc.nextInt();
                long k = sc.nextLong();
                update(tree, le, ri, k, 1);
            } else if (op == 2) {
                int le = sc.nextInt();
                int ri = sc.nextInt();
                System.out.println(query(tree, le, ri, 1));
            }
        }
    }

    private static void buildTree(Node[] tree, long[] a, int l, int r, int i) {
        int mid = (l + r) / 2;
        if (l == r) {
            tree[i] = new Node(l, r, a[l], 0);
        } else {
            buildTree(tree, a, l, mid, 2 * i);
            buildTree(tree, a, mid + 1, r, 2 * i + 1);
            tree[i] = new Node(l, r, tree[2 * i].sum + tree[2 * i + 1].sum, 0);
        }
    }

    private static void update(Node[] tree, int le, int ri, long k, int i) {
        int mid = (tree[i].l + tree[i].r) / 2;
        if (le <= tree[i].l && tree[i].r <= ri) {
            tree[i].sum += (long) (tree[i].r - tree[i].l + 1) * k;
            tree[i].tag += k;
        } else {
            pushDown(tree, i);
            if (le <= mid) update(tree, le, ri, k, 2 * i);
            if (ri > mid) update(tree, le, ri, k, 2 * i + 1);
            tree[i].sum = tree[2 * i].sum + tree[2 * i + 1].sum;
        }
    }

    private static long query(Node[] tree, int le, int ri, int i) {
        int mid = (tree[i].l + tree[i].r) / 2;
        long ans = 0;
        if (le <= tree[i].l && tree[i].r <= ri) {
            return tree[i].sum;
        } else {
            pushDown(tree, i);
            if (le <= mid) ans += query(tree, le, ri, 2 * i);
            if (ri >= mid + 1) ans += query(tree, le, ri, 2 * i + 1);
            return ans;
        }
    }

    private static void pushDown(Node[] tree, int i) {
        if (tree[i].tag != 0) {
            tree[2 * i].tag += tree[i].tag;
            tree[2 * i].sum += (long) (tree[2 * i].r - tree[2 * i].l + 1) * tree[i].tag;

            tree[2 * i + 1].tag += tree[i].tag;
            tree[2 * i + 1].sum += (long) (tree[2 * i + 1].r - tree[2 * i + 1].l + 1) * tree[i].tag;

            tree[i].tag = 0;
        }
    }
}

/*
输入
5 5
1 5 4 2 3
2 2 4
1 2 3 2
2 3 4
1 1 5 1
2 1 4

输出
11
8
20
*/