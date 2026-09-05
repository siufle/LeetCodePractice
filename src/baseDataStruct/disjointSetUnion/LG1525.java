/*洛谷1525. 关押罪犯
题目描述
S 城现有两座监狱，一共关押着 N 名罪犯，编号分别为 1∼N。他们之间的关系自然也极不和谐。
很多罪犯之间甚至积怨已久，如果客观条件具备则随时可能爆发冲突。
我们用“怨气值”（一个正整数值）来表示某两名罪犯之间的仇恨程度，怨气值越大，则这两名罪犯之间的积怨越多。
如果两名怨气值为 c 的罪犯被关押在同一监狱，他们俩之间会发生摩擦，并造成影响力为 c 的冲突事件。
每年年末，警察局会将本年内监狱中的所有冲突事件按影响力从大到小排成一个列表，然后上报到 S 城 Z 市长那里。
公务繁忙的 Z 市长只会去看列表中的第一个事件的影响力，如果影响很坏，他就会考虑撤换警察局长。
在详细考察了 N 名罪犯间的矛盾关系后，警察局长觉得压力巨大。
他准备将罪犯们在两座监狱内重新分配，以求产生的冲突事件影响力都较小，从而保住自己的乌纱帽。
假设只要处于同一监狱内的某两个罪犯间有仇恨，那么他们一定会在每年的某个时候发生摩擦。
那么，应如何分配罪犯，才能使 Z 市长看到的那个冲突事件的影响力最小？这个最小值是多少？

输入格式
每行中两个数之间用一个空格隔开。第一行为两个正整数 N,M，分别表示罪犯的数目以及存在仇恨的罪犯对数。
接下来的 M 行每行为三个正整数 aj ,bj ,cj ，表示 aj号和 bj号罪犯之间存在仇恨，其怨气值为 cj
数据保证 1≤aj<bj≤N，0<cj≤10^9，且每对罪犯组合只出现一次。

输出格式
共一行，为 Z 市长看到的那个冲突事件的影响力。如果本年内监狱中未发生任何冲突事件，请输出 0
*/

package baseDataStruct.disjointSetUnion;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

public class LG1525 {

    static class Criminal {
        int a;
        int b;
        int c;

        public int getA() {
            return a;
        }
        public int getB() {
            return b;
        }
        public int getC() {
            return c;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        if (n == 0 || m == 0) {
            System.out.println(0);
            return;
        }
        int[] f = new int[2 * n + 1];
        for(int i = 1; i < 2 * n + 1; i++) {
            f[i] = i;
        }
        Criminal[] crime = new Criminal[m];
        for(int i = 0; i < m; i++){
            crime[i] = new Criminal();
            crime[i].a = sc.nextInt();
            crime[i].b = sc.nextInt();
            crime[i].c = sc.nextInt();
        }
        Arrays.sort(crime, (a, b) -> b.c - a.c);
        for (int i = 0; i < m; i++) {
            int fa = find(f, crime[i].a);
            int fb = find(f, crime[i].b);
            int fad = find(f, crime[i].a + n);
            int fbd = find(f, crime[i].b + n);
            if(fa == fb){
                System.out.println(crime[i].c);
                return;
            }
            f[fa] = fbd;
            f[fb] = fad;
        }
        System.out.println(0);
    }

    private static int find(int[] f, int x) {
        if (f[x] == x) return x;
        return f[x] = find(f, f[x]);
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
4 6
1 4 2534
2 3 3512
1 2 28351
1 3 6618
2 4 1805
3 4 12884

输出
3512
*/