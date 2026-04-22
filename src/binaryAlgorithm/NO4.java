/*4. 寻找两个正序数组的中位数
给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。

示例 1：
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2

示例 2：
输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

提示：
nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-10^6 <= nums1[i], nums2[i] <= 10^6
*/

package binaryAlgorithm;

public class NO4 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num1 = {1,3};
        int[] num2 = {2,4};
        System.out.println(solution.findMedianSortedArrays(num1, num2));
    }
    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            if((m+n) % 2 == 1){
                int mid = (m+n)/2;
                return getKthElement(nums1, nums2, mid + 1);
            } else {
                int mid1 = (m+n)/2, mid2 = (m+n)/2 + 1;
                return (getKthElement(nums1, nums2, mid1) + getKthElement(nums1, nums2, mid2) * 1.0) / 2;
            }
        }

        private static int getKthElement(int[] nums1, int[] nums2, int k) {
            int m = nums1.length, n = nums2.length;
            int index1 = 0, index2 = 0;//偏移量

            while(true){
                //结束判断
                if(index1 == m){
                    return nums2[index2 + k - 1];
                }
                if(index2 == n){
                    return nums1[index1 + k - 1];
                }
                if(k == 1){
                    return Math.min(nums1[index1], nums2[index2]);
                }

                //正常情况
                int half = k / 2;
                int newIndex1 = Math.min(index1 + half, m) - 1;
                int newIndex2 = Math.min(index2 + half, n) - 1;
                int num1 = nums1[newIndex1], num2 = nums2[newIndex2];
                if(num1 <= num2){
                    k -= newIndex1 - index1 + 1;
                    index1 = newIndex1 + 1;
                }else{
                    k -= newIndex2 - index2 + 1;
                    index2 = newIndex2 + 1;
                }
            }
        }
    }
}
