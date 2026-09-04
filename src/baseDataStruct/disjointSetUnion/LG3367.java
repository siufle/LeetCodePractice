/*洛谷3367. 并查集
题目描述
现在有一个并查集，你需要完成合并和查询操作。

输入格式
第一行包含两个整数 N,M ,表示共有 N 个元素和 M 个操作。
接下来 M 行，每行包含三个整数 Zi, Xi, Yi
当 Zi=1 时，将 Xi与 Yi所在的集合合并。
当 Zi=2 时，输出 Xi与 Yi是否在同一集合内，是的输出 Y ；否则输出 N 。

输出格式
对于每一个 Zi=2 的操作，都有一行输出，每行包含一个大写字母，为 Y 或者 N 。

数据范围
1≤N≤2×10^5，1≤M≤10^6，1≤Xi,Yi≤N，Zi∈{1,2}。
*/

package baseDataStruct.disjointSetUnion;

import java.util.Scanner;

public class LG3367 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] father = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            father[i] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int z = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            int fx = findFather(father, x);
            int fy = findFather(father, y);
            if(z == 1){
                // 合并
                father[fx] = fy;
            } else {
                // 查询
                if(fx == fy){
                    sb.append("Y").append("\n");
                } else {
                    sb.append("N").append("\n");
                }
            }
        }
        System.out.println(sb);
    }
    private static int findFather(int[] father, int x) {
        if(father[x] == x) return x;
        return father[x] = findFather(father, father[x]);
    }
}

/*
输入
4 7
2 1 2
1 1 2
2 1 2
1 3 4
2 1 4
1 2 3
2 1 4

输出
N
Y
N
Y
*/
