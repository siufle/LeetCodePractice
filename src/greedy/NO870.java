/*870. 优势洗牌
给定两个长度相等的数组 nums1 和 nums2，nums1 相对于 nums2 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。
返回 nums1 的 任意 排列，使其相对于 nums2 的优势最大化。

示例 1：
输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
输出：[2,11,7,15]

示例 2：
输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
输出：[24,32,8,12]

提示：
1 <= nums1.length <= 10^5
nums2.length == nums1.length
0 <= nums1[i], nums2[i] <= 10^9
*/

package greedy;

import java.util.Arrays;

public class NO870 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {12,24,8,32}, nums2 = {13,25,32,11};
        int[] res = solution.advantageCount(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }
    static class Solution {
        public int[] advantageCount(int[] nums1, int[] nums2) {
            int n = nums1.length;
            Integer[] idx1 = new Integer[n];
            Integer[] idx2 = new Integer[n];
            for (int i = 0; i < n; i++) {
                idx1[i] = i;
                idx2[i] = i;
            }
            int[] res = new int[n];
            Arrays.sort(idx1, (i, j) -> nums1[i] - nums1[j]);
            Arrays.sort(idx2, (i, j) -> nums2[i] - nums2[j]);
            int left = 0, right = n - 1;
            for(int i = 0; i < n; i++){
                if(nums1[idx1[i]] > nums2[idx2[left]]){
                    res[idx2[left]] = nums1[idx1[i]];
                    left++;
                }else{
                    res[idx2[right]] = nums1[idx1[i]];
                    right--;
                }
            }
            return res;
        }
    }
}
