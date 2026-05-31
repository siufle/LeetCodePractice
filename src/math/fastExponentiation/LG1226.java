/*
洛谷 1226. 快速幂
输入 a b p 输出 a^b mod p
*/

package math.fastExponentiation;

import java.util.Scanner;

public class LG1226 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int p = in.nextInt();
        long x = a;
        long y = b;
        long ans = 1;
        while(y > 0){
            if(y % 2 == 1){
                ans = ans * x % p;
            }
            x = x * x % p;
            y /= 2;
        }
        System.out.println(a + "^" + b + " mod " + p + "=" + ans);
    }
}

/*
输入：
2 10 9
输出：
2^10 mod 9=7
*/
