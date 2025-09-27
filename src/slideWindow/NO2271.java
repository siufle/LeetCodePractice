package slideWindow;

/*2271.毯子覆盖的最多白色砖块数
给你一个二维整数数组 tiles ，其中 tiles[i] = [li, ri] ，
表示所有在 li <= j <= ri 之间的每个瓷砖位置 j 都被涂成了白色。
同时给你一个整数 carpetLen ，表示可以放在 任何位置 的一块毯子的长度。
请你返回使用这块毯子，最多 可以盖住多少块瓷砖。

示例 1：
输入：tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
输出：9
解释：将毯子从瓷砖 10 开始放置。
总共覆盖 9 块瓷砖，所以返回 9 。
注意可能有其他方案也可以覆盖 9 块瓷砖。
可以看出，瓷砖无法覆盖超过 9 块瓷砖。

示例 2：
输入：tiles = [[10,11],[1,1]], carpetLen = 2
输出：2
解释：将毯子从瓷砖 10 开始放置。
总共覆盖 2 块瓷砖，所以我们返回 2 。*/


import java.util.Arrays;

public class NO2271 {
    public static void main(String[] args) {
        int[][] tiles = {{10,11}, {1,1}};
        int carpetLen = 2;
        Solution solution = new Solution();
        System.out.println(solution.maximumWhiteTiles(tiles, carpetLen));
    }
    static class Solution {
        public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
            Arrays.sort(tiles, (x , y) -> x[0] - y[0]);
            int left = 0;
            int ans = 0;
            int cover = 0;
            for(int right = 0; right < tiles.length; right++) {
                int tl = tiles[right][0];
                int tr = tiles[right][1];
                int n = tr - tl + 1;
                cover += n;
                if(n >= carpetLen){
                    ans = carpetLen;
                    break;
                }
                while(tr - tiles[left][1] > carpetLen){
                    cover -= tiles[left][1] - tiles[left][0] + 1;
                    left++;
                }
                int carpetLeft = tr - carpetLen + 1;
                int uncover = Math.max(carpetLeft - tiles[left][0],0);
                ans = Math.max(ans, cover - uncover);
            }
            return ans;
        }
    }
}
