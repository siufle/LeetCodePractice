/*1526. 形成目标数组的子数组的最少增加次数
给你一个整数数组 target 和一个数组 initial ，initial 数组与 target  数组有同样的大小，且一开始全部为 0 。
一次操作中，你可以从 initial 数组中选择 任何 子数组，并将每个值加 1。
返回从 initial 数组构造 target 数组的最少操作次数。
答案保证在 32 位整数以内。

示例 1：
输入：target = [1,2,3,2,1]
输出：3
解释：我们需要至少 3 次操作从 intial 数组得到 target 数组。
[0,0,0,0,0] 将下标为 0 到 4 的元素（包含二者）加 1 。
[1,1,1,1,1] 将下标为 1 到 3 的元素（包含二者）加 1 。
[1,2,2,2,1] 将下标为 2 的元素增加 1 。
[1,2,3,2,1] 得到了目标数组。

示例 2：
输入：target = [3,1,1,2]
输出：4
解释：(initial)[0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2] (target) 。

示例 3：
输入：target = [3,1,5,4,2]
输出：7
解释：(initial)[0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] -> [3,1,1,1,1]
              -> [3,1,2,2,2] -> [3,1,3,3,2] -> [3,1,4,4,2] -> [3,1,5,4,2] (target)。

示例 4：
输入：target = [1,1,1,1]
输出：1

提示：
1 <= target.length <= 10^5
1 <= target[i] <= 10^5
输入保证答案在 32 位整数范围内。
*/

package baseDataStruct.difference;

public class NO1526 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minNumberOperations(new int[]{1,1,1,1}));
    }
    static class Solution {
        public int minNumberOperations(int[] target) {
            int n = target.length;
            int[] diff = new int[n];
            diff[0] = target[0];
            int po = 0;
            int ne = 0;
            if(target[0] > 0){
                po += target[0];
            }else{
                ne -= target[0];
            }
            for(int i = 1; i < n; i++){
                diff[i] = target[i] - target[i-1];
                if(diff[i] > 0){
                    po += diff[i];
                }else{
                    ne -= diff[i];
                }
            }
            return Math.max(po, ne);
        }
    }
}
