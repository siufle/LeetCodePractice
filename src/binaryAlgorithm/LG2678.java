/**
 * 洛谷2678. 跳石头
 */

package binaryAlgorithm;

import java.util.Scanner;

public class LG2678 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n + 2];
        arr[0] = 0;
        arr[n + 1] = s;
        for(int i = 1; i <= n; i++){
            arr[i] = sc.nextInt();
        }
        int l = 1, r = s;
        int ans = 0;
        while(l <= r){
            int mid = l + (r - l)/2;
            if(check(mid,arr,m)){
                ans = mid;
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        System.out.println(ans);
    }

    private static boolean check(int gap, int[] arr, int m){
        int now = 0, next = 1, sum = 0;
        while(next < arr.length){
            if(arr[next] - arr[now] < gap){
                sum++;
                next++;
            }else{
                now = next;
                next++;
            }
        }
        return sum <= m;
    }
}

/*
输入：
25 5 2
2
11
14
17
21
输出：
4
*/