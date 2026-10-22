/*洛谷7771. 欧拉路径
https://www.luogu.com.cn/problem/P7771

题目描述
求有向图字典序最小的欧拉路径。

输入格式
第一行两个整数 n,m 表示有向图的点数和边数。
接下来 m 行每行两个整数 u,v 表示存在一条 u→v 的有向边。

输出格式
如果不存在欧拉路径，输出一行 No。
否则输出一行 m+1 个数字，表示字典序最小的欧拉路径。

数据范围
对于 50% 的数据，n,m≤10^3
对于 100% 的数据，1≤u,v≤n≤10^5，m≤2×10^5
*/

package baseDataStruct.graph;

import java.io.BufferedInputStream;
import java.io.IOException;

public class LG7771 {
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

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] from = new int[m];
        int[] to = new int[m];
        int[] ind = new int[n+1];
        int[] outd = new int[n+1];

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            from[i] = u;
            to[i] = v;
            ind[v]++;
            outd[u]++;
        }

        int start = 1;
        int cntStart = 0, cntEnd = 0;
        for (int i = 1; i <= n; i++) {
            int diff = ind[i] - outd[i];
            if (diff == 0) continue;
            if (diff == 1) {
                cntEnd++;
            } else if (diff == -1) {
                cntStart++;
                start = i;
            } else {
                System.out.println("No");
                return;
            }
        }
        if (cntEnd > 1 || cntStart > 1) {
            System.out.println("No");
            return;
        }

        countingSort(from, to, n, to);
        countingSort(from, to, n, from);

        int[] head = new int[n+1];
        int pos = 0;
        for (int i = 1; i <= n; i++) {
            head[i] = pos;
            pos += outd[i];
        }

        int[] del = head.clone();
        int[] st = new int[m+2];
        int top = 0;
        st[top++] = start;
        int[] ans = new int[m+1];
        int ai = m;

        while (top > 0) {
            int u = st[top - 1];
            int end = head[u] + outd[u];
            if (end > del[u]) {
                st[top++] = to[del[u]++];
            } else {
                top--;
                ans[ai--] = u;
            }
        }

        if (ai != -1) {
            System.out.println("No");
            return;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < m+1; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void countingSort(int[] from, int[] to, int n, int[] key) {
        int m = from.length;
        int[] cnt = new int[n+2];
        for (int i = 0; i < m; i++) {
            cnt[key[i]]++;
        }
        for (int i = 1; i <= n; i++) {
            cnt[i] += cnt[i-1];
        }
        int[] nf = new int[m];
        int[] nt = new int[m];
        for (int i = m - 1; i >= 0; i--) {
            int k = key[i];
            cnt[k]--;
            nf[cnt[k]] = from[i];
            nt[cnt[k]] = to[i];
        }
        System.arraycopy(nf, 0, from, 0, m);
        System.arraycopy(nt, 0, to, 0, m);
    }
}

/*
输入
4 6
1 3
2 1
4 2
3 3
1 2
3 4
输出
1 2 1 3 3 4 2

输入
5 5
1 2
3 5
4 3
3 4
2 3
输出
1 2 3 4 3 5

输入
4 3
1 2
1 3
1 4
输出
No
*/