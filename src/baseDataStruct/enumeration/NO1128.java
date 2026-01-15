/*1128. 等价多米诺骨牌对的数量
给你一组多米诺骨牌 dominoes 。
形式上，dominoes[i] = [a, b] 与 dominoes[j] = [c, d] 等价 当且仅当 (a == c 且 b == d) 或者 (a == d 且 b == c) 。
即一张骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌。
在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。

示例 1：
输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
输出：1

示例 2：
输入：dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
输出：3

提示：
1 <= dominoes.length <= 4 * 10^4
dominoes[i].length == 2
1 <= dominoes[i][j] <= 9
*/

package baseDataStruct.enumeration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NO1128 {
    public static void main(String[] args) {
        int[][] dominoes = {{1,2},{2,1},{1,1},{1,2},{2,2}};
        Solution solution = new Solution();
        int result = solution.numEquivDominoPairs(dominoes);
        System.out.println(result);
    }
    static class Solution {
        /*
        public int numEquivDominoPairs(int[][] dominoes) {
            Map<List<Integer>, Integer> map = new HashMap<>();
            int res = 0;
            for (int[] domino : dominoes) {
                int sum = domino[0] + domino[1];
                int multip = domino[0] * domino[1];
                List<Integer> key = new ArrayList<>();
                key.add(sum);
                key.add(multip);
                if(map.containsKey(key)){
                    res += map.get(key);
                    map.put(key, map.get(key)+1);
                }else{
                    map.put(key, 1);
                }
            }
            return res;
        }
        */
        public int numEquivDominoPairs(int[][] dominoes) {
            int[][] count = new int[10][10];
            int res = 0;
            for(int[] domino : dominoes) {
                int a = Math.min(domino[0], domino[1]);
                int b = Math.max(domino[0], domino[1]);
                res += count[a][b];
                count[a][b]++;
            }
            return res;
        }
    }
}
