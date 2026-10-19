/*洛谷1265. 公路修建
https://www.luogu.com.cn/problem/P1265

题目描述
某国有 n 个城市，它们互相之间没有公路相通，因此交通十分不便。
为解决这一“行路难”的问题，政府决定修建公路。修建公路的任务由各城市共同完成。
修建工程分若干轮完成。在每一轮中，每个城市选择一个与它最近的城市，申请修建通往该城市的公路。
政府负责审批这些申请以决定是否同意修建。

政府审批的规则如下：
如果两个或以上城市申请修建同一条公路，则让它们共同修建；
如果三个或以上的城市申请修建的公路成环。
A 申请修建公路 AB，B 申请修建公路 BC，C 申请修建公路 CA。则政府将否决其中最短的一条公路的修建申请；
其他情况的申请一律同意。
一轮修建结束后，可能会有若干城市可以通过公路直接或间接相连。这些可以互相连通的城市即组成“城市联盟”。
在下一轮修建中，每个“城市联盟”将被看作一个城市，发挥一个城市的作用。
当所有城市被组合成一个“城市联盟”时，修建工程也就完成了。
你的任务是根据城市的分布和前面讲到的规则，计算出将要修建的公路总长度。

输入格式
第一行一个整数 n，表示城市的数量。
以下 n 行，每行两个整数 x 和 y，表示一个城市的坐标。

输出格式
一个实数，四舍五入保留两位小数，表示公路总长。（保证有唯一解）

数据范围
n≤5000
−10^6≤x,y≤10^6
*/

package baseDataStruct.graph;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

public class LG1265 {
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
    private static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Node[] nodes;
    static int[] vis;
    static double[] dis;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        nodes = new Node[n + 1];
        vis = new int[n + 1];
        dis = new double[n + 1];
        Arrays.fill(vis, 0);
        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            nodes[i] = new Node(x, y);
        }
        vis[1] = 1;
        dis[1] = 0;
        for (int i = 2; i <= n; i++) {
            dis[i] = squaredDist(nodes[1], nodes[i]);
        }
        double ans = 0;
        for (int i = 1; i < n; i++) {
            double minDis = Double.MAX_VALUE;
            int k = -1;
            for (int j = 1; j <= n; j++) {
                if (vis[j] == 0 && dis[j] < minDis) {
                    minDis = dis[j];
                    k = j;
                }
            }
            vis[k] = 1;
            ans += Math.sqrt(minDis);
            for (int j = 1; j <= n; j++) {
                double d = squaredDist(nodes[k], nodes[j]);
                if (vis[j] == 0 && dis[j] > d) {
                    dis[j] = d;
                }
            }
        }
        System.out.printf("%.2f\n", ans);
    }
    private static double squaredDist(Node n1, Node n2) {
        double dx = n1.x - n2.x;
        double dy = n1.y - n2.y;
        return dx * dx + dy * dy;
    }
}

/*
输入
4
0 0
1 2
-1 2
0 4

输出
6.47
*/