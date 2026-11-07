/*洛谷3373. 【模板】线段树2
https://www.luogu.com.cn/problem/P3373

题目描述
如题，已知一个数列 a，你需要进行下面三种操作：
将某区间每一个数乘上 x；
将某区间每一个数加上 x；
求出某区间每一个数的和。

输入格式
第一行包含三个整数 n,q,m，分别表示该数列数字的个数、操作的总个数和模数。

第二行包含 n 个用空格分隔的整数，其中第 i 个数字表示数列第 i 项的初始值 ai
接下来 q 行每行包含若干个整数，表示一个操作，具体如下：
操作 1： 格式：1 x y k 含义：将区间 [x,y] 内每个数乘上 k。
操作 2： 格式：2 x y k 含义：将区间 [x,y] 内每个数加上 k。
操作 3： 格式：3 x y 含义：输出区间 [x,y] 内每个数的和对 m 取模所得的结果。

输出格式
输出包含若干行整数，即为所有操作 3 的结果。

数据范围
对于 30% 的数据：n≤8，q≤10。
对于 70% 的数据：n≤10^3，q≤10^4
对于 100% 的数据：1≤n≤10^5，1≤q≤10^5,1≤ai,k≤10^4
除样例外，m=571373。
*/

package dynamicProgram;

import java.io.BufferedInputStream;
import java.io.IOException;

public class LG3373 {
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
        long sum, tagAdd, tagMul;

        Node(int l, int r, long sum, long tagAdd, long tagMul) {
            this.l = l;
            this.r = r;
            this.sum = sum;
            this.tagAdd = tagAdd;
            this.tagMul = tagMul;
        }
    }

    private static long m = 571373;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int q = sc.nextInt();
        m = sc.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        Node[] tree = new Node[4 * n];
        buildTree(tree, a, 1, n, 1);
        for (int i = 0; i < q; i++) {
            int op = sc.nextInt();
            if (op == 1) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int k = sc.nextInt();
                updateMul(tree, x, y, k, 1);
            } else if (op == 2) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int k = sc.nextInt();
                updateAdd(tree, x, y, k, 1);
            } else if (op == 3) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                long sum = queryMod(tree, x, y, 1);
                System.out.println(sum);
            }
        }
    }

    private static void buildTree(Node[] tree, int[] a, int l, int r, int i) {
        if (l == r) {
            tree[i] = new Node(l, r, a[l] % m, 0, 1);
        } else {
            int mid = (l + r) / 2;
            buildTree(tree, a, l, mid, i * 2);
            buildTree(tree, a, mid + 1, r, i * 2 + 1);
            tree[i] = new Node(l, r, (tree[i * 2].sum + tree[i * 2 + 1].sum) % m, 0, 1);
        }
    }

    private static void pushDown(Node[] tree, int i) {
        if (tree[i].tagMul != 1 || tree[i].tagAdd != 0) {
            long mul = tree[i].tagMul;
            long add = tree[i].tagAdd;

            int lc = 2 * i;
            tree[lc].tagMul = (tree[lc].tagMul * mul) % m;
            tree[lc].tagAdd = (mul * tree[lc].tagAdd + add) % m;
            int lenL = tree[lc].r - tree[lc].l + 1;
            tree[lc].sum = (tree[lc].sum * mul + add * lenL) % m;

            int rc = 2 * i + 1;
            tree[rc].tagMul = (tree[rc].tagMul * mul) % m;
            tree[rc].tagAdd = (mul * tree[rc].tagAdd + add) % m;
            int lenR = tree[rc].r - tree[rc].l + 1;
            tree[rc].sum = (tree[rc].sum * mul + add * lenR) % m;

            tree[i].tagMul = 1;
            tree[i].tagAdd = 0;
        }
    }

    private static void updateMul(Node[] tree, int le, int ri, int k, int i) {
        if (le <= tree[i].l && tree[i].r <= ri) {
            tree[i].sum = tree[i].sum * k % m;
            tree[i].tagMul = (tree[i].tagMul * k) % m;
            tree[i].tagAdd = (tree[i].tagAdd * k) % m;
        } else {
            pushDown(tree, i);
            int mid = (tree[i].l + tree[i].r) / 2;
            if (le <= mid) {
                updateMul(tree, le, ri, k, i * 2);
                tree[i].sum = (tree[i * 2].sum + tree[i * 2 + 1].sum) % m;
            }
            if (ri > mid) {
                updateMul(tree, le, ri, k, i * 2 + 1);
                tree[i].sum = (tree[i * 2].sum + tree[i * 2 + 1].sum) % m;
            }
        }
    }

    private static void updateAdd(Node[] tree, int le, int ri, int k, int i) {
        if (le <= tree[i].l && tree[i].r <= ri) {
            tree[i].sum = (tree[i].sum + (long) k * (tree[i].r - tree[i].l + 1)) % m;
            tree[i].tagAdd = (tree[i].tagAdd + k) % m;
        } else {
            pushDown(tree, i);
            int mid = (tree[i].l + tree[i].r) / 2;
            if (le <= mid) {
                updateAdd(tree, le, ri, k, i * 2);
                tree[i].sum = (tree[i * 2].sum + tree[i * 2 + 1].sum) % m;
            }
            if (ri > mid) {
                updateAdd(tree, le, ri, k, i * 2 + 1);
                tree[i].sum = (tree[i * 2].sum + tree[i * 2 + 1].sum) % m;
            }
        }
    }

    private static long queryMod(Node[] tree, int le, int ri, int i) {
        if (le <= tree[i].l && tree[i].r <= ri) {
            return tree[i].sum % m;
        } else {
            pushDown(tree, i);
            int mid = (tree[i].l + tree[i].r) / 2;
            long ans = 0;
            if (le <= mid) {
                ans += queryMod(tree, le, ri, i * 2);
            }
            if (ri >= mid + 1) {
                ans += queryMod(tree, le, ri, i * 2 + 1);
            }
            return ans % m;
        }
    }
}

/*
输入
5 5 38
1 5 4 2 3
2 1 4 1
3 2 5
1 2 4 2
2 3 5 5
3 1 4

输出
17
2
*/