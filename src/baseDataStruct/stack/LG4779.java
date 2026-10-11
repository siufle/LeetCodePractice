/*洛谷4779. 单元最短路径(标准版)

题目描述
给定一个 n 个点，m 条有向边的带非负权图，请你计算从 s 出发，到每个点的距离。
数据保证你能从 s 出发到任意点。

输入格式
第一行为三个正整数 n,m,s。 第二行起 m 行，每行三个非负整数 ui ,vi ,wi ，表示从 ui 到 vi 有一条权值为 wi 的有向边。

输出格式
输出一行 n 个空格分隔的非负整数，表示 s 到每个点的距离。

数据范围
1≤n≤10^5
1≤m≤2×10^5；
s=1；
1≤ui,vi≤n
0≤wi≤10^9 ,0≤∑wi≤10^9 。
*/

package baseDataStruct.stack;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class LG4779 {

    static Edge[] edges;
    static int[] head;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        edges = new Edge[m];
        head = new int[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(head, -1);
        dist[s] = 0;
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges[i] = new Edge(v, head[u], w);
            head[u] = i;
        }
        dijkstra(s);
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

    static void dijkstra(int s) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.add(new int[]{0, s});
        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int d = curr[0], u = curr[1];
            if (d > dist[u]) continue;
            for (int i = head[u]; i != -1; i = edges[i].next) {
                int v = edges[i].to;
                int w = edges[i].w;
                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    heap.add(new int[]{dist[v], v});
                }
            }
        }
    }

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