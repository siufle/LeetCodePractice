/*29. 两数相除
给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。
整数除法应该向零截断，也就是截去（truncate）其小数部分。例如，8.345 将被截断为 8 ，-2.7335 将被截断至 -2 。
返回被除数 dividend 除以除数 divisor 得到的 商 。
注意：假设我们的环境只能存储 32 位 有符号整数，其数值范围是 [−2^31,  2^31 − 1] 。
本题中，如果商 严格大于 2^31 − 1 ，则返回 2^31 − 1 ；如果商 严格小于 -2^31 ，则返回 -2^31 。

示例 1:
输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = 3.33333.. ，向零截断后得到 3 。

示例 2:
输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = -2.33333.. ，向零截断后得到 -2 。

提示：
-2^31 <= dividend, divisor <= 2^31 - 1
divisor != 0

*/

package math.highPrecision;

import java.util.Arrays;

public class NO29 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.divide(10, 3));
    }
    static class Solution {
        public int divide(int dividend, int divisor) {
            long dividendAbs = Math.abs((long) dividend);
            long divisorAbs = Math.abs((long) divisor);
            if (dividendAbs < divisorAbs){
                return 0;
            }
            StringBuilder sb = new StringBuilder();
            if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)){
                sb.append("-");
            }
            String num1 = dividendAbs + "";
            int m = num1.length();
            int[] dividendArr = new int[m + 1];
            dividendArr[0] = m;
            for(int i = 1; i <= m; i++){
                dividendArr[i] = num1.charAt(m - i) - '0';
            }
            String num2 = divisorAbs + "";
            int n = num2.length();
            int[] divisorArr = new int[n + 1];
            divisorArr[0] = n;
            for (int i = 1; i <= n; i++){
                divisorArr[i] = num2.charAt(n - i) - '0';
            }
            int lc = m - n + 1;
            int[] res = new int[lc];
            Arrays.fill(res, 0);
            int[] temp = new int[m + 1];
            int shift = m - n;
            while(shift >= 0){
                Arrays.fill(temp, 0);
                temp[0] = n + shift;
                for(int i = 1; i <= n; i++){
                    temp[i + shift] = divisorArr[i];
                }
                while(check(dividendArr, temp)){
                    sub(dividendArr, temp);
                    while (dividendArr[0] > 1 && dividendArr[dividendArr[0]] == 0){
                        dividendArr[0]--;
                    }
                    res[shift]++;
                }
                shift--;
            }
            while(lc >= 1 && res[lc - 1] == 0){
                lc--;
            }
            for (int i = lc - 1; i >= 0; i--){
                sb.append(res[i]);
            }
            long ans = Long.parseLong(sb.toString());
            if (ans > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (ans < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            return (int) ans;
        }
        private boolean check(int[] dividendArr, int[] divisorArr){
            int m = dividendArr[0], n = divisorArr[0];
            int i = m;
            int j = n;
            if (m < n) return false;
            if (m > n) return true;
            while(i >= 1 && j >= 1){
                if (dividendArr[i] < divisorArr[j]) return false;
                if (dividendArr[i] > divisorArr[j]) return true;
                i--;
                j--;
            }
            return true;
        }
        private void sub(int[] dividendArr, int[] divisorArr){
            for(int i = 1; i <= divisorArr[0]; i++){
                dividendArr[i] -= divisorArr[i];
                if (dividendArr[i] < 0){
                    dividendArr[i + 1]--;
                    dividendArr[i] += 10;
                }
            }
        }
    }
}
