/*洛谷4391. 无限传输
https://www.luogu.com.cn/problem/P4391

题目描述
一家无线电台需要向多位接收者发送一条信息。为了确保所有听众都能接收到，该信息在一个连续的循环中被一遍又一遍地播放。
你将得到其中一位接收者收到的一段字符序列。已知该序列的长度至少与原信息的长度一样长。
你的任务是编写一个程序，提取出电台发送的原信息。
更形式化地说，你的程序需要找到输入序列 S 的最短子序列S′使得 S 本身又是（足够长的）重复序列S′+S′+⋅⋅⋅+S′的子串。

输入格式
第一行包含一个整数 L，即序列 S 的长度。
第二行包含恰好 L 个字符，即序列 S 本身。该序列由小写字母组成。

输出格式
程序应向标准输出写入一行，包含一个整数：信息S′的长度 L′。请注意，L′必须是尽可能小的值。

数据范围
对于全部的测试点，保证 1≤L≤10^6
*/

package string.kmp;

import java.util.Scanner;

public class LG4391 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        int[] next = new int[n + 1];
        next[0] = -1;
        int j = 0, k = -1;
        while (j + 1 <= n) {
            if (k == -1 || s.charAt(j) == s.charAt(k)) {
                j++;
                k++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        System.out.println(n - next[n]);
    }
}

/*
输入
8
cabcabca

输出
3
*/