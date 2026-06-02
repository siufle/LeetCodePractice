/*
洛谷 2142. 高精度减法
*/

package math.highPrecision;

import java.math.BigInteger;
import java.util.Scanner;

public class LG2142 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        BigInteger A = new BigInteger(a);
        BigInteger B = new BigInteger(b);
        System.out.println(A.subtract(B));
    }
}
