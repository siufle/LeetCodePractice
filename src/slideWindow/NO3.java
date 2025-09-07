package slideWindow;

public class NO3 {

    public static void main(String[] args) {
        String s = "abcabcbb";
        Solution solution = new Solution();
        int i = solution.lengthOfLongestSubstring(s);
        System.out.println(i);
    }
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            char[] chs = s.toCharArray();
            int n = chs.length;
            int ans = 0;
            int left = 0;
            int[] cnt = new int[128];
            for(int right = 0; right < n; right++){
                char c = chs[right];
                cnt[c]++;
                while(cnt[c] > 1){
                    cnt[chs[left]]--;
                    left++;
                }
                ans = Math.max(ans,right - left + 1);
            }
            return ans;
        }
    }
}


