package slideWindow;

/*1695.删除子数组的最大得分
给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
返回 只删除一个 子数组可获得的 最大得分 。
如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。

示例 1：
输入：nums = [4,2,4,5,6]
输出：17
解释：最优子数组是 [2,4,5,6]

示例 2：
输入：nums = [5,2,1,2,5,2,1,2,5]
输出：8
解释：最优子数组是 [5,2,1] 或 [1,2,5]*/

import java.util.HashMap;

public class NO1695 {
    public static void main(String[] args) {
        int[] nums = {5,2,1,2,5,2,1,2,5};
        Solution solution = new Solution();
        System.out.println(solution.maximumUniqueSubarray(nums));
    }
    static class Solution {
        public int maximumUniqueSubarray(int[] nums) {
            int maxNum = 0;
            int num = 0;
            int left = 0;
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(int right = 0; right < nums.length; right++) {
                int n = nums[right];
                num += n;
                if(map.containsKey(n)) {
                    map.put(n, map.get(n) + 1);
                }else{
                    map.put(n, 1);
                }
                while(map.size() < right - left + 1) {
                    int temp = nums[left];
                    int count = map.get(temp);
                    if(count > 1) {
                        map.put(temp, count - 1);
                    }else{
                        map.remove(temp);
                    }
                    num -= temp;
                    left++;
                }
                maxNum = Math.max(maxNum, num);
            }
            return maxNum;
        }
    }
}
