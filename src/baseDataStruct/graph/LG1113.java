/*洛谷1113. 杂务
题目描述
John 的农场在给奶牛挤奶前有很多杂务要完成，每一项杂务都需要一定的时间来完成它。
比如：他们要将奶牛集合起来，将他们赶进牛棚，为奶牛清洗乳房以及一些其它工作。尽早将所有杂务完成是必要的，因为这样才有更多时间挤出更多的牛奶。
当然，有些杂务必须在另一些杂务完成的情况下才能进行。比如：只有将奶牛赶进牛棚才能开始为它清洗乳房，还有在未给奶牛清洗乳房之前不能挤奶。
我们把这些工作称为完成本项工作的准备工作。至少有一项杂务不要求有准备工作，这个可以最早着手完成的工作，标记为杂务 1。
John 有需要完成的 n 个杂务的清单，并且这份清单是有一定顺序的，杂务 k (k>1) 的准备工作只可能在杂务 1 至 k−1 中。
写一个程序依次读入每个杂务的工作说明。计算出所有杂务都被完成的最短时间。
当然互相没有关系的杂务可以同时工作，并且，你可以假定 John 的农场有足够多的工人来同时完成任意多项任务。

输入格式
第 1 行，一个整数 n (3≤n≤10,000)，必须完成的杂务的数目；
第 2 至 n+1 行，每行有一些用空格隔开的整数，分别表示：
工作序号（保证在输入文件中是从 1 到 n 有序递增的）；
完成工作所需要的时间 len (1≤len≤100)；
一些必须完成的准备工作，总数不超过 100 个，由一个数字 0 结束。有些杂务没有需要准备的工作只描述一个单独的 0。
保证整个输入文件中不会出现多余的空格。

输出格式
一个整数，表示完成所有杂务所需的最短时间。

数据范围
3≤n≤10,000，1≤len≤100
*/


package baseDataStruct.graph;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LG1113 {

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
        int to, next;
        Edge(int to, int next) {
            this.to = to;
            this.next = next;
        }
    }

    static Edge[] edges;
    static int[] head;
    static int[] ind;
    static int[] time;
    static int[] len;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        edges = new Edge[n * 100];
        head = new int[n + 1];
        ind = new int[n + 1];
        time = new int[n + 1];
        len = new int[n + 1];
        Arrays.fill(head, -1);
        Arrays.fill(ind, 0);
        Arrays.fill(time, 0);
        for (int i = 1; i <= n; i++) {
            int v = sc.nextInt();
            len[v] = sc.nextInt();
            while (true) {
                int u = sc.nextInt();
                if (u == 0) break;
                ind[v]++;
                edges[cnt] = new Edge(v, head[u]);
                head[u] = cnt++;
            }
        }
        kahn(n);
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            maxTime = Math.max(maxTime, time[i]);
        }
        System.out.println(maxTime);
    }

    private static void kahn(int n) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (ind[i] == 0) {
                q.add(i);
                time[i] = len[i];
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            int t = time[u];
            for (int i = head[u]; i != -1; i = edges[i].next) {
                int v = edges[i].to;
                time[v] = Math.max(time[v], t + len[v]);
                ind[v]--;
                if (ind[v] == 0) {
                    q.add(v);
                }
            }
        }
    }

}


/*
输入
7
1 5 0
2 2 1 0
3 3 2 0
4 6 1 0
5 1 2 4 0
6 8 2 4 0
7 4 3 5 6 0

输出
23

*/