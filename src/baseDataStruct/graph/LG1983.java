/*洛谷1983. 车站分级
https://www.luogu.com.cn/problem/P1983

题目描述
一条单向的铁路线上，依次有编号为 1,2,…,n 的 n 个火车站。每个火车站都有一个级别，最低为 1 级。
现有若干趟车次在这条线路上行驶，每一趟都满足如下要求：
如果这趟车次停靠了火车站 x，则始发站、终点站之间所有级别大于等于火车站 x 的都必须停靠。
注意：起始站和终点站自然也算作事先已知需要停靠的站点。

输入格式
第一行包含 2 个正整数 n,m，用一个空格隔开。
第 i+1 行 (1≤i≤m) 中，首先是一个正整数 si(2≤si≤n)，表示第 i 趟车次有 si 个停靠站；
接下来有 si 个正整数，表示所有停靠站的编号，从小到大排列。每两个数之间用一个空格隔开。
输入保证所有的车次都满足要求。

输出格式
一个正整数，即 n 个火车站最少划分的级别数。

数据范围
对于 20% 的数据，1≤n,m≤10；
对于 50% 的数据，1≤n,m≤100；
对于 100% 的数据，1≤n,m≤1000。
*/

package baseDataStruct.graph;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LG1983 {
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

            int value = 0;
            while (c > ' ') {
                value = value * 10 + c - '0';
                c = read();
            }
            return value;
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
    private static class Edge {
        int to, next;
        Edge(int to, int next) {
            this.to = to;
            this.next = next;
        }
    }

    static Edge[] edges;
    static int[] head;
    static int cnt = 0;
    static int[] ind;
    static int[] lev;
    static int[] flag; //记录站点是否被停靠过
    static int[] s; //记录停下的站点
    static int[][] dp; //记录i到j的边是否已经存在

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        edges = new Edge[n * n];
        head = new int[n + 1];
        lev = new int[n + 1];
        ind = new int[n + 1];
        flag = new int[n + 1];
        dp = new int[n + 1][n + 1];
        Arrays.fill(head, -1);
        Arrays.fill(lev, 0);
        Arrays.fill(ind, 0);

        for (int i = 1; i <= m; i++) {
            int sn = sc.nextInt();
            Arrays.fill(flag, 0);
            s = new int[sn + 1];
            for (int j = 1; j <= sn; j++) {
                s[j] = sc.nextInt();
                flag[s[j]] = 1;
            }
            for (int j = s[1]; j <= s[sn]; j++) {
                if (flag[j] == 1) continue;
                for (int k = 1; k <= sn; k++) {
                    if (dp[j][s[k]] == 1) continue; //如果j到s[k]的边已经存在则跳过
                    edges[cnt] = new Edge(s[k], head[j]);
                    ind[s[k]]++;
                    dp[j][s[k]] = 1;
                    head[j] = cnt++;
                }
            }
        }
        kahn(n);
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, lev[i]);
        }
        System.out.println(ans);
    }
    private static void kahn(int n) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (ind[i] == 0) {
                q.add(i);
                lev[i] = 1;
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int i = head[u]; i != -1; i = edges[i].next) {
                int v = edges[i].to;
                ind[v]--;
                if (ind[v] == 0) {
                    q.add(v);
                    lev[v] = Math.max(lev[v], lev[u] + 1);
                }
            }
        }
    }
}

/*
输入
9 2
4 1 3 5 6
3 3 5 6
输出
2


输入
9 3
4 1 3 5 6
3 3 5 6
3 1 5 9
输出
3
*/