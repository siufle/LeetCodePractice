package slideWindow;

/*2516.每种字符至少取k个
给你一个由字符 'a'、'b'、'c' 组成的字符串 s 和一个非负整数 k 。每分钟，你可以选择取走 s 最左侧 还是 最右侧 的那个字符。
你必须取走每种字符 至少 k 个，返回需要的 最少 分钟数；如果无法取到，则返回 -1 。

示例 1：
输入：s = "aabaaaacaabc", k = 2
输出：8
解释：
从 s 的左侧取三个字符，现在共取到两个字符 'a' 、一个字符 'b' 。
从 s 的右侧取五个字符，现在共取到四个字符 'a' 、两个字符 'b' 和两个字符 'c' 。
共需要 3 + 5 = 8 分钟。
可以证明需要的最少分钟数是 8 。

示例 2：
输入：s = "a", k = 1
输出：-1
解释：无法取到一个字符 'b' 或者 'c'，所以返回 -1 。*/

public class NO2516 {
    public static void main(String[] args) {
        String s = "bcbaab";
        int k = 1;
        Solution solution = new Solution();
        System.out.println(solution.takeCharacters(s,k));
    }
    static class Solution {
        public int takeCharacters(String s, int k) {
            char[] arr = s.toCharArray();
            int maxCount = 0;
            int[] cha = {0,0,0};
            for (char c : arr) {
                if (c == 'a') {
                    cha[0]++;
                } else if (c == 'b') {
                    cha[1]++;
                } else {
                    cha[2]++;
                }
            }
            if(cha[0] < k || cha[1] < k || cha[2] < k)
            {
                return -1;
            }
            int left = 0;
            for(int right = 0; right < arr.length; right++) {
                char c = arr[right];
                if (c == 'a') {
                    cha[0]--;
                }else if (c == 'b') {
                    cha[1]--;
                }else{
                    cha[2]--;
                }
                while(cha[0] < k || cha[1] < k || cha[2] < k) {
                    char temp = arr[left];
                    if (temp == 'a') {
                        cha[0]++;
                    }else if (temp == 'b') {
                        cha[1]++;
                    }else{
                        cha[2]++;
                    }
                    left++;
                }
                maxCount = Math.max(maxCount, right - left + 1);
            }
            return arr.length - maxCount;
        }
    }
}
