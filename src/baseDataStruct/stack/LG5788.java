/*洛谷5788. 单调栈模板
找出右边第一个大于当前数字的下标

输入格式
第一行一个正整数 n。
第二行 n 个正整数 a1~~an

输出格式
一行 n 个整数表示 f(1),f(2),…,f(n) 的值。
*/

package baseDataStruct.stack;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class LG5788 {
    public static void main(String[] args) throws IOException {
        FastScanner input = new FastScanner();
        int n = input.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = input.nextInt();
        }

        int[] f = new int[n + 1];
        int[] stack = new int[n];
        int top = 0;
        for (int i = n; i >= 1; i--) {
            while (top > 0 && a[i] >= a[stack[top - 1]]) {
                top--;
            }
            f[i] = top == 0 ? 0 : stack[top - 1];
            stack[top++] = i;
        }

        FastOutput output = new FastOutput();
        for (int i = 1; i <= n; i++) {
            output.writeInt(f[i]);
            output.writeByte(i == n ? '\n' : ' ');
        }
        output.flush();
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

    private static class FastOutput {
        private final BufferedOutputStream output = new BufferedOutputStream(System.out);
        private final byte[] numberBuffer = new byte[11];

        void writeInt(int value) throws IOException {
            if (value == 0) {
                output.write('0');
                return;
            }

            int length = 0;
            while (value > 0) {
                numberBuffer[length++] = (byte) ('0' + value % 10);
                value /= 10;
            }
            while (length > 0) {
                output.write(numberBuffer[--length]);
            }
        }

        void writeByte(int value) throws IOException {
            output.write(value);
        }

        void flush() throws IOException {
            output.flush();
        }
    }
}

/*
输入
5
1 4 2 3 5

输出
2 5 4 5 0
*/
