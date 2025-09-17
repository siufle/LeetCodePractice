package slideWindow;

/*1658.将x减到0的最小操作数
给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。
请注意，需要 修改 数组以供接下来的操作使用。
如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。

示例 1：
输入：nums = [1,1,4,2,3], x = 5
输出：2
解释：最佳解决方案是移除后两个元素，将 x 减到 0 。

示例 2：
输入：nums = [5,6,7,8,9], x = 4
输出：-1

示例 3：
输入：nums = [3,2,20,1,1,3], x = 10
输出：5
解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。*/

public class NO1658 {
    public static void main(String[] args) {
        int[] nums = {3,2,20,1,1,3};
        int x = 10;
        Solution solution = new Solution();
        System.out.println(solution.minOperations(nums,x));
    }
    static class Solution {
        public int minOperations(int[] nums, int x) {
            if(x == 0) return 0;
            long sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if(sum < x){
                return -1;
            }
            if(sum == x){
                return nums.length;
            }
            long k = sum - x;
            long n = 0;
            int left = 0;
            int count = 0;
            for(int right = 0; right<nums.length; right++){
                n += nums[right];
                while(n > k){
                    int temp = nums[left];
                    n -= temp;
                    left++;
                }
                if(n == k){
                    count = Math.max(count, right-left+1);
                }
            }
            return count == 0 ? -1 : (nums.length - count);
        }
    }
}
