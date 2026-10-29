/*洛谷4170. 涂色
https://www.luogu.com.cn/problem/P4170

题目描述
假设你有一条长度为 n 的木板，初始时没有涂过任何颜色。
你希望把它的 n 个单位长度涂上目标颜色，用一个长度为 n 的字符串表示这个目标。
每次你可以把一段连续的木板涂成一个给定的颜色，后涂的颜色覆盖先涂的颜色。
例如，对于目标字符串 RGBGR ，
一种涂色方式为：第一次把木板涂成 RRRRR ，第二次涂成 RGGGR ，第三次涂成 RGBGR ，达到目标。
用尽量少的涂色次数达到目标。

输入格式
输入仅一行，包含一个长度为 n 的字符串，即涂色目标。
字符串中的每个字符都是一个大写字母，不同的字母代表不同颜色，相同的字母代表相同颜色。

输出格式
仅一行，包含一个数，即最少的涂色次数

数据范围
40% 的数据满足 1≤n≤10。
100% 的数据满足 1≤n≤50。
*/

package dynamicProgram;

import java.util.Scanner;

public class LG4170 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = len;
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = Math.min(dp[i + 1][j],dp[i][j - 1]);
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j],dp[i][k] + dp[k + 1][j]);
                }
            }
        }
        System.out.println(dp[0][n - 1]);
    }
}

/*
输入
AAAAA
输出
1

输入
RGBGR
输出
3
*/