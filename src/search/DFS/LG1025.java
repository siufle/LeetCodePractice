/*洛谷1025. 数的划分
题目描述
将整数 n 分成 k 份，且每份不能为空，任意两个方案不相同（不考虑顺序）。
例如：n=7，k=3，下面三种分法被认为是相同的。
1,1,5；
1,5,1；
5,1,1。
问有多少种不同的分法。

输入格式
n,k（6<n≤200,2≤k≤6）。

输出格式
1 个整数，即不同的分法。
*/

package search.DFS;

import java.util.Scanner;

public class LG1025 {

    static int ans = 0;
    static int n, k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        dfs(1, 1, 0);
        System.out.println(ans);
    }
    private static void dfs(int last, int step, int sum) {
        if (step > k) {
            if (sum == n) {
                ans++;
            }
            return;
        }
        for (int i = last; i <= n - k + 1; i++) {
            if (sum + i * (k - step + 1) > n) break;
            dfs(i, step + 1, sum + i);
        }
    }
}

/*
输入
7 3

输出
4
*/