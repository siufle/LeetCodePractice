/*洛谷2024. 食物链
题目描述
动物王国中有三类动物 A,B,C，这三类动物的食物链构成了有趣的环形。A 吃 B，B 吃 C，C 吃 A。
现有 N 个动物，以 1∼N 编号。每个动物都是 A,B,C 中的一种，但是我们并不知道它到底是哪一种。

有人用两种说法对这 N 个动物所构成的食物链关系进行描述：
第一种说法是 1 X Y，表示 X 和 Y 是同类。
第二种说法是 2 X Y，表示 X 吃 Y。
此人对 N 个动物，用上述两种说法，一句接一句地说出 K 句话，这 K 句话有的是真的，有的是假的。

当一句话满足下列三条之一时，这句话就是假话，否则就是真话。
当前的话与前面的某些真的话冲突，就是假话；
当前的话中 X 或 Y 比 N 大，就是假话；
当前的话表示 X 吃 X，就是假话。

你的任务是根据给定的 N 和 K 句话，输出假话的总数。

输入格式
第一行两个整数，N,K，表示有 N 个动物，K 句话。
第二行开始每行一句话。格式见题目描述与样例。

输出格式
一行，一个整数，表示假话的总数。
*/

package baseDataStruct.disjointSetUnion;

import java.io.BufferedInputStream;
import java.io.IOException;

public class LG2024 {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] parent = new int[3 * n + 1];
        for(int i = 1; i <= 3 * n; i++) {
            parent[i] = i;
        }
        int res = 0;
        for(int i = 0; i < k; i++) {
            int type = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            if((x > n || y > n) || (type == 2 && x == y)) {
                res++;
                continue;
            }
            int rootX = find(parent, x);
            int rootY = find(parent, y);
            int rootXD = find(parent, x + n);
            int rootYD = find(parent, y + n);
            int rootXS = find(parent, x + 2 * n);
            int rootYS = find(parent, y + 2 * n);
            if(type == 1) {
                if(rootX == rootYD || rootX == rootYS) {
                    res++;
                    continue;
                }
                parent[rootX] = rootY;
                parent[rootXD] = rootYD;
                parent[rootXS] = rootYS;
            }
            if (type == 2) {
                if (rootX == rootY || rootX == rootYS) {
                    res++;
                    continue;
                }
                parent[rootX] = rootYD;
                parent[rootXS] = rootY;
                parent[rootXD] = rootYS;
            }
        }
        System.out.println(res);
    }
    private static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
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
100 7
1 101 1
2 1 2
2 2 3
2 3 3
1 1 3
2 3 1
1 5 5

输出
3
*/
