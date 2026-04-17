/**
 * 洛谷1873: 砍树
 */

package binaryAlgorithm;

import java.util.Scanner;

public class LG1873 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        long l = 0, r = 0;
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
            r = Math.max(r, arr[i]);
        }
        long ans = 0;
        while(l <= r){
            long mid = (l+r)/2;
            if(check(mid,arr,m)){
                ans = mid;
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        System.out.println(ans);
    }
    private static boolean check(long h, int[] arr, int m){
        long res = 0;
        for (int j : arr) {
            if (j - h > 0) {
                res += j - h;
            }
        }
        return res >= m;
    }
}

/*
输入：
4 7
20 15 10 17
输出：
15

输入：
5 20
4 42 40 26 46
输出：
36
*/
