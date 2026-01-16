/*1679. K 和数对的最大数目
给你一个整数数组 nums 和一个整数 k 。
每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
返回你可以对数组执行的最大操作数。

示例 1：
输入：nums = [1,2,3,4], k = 5
输出：2
解释：开始时 nums = [1,2,3,4]：
- 移出 1 和 4 ，之后 nums = [2,3]
- 移出 2 和 3 ，之后 nums = []
不再有和为 5 的数对，因此最多执行 2 次操作。

示例 2：
输入：nums = [3,1,3,4,3], k = 6
输出：1
解释：开始时 nums = [3,1,3,4,3]：
- 移出前两个 3 ，之后nums = [1,4,3]
不再有和为 6 的数对，因此最多执行 1 次操作。

提示：
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= k <= 10^9
*/

package baseDataStruct.enumeration;

import java.util.HashMap;
import java.util.Map;

public class NO1679 {
    public static void main(String[] args) {
        int[] nums = {2,5,4,4,1,3,4,4,1,4,4,1,2,1,2,2,3,2,4,2};
        int k = 3;
        Solution solution = new Solution();
        int result = solution.maxOperations(nums, k);
        System.out.println(result);
    }
    static class Solution {
        public int maxOperations(int[] nums, int k) {
            int res = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for(int num:nums){
                if(map.containsKey(k - num) && map.get(k - num) > 0){
                    res++;
                    map.put(k - num, map.get(k - num) - 1);
                } else {
                    map.merge(num, 1, Integer::sum);
                }
                /*else if(map.containsKey(num)){
                    map.put(num, map.get(num) + 1);
                }else{
                    map.put(num, 1);
                }*/
            }
            return res;
        }
    }
}
