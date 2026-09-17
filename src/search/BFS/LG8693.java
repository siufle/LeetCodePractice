/*洛谷8693. 大胖子走迷宫
题目描述
小明是个大胖子，或者说是个大大胖子，如果说正常人占用 1×1 的面积，小明要占用 5×5 的面积。
由于小明太胖了，所以他行动起来很不方便。当玩一些游戏时，小明相比小伙伴就吃亏很多。小明的朋友们制定了一个计划，帮助小明减肥。
计划的主要内容是带小明玩一些游戏，让小明在游戏中运动消耗脂肪。走迷宫是计划中的重要环节。
朋友们设计了一个迷宫，迷宫可以看成是一个由 n×n 个方阵组成的方阵，正常人每次占用方阵中 1×1 的区域，
而小明要占用 5×5 的区域。小明的位置定义为小明最正中的一个方格。迷宫四周都有障碍物。
为了方便小明，朋友们把迷宫的起点设置在了第 3 行第 3 列，终点设置在了第 n−2 行第 n−2 列。
小明在时刻 0 出发，每单位时间可以向当前位置的上、下、左、右移动单位 1 的距离，也可以停留在原地不动。
小明走迷宫走得很辛苦，如果他在迷宫里面待的时间很长，则由于消耗了很多脂肪，他会在时刻 k 变成一个胖子，只占用 3×3 的区域。
如果待的时间更长，他会在时刻 2×k 变成一个正常人，只占用 1×1 的区域。注意，当小明变瘦时迷宫的起点和终点不变。
请问，小明最少多长时间能走到迷宫的终点。注意，小明走到终点时可能变瘦了也可能没有变瘦。

输入格式
输入的第一行包含两个整数 n，k。 接下来 n 行，每行一个由 n 个字符组成的字符串，字符为 + 表示为空地， 字符为 * 表示为阻碍物

输出格式
输出一个整数，表示小明走到终点的最小时间。

*/

package search.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class LG8693 {

    static int n, k;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        k = Integer.parseInt(s[1]);
        map = new char[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= n; j++) {
                map[i][j] = line.charAt(j - 1);
            }
        }
        visited = new boolean[n + 1][n + 1];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(3, 3, 0, 2));
        visited[3][3] = true;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.x == n - 2 && cur.y == n - 2) {
                System.out.println(cur.t);
                return;
            }
            int r;
            if (cur.s != 0) {
                if (cur.t + 1 < k) r = 2;
                else if (cur.t + 1 < 2 * k) r = 1;
                else r = 0;
                queue.add(new Node(cur.x, cur.y, cur.t + 1, r));
            }
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (nextX < 1 || nextX > n || nextY < 1 || nextY > n) continue;
                if (check(nextX, nextY, cur.s)) {
                    visited[nextX][nextY] = true;
                    int nextT = cur.t + 1;
                    if (cur.t + 1 < k) r = 2;
                    else if (cur.t + 1 < 2 * k) r = 1;
                    else r = 0;
                    queue.add(new Node(nextX, nextY, nextT, r));
                }
            }
        }
    }

    static boolean check(int x, int y, int r) {
        if (visited[x][y]) return false;
        for (int i = x - r; i <= x + r; i++) {
            for (int j = y - r; j <= y + r; j++) {
                if (i < 1 || i > n || j < 1 || j > n || map[i][j] == '*') return false;
            }
        }
        return true;
    }

    static class Node {
        int x, y, t, s;

        Node(int x, int y, int t, int s) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.s = s;
        }
    }
}

/*
输入
9 5
+++++++++
+++++++++
+++++++++
+++++++++
+++++++++
***+*****
+++++++++
+++++++++
+++++++++

输出
16
*/