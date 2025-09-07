package slideWindow;

/*
3090.每个字符最多出现两次的最长子字符串
给你一个字符串 s ，请找出满足每个字符最多出现两次的最长子字符串，并返回该子字符串的最大长度。

示例 1：
输入： s = "bcbbbcba"
输出： 4
解释：
以下子字符串长度为 4，并且每个字符最多出现两次："bcbbbcba"。

示例 2：
输入： s = "aaaa"
输出： 2
解释：
以下子字符串长度为 2，并且每个字符最多出现两次："aaaa"。
*/

import java.util.HashMap;
import java.util.Map;

public class NO3090 {
    public static void main(String[] args) {
        String s1 = "bcbbbcba";
        String s2 = "aaaa";
        String s3 = "acedc";
        Solution solution = new Solution();
        int maxLen1 = solution.maximumLengthSubstring(s1);
        int maxLen2 = solution.maximumLengthSubstring(s2);
        //int maxLen3 = solution.maximumLengthSubstring(s3);
        System.out.println(maxLen1 + " " + maxLen2);
        //System.out.println(maxLen3);
    }
    static class Solution {
        public int maximumLengthSubstring(String s) {
            char[] chs = s.toCharArray();
            int left = 0;
            int maxLen = 0;
            Map<Character, Integer> map = new HashMap<>();
            for (int right = 0; right < chs.length; right++) {
                char c = chs[right];
                if (map.containsKey(c)) {
                    int temp = map.get(c);
                    map.put(c, temp + 1);
                } else {
                    map.put(c, 1);
                }
                while (map.get(c) > 2) {
                    char c2 = chs[left];
                    int temp = map.get(c2);
                    if(temp == 1){
                        map.remove(c2);
                    }else{
                        map.put(c2, temp - 1);
                    }
                    left++;
                }
                maxLen = Math.max(maxLen, right - left + 1);
            }
            return maxLen;
        }
    }
}

