/**
 * 洛谷1003. 铺地毯
 */

package baseDataStruct.enumeration;

import java.util.Scanner;

public class LG1003 {
    public static void main(String[] args) {
        //地毯数
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        int[] g = new int[n + 1];
        int[] k = new int[n + 1];
        int flag = 0;
        for(int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
            g[i] = sc.nextInt();
            k[i] = sc.nextInt();
        }
        int x = sc.nextInt();
        int y = sc.nextInt();
        for(int i = n; i >= 1; i--) {
            if(a[i] <= x && x <= a[i] + g[i] && b[i] <= y && y <= b[i] + k[i]) {
                System.out.println(i);
                flag = 1;
                break;
            }
        }
        if(flag == 0) {
            System.out.println(-1);
        }
    }
}

/*
输入
3
1 0 2 3
0 2 3 3
2 1 3 3
2 2
输出
3

输入
3
1 0 2 3
0 2 3 3
2 1 3 3
4 5
输出
-1
*/
