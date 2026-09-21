/*洛谷1464. function
题目描述
对于一个递归函数 w(a,b,c)
如果 a≤0 或 b≤0 或 c≤0 就返回值 1。
如果 a>20 或 b>20 或 c>20 就返回 w(20,20,20)
如果 a<b 并且 b<c 就返回 w(a,b,c−1)+w(a,b−1,c−1)−w(a,b−1,c)。
其它的情况就返回 w(a−1,b,c)+w(a−1,b−1,c)+w(a−1,b,c−1)−w(a−1,b−1,c−1)
这是个简单的递归函数，但实现起来可能会有些问题。当 a,b,c 均为 15 时，调用的次数将非常的多。你要想个办法才行。
注意：例如 w(30,−1,0) 又满足条件 1 又满足条件 2，请按照最上面的条件来算，答案为 1。

输入格式
会有若干行。
并以 −1,−1,−1 结束。

输出格式
输出若干行，每一行格式：
w(a, b, c) = ans
注意空格。
*/

package search.DFS;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

public class LG1464 {

    static long a, b, c;

    static long[][][] memo = new long[21][21][21];
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        while (true) {
            a = sc.nextLong();
            b = sc.nextLong();
            c = sc.nextLong();
            if (a == -1 && b == -1 && c == -1) {
                break;
            }
            System.out.println("w(" + a + ", " + b + ", " + c + ") = " + w(a, b, c));
        }
    }

    private static long w(long a, long b, long c) {
        if (a <= 0 || b <= 0 || c <= 0) return 1;
        if (a > 20 || b > 20 || c > 20) {
            if (memo[20][20][20] == -1) memo[20][20][20] = w(20, 20, 20);
            return memo[20][20][20];
        }
        int ai = (int) a, bi = (int) b, ci = (int) c;
        if (ai < bi && bi < ci) {
            if (memo[ai][bi][ci - 1] == -1) memo[ai][bi][ci - 1] = w(ai, bi, ci - 1);
            if (memo[ai][bi - 1][ci - 1] == -1) memo[ai][bi - 1][ci - 1] = w(ai, bi - 1, ci - 1);
            if (memo[ai][bi - 1][ci] == -1) memo[ai][bi - 1][ci] = w(ai, bi - 1, ci);
            return memo[ai][bi][ci - 1] + memo[ai][bi - 1][ci - 1] - memo[ai][bi - 1][ci];
        }
        else {
            if (memo[ai - 1][bi][ci] == -1) memo[ai - 1][bi][ci] = w(ai - 1, bi, ci);
            if (memo[ai - 1][bi - 1][ci] == -1) memo[ai - 1][bi - 1][ci] = w(ai - 1, bi - 1, ci);
            if (memo[ai - 1][bi][ci - 1] == -1) memo[ai - 1][bi][ci - 1] = w(ai - 1, bi, ci - 1);
            if (memo[ai - 1][bi - 1][ci - 1] == -1) memo[ai - 1][bi - 1][ci - 1] = w(ai - 1, bi - 1, ci - 1);
            return memo[ai - 1][bi][ci] + memo[ai - 1][bi - 1][ci] + memo[ai - 1][bi][ci - 1] - memo[ai - 1][bi - 1][ci - 1];
        }
    }

    private static class FastScanner {
        private final BufferedInputStream input = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int pointer;
        private int length;

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long value = 0;
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
1 1 1
2 2 2
-1 -1 -1

输出
w(1, 1, 1) = 2
w(2, 2, 2) = 4
*/