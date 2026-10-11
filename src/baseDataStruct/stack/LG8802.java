/*洛谷8802. 出差
题目描述
A 国有 N 个城市，编号为 1…N 小明是编号为 1 的城市中一家公司的员工，今天突然接到了上级通知需要去编号为 N 的城市出差。
由于疫情原因，很多直达的交通方式暂时关闭，小明无法乘坐飞机直接从城市 1 到达城市 N，需要通过其他城市进行陆路交通中转。
小明通过交通信息网，查询到了 M 条城市之间仍然还开通的路线信息以及每一条路线需要花费的时间。
同样由于疫情原因，小明到达一个城市后需要隔离观察一段时间才能离开该城市前往其他城市。
通过网络，小明也查询到了各个城市的隔离信息。（由于小明之前在城市 1，因此可以直接离开城市 1，不需要隔离）
由于上级要求，小明希望能够尽快赶到城市 N, 因此他求助于你，希望你能帮他规划一条路线，能够在最短时间内到达城市 N 。

输入格式
第 1 行：两个正整数 N,M，N 表示 A 国的城市数量, M 表示未关闭的路线数量。
第 2 行: N 个正整数，第 i 个整数 Ci 表示到达编号为 i 的城市后需要隔离的时间。
第 3…M+2 行: 每行 3 个正整数, u,v,c, 表示有一条城市 u 到城市 v 的双向路线仍然开通着，通过该路线的时间为 c。

输出格式
第 1 行：1 个正整数，表示小明从城市 1 出发到达城市 N 的最短时间。（到达城市 N，不需要计算城市 N 的隔离时间）

数据范围
对于 100% 的数据, 1≤N≤1000,1≤M≤10000,1≤Ci≤200,1≤u,v≤ N,1≤c≤1000
*/

package baseDataStruct.stack;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class LG8802 {

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
        int to, next, w;
        Edge(int to, int next, int w) {
            this.to = to;
            this.next = next;
            this.w = w;
        }
    }

    static Edge[] edges;
    static int[] head;
    static int[] dis;
    static int[] c;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        edges = new Edge[2 * m];
        head = new int[n + 1];
        dis = new int[n + 1];
        c = new int[n + 1];
        Arrays.fill(head, -1);
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[1] = 0;
        for (int i = 1; i <= n; i++) {
            c[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges[i] = new Edge(v, head[u], w + c[v]);
            head[u] = i;
            edges[i + m] = new Edge(u, head[v], w + c[u]);
            head[v] = i + m;
        }
        dijkstra();
        System.out.println(dis[n] - c[n]);
    }

    private static void dijkstra() {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.add(new int[]{0, 1});
        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int d = curr[0], u = curr[1];
            if (d > dis[u]) continue;
            for (int i = head[u]; i != -1; i = edges[i].next) {
                int v = edges[i].to;
                if (dis[v] > d + edges[i].w) {
                    dis[v] = d + edges[i].w;
                    heap.add(new int[]{dis[v], v});
                }
            }
        }
    }
}

/*
输入
4 4
5 7 3 4
1 2 4
1 3 5
2 4 3
3 4 5

输出
13
*/