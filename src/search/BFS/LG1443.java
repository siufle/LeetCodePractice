/*洛谷1443. 马的遍历
题目描述
有一个 n×m 的棋盘，在某个点 (x,y) 上有一个马，要求你计算出马到达棋盘上任意一个点最少要走几步。

输入格式
输入只有一行四个整数，分别为 n,m,x,y。

输出格式
一个 n×m 的矩阵，代表马到达某个点最少要走几步（不能到达则输出 −1）
*/

package search.BFS;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class LG1443 {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int[] passX = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] passY = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[][] dist = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                dist[i][j] = -1;
            }
        }
        dist[x][y] = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            for (int i = 0; i < 8; i++) {
                int nextX = curX + passX[i];
                int nextY = curY + passY[i];
                if (nextX > 0 && nextX < n + 1 && nextY > 0 && nextY < m + 1 && dist[nextX][nextY] == -1) {
                    dist[nextX][nextY] = dist[curX][curY] + 1;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
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
            return value * sign;
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
3 3 1 1

输出
0 3 2
3 -1 1
2 1 4
*/