/*洛谷4387. 验证栈序列
给出两个序列 pushed 和 poped 两个序列，其取值从 1 到 n(n≤100000)。
已知入栈序列是 pushed，如果出栈序列有可能是 poped，则输出 Yes，否则输出 No。
请注意，给定的序列一定是一个排列（即没有重复数字）。

输入格式：
第一行一个整数 q，询问次数。
接下来 q 个询问，对于每个询问：
第一行一个整数 n 表示序列长度；
第二行 n 个整数表示入栈序列；
第三行 n 个整数表示出栈序列；
*/

package baseDataStruct.stack;

import java.io.BufferedInputStream;
import java.io.IOException;

public class LG4387 {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int q = sc.nextInt();
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < q; i++) {
            int n = sc.nextInt();
            int[] pushed = new int[n];

            for (int j = 0; j < n; j++) {
                pushed[j] = sc.nextInt();
            }

            int top = 0;
            int pushIndex = 0;
            boolean valid = true;
            for (int j = 0; j < n; j++) {
                int target = sc.nextInt();

                while (pushIndex < n && (top == 0 || pushed[top - 1] != target)) {
                    pushed[top++] = pushed[pushIndex++];
                }
                if (top == 0 || pushed[top - 1] != target) {
                    valid = false;
                } else {
                    top--;
                }
            }

            answer.append(valid ? "Yes" : "No").append('\n');
        }
        System.out.print(answer);
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
2
5
1 2 3 4 5
5 4 3 2 1
4
1 2 3 4
2 4 1 3

输出
Yes
No
*/
