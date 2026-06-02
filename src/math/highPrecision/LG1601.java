/*
洛谷 1601. 高精度加法
给定两个非负整数 a,b，求它们的和。不用考虑负数。

*/

package math.highPrecision;

import java.util.Scanner;

public class LG1601 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        int la = a.length();
        String b = sc.next();
        int lb = b.length();
        int lc = Math.max(la, lb);
        int[] arrA = new int[lc];
        for(int i = 0; i < lc; i++){
            if(i < la){
                arrA[i] = a.charAt(la - i - 1) - '0';
            } else {
                arrA[i] = 0;
            }
        }
        int[] arrB = new int[lc];
        for(int i = 0; i < lc; i++){
            if(i < lb){
                arrB[i] = b.charAt(lb - i - 1) - '0';
            } else {
                arrB[i] = 0;
            }
        }
        int[] arrC = new int[lc + 1];
        for(int i = 0; i < lc; i++){
            arrC[i] += arrA[i] + arrB[i];
            if(arrC[i] >= 10){
                arrC[i + 1]++;
                arrC[i] -= 10;
            }
        }
        while (lc > 0 && arrC[lc] == 0) lc--;
        StringBuilder sb = new StringBuilder();
        for(int i = lc; i >= 0; i--){
            sb.append(arrC[i]);
        }
        System.out.println(sb);
    }
}
