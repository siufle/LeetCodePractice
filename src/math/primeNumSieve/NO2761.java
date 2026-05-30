/*2761. 和等于目标值的质数对
给你一个整数 n 。如果两个整数 x 和 y 满足下述条件，则认为二者形成一个质数对：
1 <= x <= y <= n
x + y == n
x 和 y 都是质数
请你以二维有序列表的形式返回符合题目要求的所有 [xi, yi] ，
列表需要按 xi 的 非递减顺序 排序。如果不存在符合要求的质数对，则返回一个空数组。
注意：质数是大于 1 的自然数，并且只有两个因子，即它本身和 1 。

示例 1：
输入：n = 10
输出：[[3,7],[5,5]]
解释：在这个例子中，存在满足条件的两个质数对。
这两个质数对分别是 [3,7] 和 [5,5]，按照题面描述中的方式排序后返回。

示例 2：
输入：n = 2
输出：[]
解释：可以证明不存在和为 2 的质数对，所以返回一个空数组。

提示：
1 <= n <= 10^6
*/

package math.primeNumSieve;

import java.util.*;

public class NO2761 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 36;
        solution.findPrimePairs(n).forEach(System.out::println);
    }
    static class Solution {
        public List<List<Integer>> findPrimePairs(int n) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> prime = new ArrayList<>();
            boolean[] isPrime = new boolean[n + 1];
            Arrays.fill(isPrime, true);
            isPrime[0] = isPrime[1] = false;
            for (int i = 2; i <= n; i++) {
                if(isPrime[i]){
                    prime.add(i);
                }
                for(int p: prime){
                    int temple = i * p;
                    if(temple > n) break;
                    isPrime[temple] = false;
                    if(i % p == 0) break;
                }
            }
            for(int i = 2; i <= n/2; i++){
                if(isPrime[i] && isPrime[n - i]){
                    ans.add(Arrays.asList(i, n - i));
                }
            }
            return ans;
        }
    }
}
