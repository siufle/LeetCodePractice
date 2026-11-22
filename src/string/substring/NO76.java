/*76. 最小覆盖子串
https://leetcode.cn/problems/minimum-window-substring/description/?envType=study-plan-v2&envId=top-100-liked

给定两个字符串s和t，长度分别是m和n，返回s中的最短窗口子串，使得该子串包含t中的每一个字符（包括重复字符）
如果没有这样的子串，返回空字符串 ""。
测试用例保证答案唯一。

示例 1：
输入：
String s = "ADOBECODEBANC";
String t = "ABC";
输出："BANC"
解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。

示例 2：
输入：
String s = "a";
String t = "a";
输出："a"
解释：整个字符串 s 是最小覆盖子串。

示例 3:
输入:
String s = "a";
String t = "aa";
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。

提示：
m == s.length
n == t.length
1 <= m, n <= 10^5
s 和 t 由英文字母组成
*/

package string.substring;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NO76 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "a";
        String t = "a";
        System.out.println(solution.minWindow(s, t));
    }

    static class Solution {
        static Map<Character, Integer> ori = new HashMap<>();
        static Map<Character, Integer> cnt = new HashMap<>();
        public String minWindow(String s, String t) {
            int tLen = t.length();
            for (int i = 0; i < tLen; i++) {
                char c = t.charAt(i);
                ori.put(c, ori.getOrDefault(c, 0) + 1);
            }
            int sLen = s.length();
            int len = Integer.MAX_VALUE;
            int ansL = -1, ansR = -1;
            int l = 0, r = -1;
            while (r < sLen) {
                r++;
                if (r < sLen && ori.containsKey(s.charAt(r))) {
                    cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
                }
                while (check() && l <= r) {
                    if (r - l + 1 < len) {
                        len = r - l + 1;
                        ansL = l;
                        ansR = l + len;
                    }
                    if (ori.containsKey(s.charAt(l))) {
                        cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                    }
                    l++;
                }
            }
            return ansL == -1 ? "" : s.substring(ansL, ansR);
        }
        private static boolean check() {
            Iterator iter = ori.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Character key = (Character) entry.getKey();
                Integer val = (Integer) entry.getValue();
                if (cnt.getOrDefault(key, 0) < val) {
                    return false;
                }
            }
            return true;
        }
    }
}
