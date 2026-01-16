/*16.24.数对和
设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。

示例 1：
输入：nums = [5,6,5], target = 11
输出：[[5,6]]

示例 2：
输入：nums = [5,6,5,6], target = 11
输出：[[5,6],[5,6]]

提示：
nums.length <= 100000
-105 <= nums[i], target <= 10^5
*/

package baseDataStruct.enumeration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NO16_24 {
    public static void main(String[] args) {
        int[] nums = {5,6,5};
        int target = 11;
        Solution solution = new Solution();
        List<List<Integer>> res = solution.pairSums(nums, target);
        System.out.println(res);
    }
    static class Solution {
        public List<List<Integer>> pairSums(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Map<Integer, Integer> map = new HashMap<>();
            for(int num : nums) {
                if(map.containsKey(target - num) && map.get(target - num) > 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(target - num);
                    list.add(num);
                    res.add(list);
                    map.put(target - num, map.get(target - num) - 1);
                }else{
                    map.put(num, map.getOrDefault(num, 0) + 1);
                }
            }
            return res;
        }
    }
}
