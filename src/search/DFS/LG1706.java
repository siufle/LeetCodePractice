/*洛谷1706. 全排列问题
题目描述
按照字典序输出自然数 1 到 n 所有不重复的排列，即 n 的全排列，要求所产生的任一数字序列中不允许出现重复的数字。

输入格式
一个整数n

输出格式
由 1∼n 组成的所有不重复的数字序列，每行一个序列。
每个数字保留 5 个场宽。
1 ≤ n ≤ 9
*/

package search.DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class LG1706 {

    static boolean[] used = new boolean[10];
    static int[] ans = new int[10];
    static StringBuilder sb = new StringBuilder(1 << 16);
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dfs(1, n);
        if (sb.length() > 0) {
            bw.write(sb.toString());
        }
        bw.flush();
    }

    private static void dfs(int x, int n) throws IOException {
        if (x > n) {
            for (int i = 1; i <= n; i++) {
                sb.append("    ").append(ans[i]);
            }
            sb.append('\n');
            if (sb.length() >= 1 << 20) {
                bw.write(sb.toString());
                sb.setLength(0);
            }
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                ans[x] = i;
                used[i] = true;
                dfs(x + 1, n);
                used[i] = false;
            }
        }
    }

}

/*
输入
3

输出
    1    2    3
    1    3    2
    2    1    3
    2    3    1
    3    1    2
    3    2    1

*/