/*洛谷1352. 没有上司的聚会
https://www.luogu.com.cn/problem/P1352

题目描述
某大学有 n 个职员，编号为 1…n。
他们之间有从属关系，也就是说他们的关系就像一棵以校长为根的树，父结点就是子结点的直接上司。
现在有个周年庆宴会，宴会每邀请来一个职员都会增加一定的快乐指数 ri
但是呢，如果某个职员的直接上司来参加舞会了，那么这个职员就无论如何也不肯来参加舞会了。
所以，请你编程计算，邀请哪些职员可以使快乐指数最大，求最大的快乐指数。

输入格式
输入的第一行是一个整数 n。
第 2 到第 (n+1) 行，每行一个整数，第 (i+1) 行的整数表示 i 号职员的快乐指数 ri
第 (n+2) 到第 2n 行，每行输入一对整数 l,k，代表 k 是 l 的直接上司。

输出格式
输出一行一个整数代表最大的快乐指数。

数据范围
对于 100% 的数据，保证 1≤n≤6×10^3, −128≤ri≤127，1≤l,k≤n
*/

package dynamicProgram;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class LG1352 {
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

    static int[][] dp;
    static int[] r;
    static ArrayList<Integer>[] g;
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        r = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            r[i] = sc.nextInt();
        }
        g = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        int[] v = new int[n + 1];
        Arrays.fill(v, 0);
        for (int i = 1; i < n; i++) {
            int l,k;
            l = sc.nextInt();
            k = sc.nextInt();
            g[k].add(l);
            v[l] = 1;
        }
        dp = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = 0;
            }
        }
        int root = -1;
        for (int i = 1; i <= n; i++) {
            if (v[i] == 0) {
                root = i;
            }
        }
        //dfs(root);
        int[] order = new int[n];
        int index = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            order[index++] = u;
            for (int i = 0; i < g[u].size(); i++) {
                int y = g[u].get(i);
                stack.push(y);
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            dp[u][0] = 0;
            dp[u][1] = r[u];
            for (int child : g[u]) {
                dp[u][0] += Math.max(dp[child][0], dp[child][1]);
                dp[u][1] += dp[child][0];
            }
        }
        System.out.println(Math.max(dp[root][0], dp[root][1]));
    }
    /*private static void dfs(int root) {
        dp[root][0] = 0;
        dp[root][1] = r[root];
        for (int i = 0; i < g[root].size(); i++) {
            int y = g[root].get(i);
            dfs(y);
            dp[root][0] += Math.max(dp[y][0], dp[y][1]);
            dp[root][1] += dp[y][0];
        }
    }*/
}

/*
输入
7
1
1
1
1
1
1
1
1 3
2 3
6 4
7 4
4 5
3 5

输出
5
*/