/*洛谷3370. 字符串哈希
https://www.luogu.com.cn/problem/P3370

题目描述
如题，给定 N 个字符串（第 i 个字符串长度为 Mi，字符串内包含数字、大小写字母，大小写敏感），
请求出 N 个字符串中共有多少个不同的字符串。

输入格式
第一行包含一个整数 N，为字符串的个数。
接下来 N 行每行包含一个字符串，为所提供的字符串。

输出格式
输出包含一行，包含一个整数，为不同的字符串个数。

数据范围
对于 30% 的数据：N≤10，Mi≈6，Mmax≤15
对于 70% 的数据：N≤1000，Mi≈100，Mmax ≤150。
对于 100% 的数据：N≤10000，Mi≈1000，Mmax≤1500。
*/

package hash;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LG3370 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] h = new long[n];
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            h[i] = hash(s);
        }
        Arrays.sort(h);
        int ans = 1;
        for (int i = 1; i < n; i++) {
            if (h[i] != h[i - 1]) ans++;
        }
        System.out.println(ans);
    }
    private static long hash(String s) {
        int len = s.length();
        long hash = s.charAt(0);
        for (int i = 1; i < len; i++) {
            hash = hash * 131 + s.charAt(i);
        }
        return hash;
    }
}

/*
输入
5
abc
aaaa
abc
abcc
12345

输出
4
*/