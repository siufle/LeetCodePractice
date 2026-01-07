/*1287. 有序数组中出现次数超过25%的元素
给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。
请你找到并返回这个整数

示例：
输入：arr = [1,2,2,6,6,6,6,7,10]
输出：6

提示：
1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5
*/

package binaryAlgorithm;

public class NO1287 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,12,12,12};
        System.out.println(s.findSpecialInteger(arr));
    }
    static class Solution {
        public int findSpecialInteger(int[] arr) {
            for(int i = 0; i <= 3; i++) {
                long index = Math.round(arr.length / 4.0 * i);
                int num = arr[(int)index];
                int left = upperBound(arr, num - 1);
                int right = upperBound(arr, num);
                if(right - left > arr.length / 4){
                    return num;
                }
            }
            return -1;
        }
        public int upperBound(int[] arr, int target){
            int left = -1;
            int right = arr.length;
            while(left + 1 < right){
                int mid = (right + left) / 2;
                if(arr[mid] > target){
                    right = mid;
                }else{
                    left = mid;
                }
            }
            return right;
        }
    }
}
