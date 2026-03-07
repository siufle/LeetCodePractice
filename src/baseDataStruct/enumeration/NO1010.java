/*1010. 总持续时间可被60整除的歌曲对
在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。
形式上，我们希望下标数字 i 和 j 满足  i < j 且有 (time[i] + time[j]) % 60 == 0。

示例 1：
输入：time = [30,20,150,100,40]
输出：3
解释：这三对的总持续时间可被 60 整除：
(time[0] = 30, time[2] = 150): 总持续时间 180
(time[1] = 20, time[3] = 100): 总持续时间 120
(time[1] = 20, time[4] = 40): 总持续时间 60

示例 2：
输入：time = [60,60,60]
输出：3
解释：所有三对的总持续时间都是 120，可以被 60 整除。

提示：
1 <= time.length <= 6 * 10^4
1 <= time[i] <= 500
*/

package baseDataStruct.enumeration;

import java.util.HashMap;
import java.util.Map;

public class NO1010 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] time = {60,60,60};
        int res = solution.numPairsDivisibleBy60(time);
        System.out.println(res);
    }
    static class Solution {
        public int numPairsDivisibleBy60(int[] time) {
            Map<Integer,Integer> map = new HashMap<>();
            int res = 0;
            for (int t : time) {
                t = t % 60;
                if(t == 0){
                    res += map.getOrDefault(t,0);
                }else if(map.containsKey(60 - t)) {
                    res += map.get(60 - t);
                }
                map.merge(t,1,Integer::sum);
            }
            return res;
        }
    }
}
