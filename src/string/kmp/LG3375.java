/*洛谷3375. KMP
https://www.luogu.com.cn/problem/P3375

题目描述
给出两个字符串s1和s2，若s1的区间 [l,r] 子串与s2完全相同，则称s2在s1中出现了，其出现位置为 l。
现在请你求出 s2 在 s1中所有出现的位置。
定义一个字符串 s 的 border 为 s 的一个非 s 本身的子串 t，满足 t 既是 s 的前缀，又是 s 的后缀。
对于 s2，你还需要求出对于其每个前缀 s 的最长 border t 的长度。

输入格式
第一行为一个字符串，即为s1
第二行为一个字符串，即为s2

输出格式
首先输出若干行，每行一个整数，按从小到大的顺序输出s2在s1中出现的位置。
最后一行输出 ∣s2∣ 个整数，第 i 个整数表示s2的长度为 i 的前缀的最长 border 长度。

数据范围
对于全部的测试点，保证 1≤∣s1∣,∣s2∣≤10^6，s1,s2中均只含大写英文字母。
*/

package string.kmp;

import java.util.Scanner;

public class LG3375 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        int l1 = s1.length();
        int l2 = s2.length();
        int[] next = new int[l2 + 1];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j + 1 <= l2) {
            if (k == -1 || s2.charAt(j) == s2.charAt(k)) {
                j++;
                k++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        int i = 0;
        j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < l1) {
            if (j == -1 || s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            if (j == l2) {
                sb.append(i - l2 + 1).append("\n");
                j = next[j];
            }
        }
        for (i = 1; i <= l2; i++) {
            sb.append(next[i]).append(" ");
        }
        System.out.println(sb);
    }
}

/*
输入
ABABABC
ABA

输出
1
3
0 0 1
*/