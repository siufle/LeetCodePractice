/*洛谷2758. 编辑距离
https://www.luogu.com.cn/problem/P2758

题目描述
设 A 和 B 是两个字符串。我们要用最少的字符操作次数，将字符串 A 转换为字符串 B。
这里所说的字符操作共有三种：
1.删除一个字符；
2.插入一个字符；
3.将一个字符改为另一个字符。
A,B 均只包含小写字母。

输入格式
第一行为字符串 A；第二行为字符串 B；字符串 A,B 的长度均小于 2000。

输出格式
只有一个正整数，为最少字符操作次数。

数据范围
对于 100% 的数据，1≤∣A∣,∣B∣≤2000。
*/

package dynamicProgram;

import java.util.Scanner;

public class LG2758 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        String B = sc.nextLine();
        int n = A.length();
        int m = B.length();
        A = " " + A;
        B = " " + B;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = i;
        for (int j = 0; j <= m; j++)
            dp[0][j] = j;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j],dp[i][j - 1]),dp[i - 1][j - 1]) + 1;
                }
            }
        }
        System.out.println(dp[n][m]);
    }
}

/*
输入
sfdqxbw
gfdgw

输出
4
*/