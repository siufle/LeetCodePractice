/*洛谷1194. 买礼物
https://www.luogu.com.cn/problem/P1194

题目描述
又到了一年一度的明明生日了，明明想要买 B 样东西，巧的是，这 B 样东西价格都是 A 元。
但是，商店老板说最近有促销活动，也就是：
如果你买了第 I 样东西，再买第 J 样，那么就可以只花 Kij元，更巧的是，Kij竟然等于Kji 。
现在明明想知道，他最少要花多少钱。

输入格式
第一行两个整数，A,B。
接下来 B 行，每行 B 个数，第 I 行第 J 个为 Kij
我们保证 Kij=Kji 并且 Kii =0。
特别的，如果 Kij =0，那么表示这两样东西之间不会导致优惠。
注意 Kij 可能大于 A。

输出格式
一个整数，为最小要花的钱数。

数据范围
对于 30% 的数据，1≤B≤10。
对于 100% 的数据，1≤B≤500,0≤A,Kij≤1000。
*/

package baseDataStruct.graph;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

public class LG1194 {
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
    private static class Edge{
        int u,v,w;
        Edge(int u,int v,int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static Edge[] edges;
    static int cnt = 0;
    static int[] fa;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int a = sc.nextInt();
        int b = sc.nextInt();
        edges = new Edge[b * b];
        fa = new int[b + 1];
        fa[0] = 0;
        for (int i = 1; i <= b; i++) {
            fa[i] = i;
            edges[cnt++] = new Edge(0, i, a);
        }
        for (int i = 1; i <= b; i++) {
            int x = i;
            for (int j = 1; j <= b; j++) {
                int y = j;
                int k = sc.nextInt();
                if (k != 0) {
                    edges[cnt++] = new Edge(x, y, k);
                }
            }
        }
        Arrays.sort(edges, 0, cnt, (o1, o2) -> o1.w - o2.w);
        int mst = kruskal();
        System.out.println(mst);
    }

    private static int kruskal() {
        int ans = 0;
        for (int i = 0; i < cnt; i++) {
            int x = edges[i].u;
            int y = edges[i].v;
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                fa[fx] = fy;
                ans += edges[i].w;
            }
        }
        return ans;
    }

    private static int find(int x) {
        if (fa[x] != x) {
            fa[x] = find(fa[x]);
        }
        return fa[x];
    }
}

/*
输入
1 1
0

输出
1

输入
3 3
0 2 4
2 0 2
4 2 0

输出
7
*/