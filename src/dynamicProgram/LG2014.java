/*洛谷2014. 选课
https://www.luogu.com.cn/problem/P2014

题目描述
在大学里每个学生，为了达到一定的学分，必须从很多课程里选择一些课程来学习，
在课程里有些课程必须在某些课程之前学习，如高等数学总是在其它课程之前学习。
现在有 N 门功课，每门课有若干学分，分别记作 s1,s2,⋯,sN，
每门课有一门或没有直接先修课（若课程 a 是课程 b 的先修课即只有学完了课程 a，才能学习课程 b）。
一个学生要从这些课程里选择 M 门课程学习，问他能获得的最大学分是多少？
题目保证课程安排无冲突。（即不会有 a 是 b 的先修课，b 也是 a 的先修课这类情况存在。）

输入格式
第一行有两个整数 N，M 用空格隔开 。
接下来的 N 行，第 i+1 行包含两个整数 ki和 si ，
ki表示第 i 门课的直接先修课，si表示第 i 门课的学分。若 ki=0 表示没有直接先修课
数据保证至少存在一个 ki=0，即至少一门课无先修课。

输出格式
只有一行，选 M 门课程的最大学分。

数据范围
(1≤N≤300 , 1≤M≤300)
(0≤ki≤N , 1≤si≤20)
*/

package dynamicProgram;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

public class LG2014 {
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

    private static class Edge {
        int to,next;
        Edge(int to,int next){
            this.to = to;
            this.next = next;
        }
    }

    static int n,m;
    static Edge[] edges;
    static int[] head;
    static int[] val;
    static int cnt = 0;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        n = sc.nextInt();
        m = sc.nextInt();
        m++;
        head = new int[n + 1];
        Arrays.fill(head, -1);
        edges = new Edge[n + 1];
        val = new int[n + 1];
        val[0] = 0;
        for (int i = 1; i <= n; i++) {
            int k = sc.nextInt();
            int s = sc.nextInt();
            val[i] = s;
            edges[cnt] = new Edge(i, head[k]);
            head[k] = cnt++;
        }
        dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 0);
        }
        dfs(0, 0);
        System.out.println(dp[0][m]);
    }

    private static int dfs(int u, int d) {
        int sumu = 1;
        dp[u][0] = 0;
        dp[u][1] = val[u];

        for (int i = head[u]; i != -1; i = edges[i].next) {
            int v = edges[i].to;
            int sumv = dfs(v, d + 1);
            sumu += sumv;
            for (int j = Math.min(m - d, sumu); j >= 1; j--) {
                for (int k = 0; k <= Math.min(j - 1, sumv); k++) {
                    dp[u][j] = Math.max(dp[u][j], dp[v][k] + dp[u][j - k]);
                }
            }
        }
        return sumu;
    }
}

/*
输入
7 4
2 2
0 1
0 4
2 1
7 1
7 6
2 2

输出
13
*/