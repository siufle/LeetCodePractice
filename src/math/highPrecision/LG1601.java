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
        int[] arrA = new int[la + 1];
        arrA[la] = 0;
        for (int i = 0; i < la; i++) {
            arrA[i] = a.charAt(la - 1 - i) - '0';
        }
        String b = sc.next();
        int lb = b.length();
        int[] arrB = new int[lb + 1];
        arrB[lb] = 0;
        for (int i = 0; i < lb; i++) {
            arrB[i] = b.charAt(lb - 1 - i) - '0';
        }
        int lc = Math.max(la, lb);
        int[] arrC = new int[lc + 1];
        for(int i = 0; i < lc; i++){
            arrC[i] += arrA[i] + arrB[i];
            if(arrC[i] >= 10){
                arrC[i + 1]++;
                arrC[i] -= 10;
            }
        }
        do {
            lc--;
        } while (lc > 0 && arrC[lc] == 0);
        for(int i = lc; i >= 0; i--){
            System.out.print(arrC[i]);
        }
    }
}
