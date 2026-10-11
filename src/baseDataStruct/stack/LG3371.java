/*洛谷3371. 单源最短路径(弱化版)

题目描述
给出一个有向图，请输出从某一点出发到所有点的最短路径长度。

输入格式
第一行包含三个整数 n,m,s，分别表示点的个数、有向边的个数、出发点的编号。
接下来 m 行每行包含三个整数 u,v,w，表示一条 u→v 的，长度为 w 的边。

输出格式
输出一行 n 个整数，第 i 个表示 s 到第 i 个点的最短路径，若不能到达则输出 (2^31)−1。

数据范围
对于 20% 的数据：1≤n≤5，1≤m≤15；
对于 40% 的数据：1≤n≤100，1≤m≤10%^4；
对于 70% 的数据：1≤n≤1000，1≤m≤10^5；
对于 100% 的数据：1≤n≤10^4，1≤m≤5×10^5，1≤u,v≤n，w≥0，∑w<2^31
保证数据随机。
*/

package baseDataStruct.stack;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LG3371 {

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

    static Edge[] edges;
    static int[] dist;

    static int[] head;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        edges = new Edge[m];
        dist = new int[n + 1];
        head = new int[n + 1];
        Arrays.fill(head, -1);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            //edges[i] = new Edge(u, v, w);
            edges[i] = new Edge(v, head[u], w);
            head[u] = i;
        }
        //bellmanFord(n, m);
        boolean hasNegCircle = !spfa(n, s);
        for (int i = 1; i <= n; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    private static class Edge {
        int to, next, w;
        Edge(int to, int next, int w) {
            this.to = to;
            this.next = next;
            this.w = w;
        }
    }

    // 优化后的SPFA算法 + 判断是否存在负权环
    private static boolean spfa(int n, int s) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] inQ = new boolean[n + 1];
        int[] inCnt = new int[n + 1];
        Arrays.fill(inCnt, 0);
        Arrays.fill(inQ, false);
        q.add(s);
        inQ[s] = true;
        while (!q.isEmpty()) {
            int curr = q.poll();
            inQ[curr] = false;
            for (int i = head[curr]; i != -1; i = edges[i].next) {
                int to  = edges[i].to;
                int w = edges[i].w;
                if (dist[curr] + w < dist[to]) {
                    dist[to] = dist[curr] + w;
                    if (!inQ[to]) {
                        q.add(to);
                        inQ[to] = true;
                        inCnt[to]++;
                    }
                }
                if (inCnt[to] >= n) {
                    return false;
                }
            }
        }
        return true;
    }


    // 一般贝尔曼福特算法
    /*private static class Edge {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    private static void bellmanFord(int n, int m) {
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int u = edges[j].u;
                int v = edges[j].v;
                int w = edges[j].w;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }
    }*/
}

/*
输入
4 6 1
1 2 2
2 3 2
2 4 1
1 3 5
3 4 3
1 4 4

输出
0 2 4 3
*/