/*洛谷4995 跳跳
你是一只小跳蛙，你特别擅长在各种地方跳来跳去。
这一天，你和朋友小 F 一起出去玩耍的时候，遇到了一堆高矮不同的石头，其中第 i 块的石头高度为 hi
地面的高度是 h0=0。你估计着，从第 i 块石头跳到第 j 块石头上耗费的体力值为 (hi−hj)^2
从地面跳到第 i 块石头耗费的体力值是 (hi)^2
为了给小 F 展现你超级跳的本领，你决定跳到每个石头上各一次，并最终停在任意一块石头上，并且小跳蛙想耗费尽可能多的体力值。
同时，你不能从石头上跳到地上。
当然，你只是一只小跳蛙，你只会跳，不知道怎么跳才能让本领更充分地展现。
不过你有救啦！小 F 给你递来了一个写着 AK 的电脑，你可以使用计算机程序帮你解决这个问题，万能的计算机会告诉你怎么跳。
那就请你——会写代码的小跳蛙——写下这个程序，为你 NOIp AK 踏出坚实的一步吧

输入格式
输入一行一个正整数 n，表示石头个数。
输入第二行 n 个正整数，表示第 i 块石头的高度 hi

输出格式
输出一行一个正整数，表示你可以耗费的体力值的最大值。
*/

package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LG4995 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] h = new int[n + 1];
        h[0] = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            h[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(h);
        long ans = 0;
        int l = 0, r = n;
        int count = 0;
        while(l < r){
            ans += (long) (h[l] - h[r]) * (h[l] - h[r]);
            if(count % 2 == 0){
                l++;
            }else{
                r--;
            }
            count++;
        }
        System.out.println(ans);
    }
}

/*
输入
2
2 1
输出
5

输入
3
6 3 5
输出
49
*/
