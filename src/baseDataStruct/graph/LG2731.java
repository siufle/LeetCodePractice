/*洛谷2731. 骑马修栅栏
https://www.luogu.com.cn/problem/P2731

题目描述
John 是一个与其他农民一样懒的人。他讨厌骑马，因此从来不两次经过一个栅栏。
John 的农场上一共有 m 个栅栏，每一个栅栏连接两个顶点，顶点用 1 到 500 标号（虽然有的农场并没有那么多个顶点）。
一个顶点上至少连接 1 个栅栏，没有上限。两顶点间可能有多个栅栏。
所有栅栏都是连通的（也就是你可以从任意一个栅栏到达另外的所有栅栏）。
John 能从任何一个顶点（即两个栅栏的交点）开始骑马，在任意一个顶点结束。
你需要求出输出骑马的路径（用路上依次经过的顶点号码表示)，使每个栅栏都恰好被经过一次。
如果存在多组可行的解，按照如下方式进行输出：
如果把输出的路径看成是一个 500 进制的数，那么当存在多组解的情况下，输出 500 进制表示法中最小的一个
（也就是输出第一位较小的，如果还有多组解，输出第二位较小的，以此类推）。
输入数据保证至少有一个解。

输入格式
第一行一个整数 m，表示栅栏的数目。
从第二行到第 (m+1) 行，每行两个整数 u,v，表示有一条栅栏连接 u,v 两个点。

输出格式
共 (m+1) 行，每行一个整数，依次表示路径经过的顶点号。
注意数据可能有多组解，但是只有上面题目要求的那一组解是认为正确的。
数据保证至少有一组可行解

数据范围
对于 100% 的数据，1≤m≤1024,1≤u,v≤500。
*/

package baseDataStruct.graph;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class LG2731 {
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

    static int m;
    static int[][] edges = new int[505][505];
    static int[] d = new int[505];
    static int mini = 500, maxi = 0;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        m = sc.nextInt();
        for (int i = 0; i < 505; i++) {
            Arrays.fill(edges[i], 0);

        }
        Arrays.fill(d, 0);

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            mini = Math.min(mini, Math.min(u, v));
            maxi = Math.max(maxi, Math.max(u, v));
            edges[u][v] += 1;
            edges[v][u] += 1;
            d[u] += 1;
            d[v] += 1;
        }

        int start = mini;
        for (int i = mini; i <= maxi; i++) {
            if (d[i] % 2 == 1) {
                start = i;
                break;
            }
        }

        dfs(start);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private static void dfs(int u) {
        for (int v = mini; v <= maxi; v++) {
            if (edges[u][v] > 0) {
                edges[u][v] -= 1;
                edges[v][u] -= 1;
                dfs(v);
            }
        }
        stack.push(u);
    }
}

/*
输入
9
1 2
2 3
3 4
4 2
4 5
2 5
5 6
5 7
4 6

输出
1
2
3
4
2
5
4
6
5
7
*/