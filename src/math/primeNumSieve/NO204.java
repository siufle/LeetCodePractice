/*
204. 计数质数
给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。

示例 1：
输入：n = 10
输出：4
解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。

示例 2：
输入：n = 0
输出：0

示例 3：
输入：n = 1
输出：0

提示：
0 <= n <= 5 * 10^6
*/

package math.primeNumSieve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NO204 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 2;
        System.out.println(solution.countPrimes_o(n));
    }
    static class Solution {
        // 埃氏筛
        public int countPrimes_a(int n) {
            List<Integer> primes = new ArrayList<>();
            boolean[] isPrime = new boolean[n + 1];
            Arrays.fill(isPrime, true);
            isPrime[0] = isPrime[1] = false;
            for (int i = 2; i < n; i++) {
                if(isPrime[i]){
                    primes.add(i);
                    for(int j = 2; j <= n / i; j++){
                        int temple = i * j;
                        isPrime[temple] = false;
                    }
                }
            }
            primes.forEach(System.out::println);
            return primes.size();
        }

        // 欧拉筛
        public int countPrimes_o(int n) {
            List<Integer> primes = new ArrayList<>();
            boolean[] isPrime = new boolean[n + 1];
            Arrays.fill(isPrime, true);
            if(n < 2) return 0;
            isPrime[0] = isPrime[1] = false;
            for (int i = 2; i < n; i++) {
                if(isPrime[i]){
                    primes.add(i);
                }
                for(int p: primes){
                    int temple = i * p;
                    if(temple > n) break;
                    isPrime[temple] = false;
                    if(i % p == 0) break;
                }
            }
            primes.forEach(System.out::println);
            return primes.size();
        }
    }
}
