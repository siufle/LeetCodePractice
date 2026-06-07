/*43. 字符串相乘
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。

示例 1:
输入: num1 = "2", num2 = "3"
输出: "6"

示例 2:
输入: num1 = "123", num2 = "456"
输出: "56088"

提示：
1 <= num1.length, num2.length <= 200
num1 和 num2 只能由数字组成。
num1 和 num2 都不包含任何前导零，除了数字0本身。
*/

package math.highPrecision;

public class NO43 {
    public static void main(String[] args) {
        String num1 = "2";
        String num2 = "3";
        Solution solution = new Solution();
        System.out.println(solution.multiply(num1, num2));
    }
    static class Solution {
        public String multiply(String num1, String num2) {
            int m = num1.length(), n = num2.length();
            int[] num1Arr = new int[m];
            for(int i = 0; i < m; i++){
                num1Arr[i] = num1.charAt(m - 1 - i) - '0';
            }
            int[] num2Arr = new int[n];
            for (int i = 0; i < n; i++){
                num2Arr[i] = num2.charAt(n - 1 - i) - '0';
            }
            int lc = n + m + 1;
            int[] pos = new int[lc];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    pos[i + j] += num1Arr[i] * num2Arr[j];
                }
            }
            for(int i = 0; i < lc - 1; i++){
                int p = pos[i] / 10;
                pos[i] %= 10;
                pos[i + 1] += p;
            }
            while(pos[lc - 1] == 0 && lc > 1){
                lc--;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = lc - 1; i >= 0; i--){
                sb.append(pos[i]);
            }
            return sb.toString();
        }
    }
}
