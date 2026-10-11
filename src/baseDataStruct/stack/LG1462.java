/*洛谷1462. 通往奥格瑞玛的道路
题目描述
在艾泽拉斯，有 n 个城市。编号为 1,2,3,…,n。
城市之间有 m 条双向的公路，连接着两个城市，从某个城市到另一个城市，会遭到联盟的攻击，进而损失一定的血量。
每次经过一个城市，都会被收取一定的过路费（包括起点和终点）。路上并没有收费站。
假设 1 为暴风城，n 为奥格瑞玛，而他的血量最多为 b，出发时他的血量是满的。如果他的血量降低至负数，则他就无法到达奥格瑞玛。
歪嘴哦不希望花很多钱，他想知道，在所有可以到达奥格瑞玛的道路中，对于每条道路所经过的城市单次收费的最大值，其最小值为多少。

输入格式
第一行 3 个正整数，n,m,b。分别表示有 n 个城市，m 条公路，歪嘴哦的血量为 b。
接下来有 n 行，每行 1 个非负整数，fi。表示经过城市 i，需要交费 fi 元。
再接下来有 m 行，每行 3 个正整数，ai,bi,ci（1≤ai,bi≤n）。
表示城市 ai 和城市 bi 之间有一条公路，如果从城市 ai 到城市 bi ，或者从城市 bi 到城市 ai ，会损失 ci 的血量。

输出格式
仅一个整数，表示歪嘴哦经过城市单次交费最大值的最小值。
如果他无法到达奥格瑞玛，输出 AFK。

数据范围
对于 60% 的数据，满足 n≤200，m≤10^4，b≤200
对于 100% 的数据，满足 1≤n≤10^4，1≤m≤5×10^4，1≤b≤10^9；
对于 100% 的数据，满足 1≤ci≤10^9，0≤fi≤10^9，可能有两条边连接着相同的城市。

*/

package baseDataStruct.stack;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class LG1462 {

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
    static int[] f;
    static long[] dis;
    static int fMax, fMin;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int b = sc.nextInt();
        edges = new Edge[2 * m];
        head = new int[n + 1];
        f = new int[n + 1];
        dis = new long[n + 1];
        fMax = Integer.MIN_VALUE;
        fMin = Integer.MAX_VALUE;
        Arrays.fill(head, -1);
        Arrays.fill(dis, Long.MAX_VALUE);
        dis[1] = 0;
        for (int i = 1; i <= n; i++) {
            f[i] = sc.nextInt();
            fMax = Math.max(fMax, f[i]);
            fMin = Math.min(fMin, f[i]);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges[i] = new Edge(v, head[u], w);
            head[u] = i;
            edges[i + m] = new Edge(u, head[v], w);
            head[v] = i + m;
        }

        if (!dijkstra(n, b, fMax)) {
            System.out.println("AFK");
            return;
        }
        int l = fMin, r = fMax, ans = -1;
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            Arrays.fill(dis, Long.MAX_VALUE);
            dis[1] = 0;
            if (dijkstra(n, b, mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(ans);
    }

    private static boolean dijkstra(int n, int b, int max) {
        if (f[1] > max) return false;
        PriorityQueue<long[]> heap = new PriorityQueue<>((x, y) -> Long.compare(x[0], y[0]));
        heap.add(new long[]{0, 1});
        while (!heap.isEmpty()) {
            long[] cur = heap.poll();
            long d = cur[0];
            int u = (int)cur[1];
            if (d > dis[u]) continue;
            for (int i = head[u]; i != -1; i = edges[i].next) {
                int v = edges[i].to, w = edges[i].w;
                if (f[v] <= max && d + w < dis[v]) {
                    dis[v] = d + w;
                    heap.add(new long[]{ dis[v], v});
                }
            }
        }
        return dis[n] <= b;
    }
}

/*
输入
4 4 8
8
5
6
10
2 1 2
2 4 1
1 3 4
3 4 3

输出
10
*/