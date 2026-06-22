/*3767. 选择k个任务的最大总分数
给你两个整数数组 technique1 和 technique2，长度均为 n，其中 n 代表需要完成的任务数量。
如果第 i 个任务使用技巧 1 完成，你将获得 technique1[i] 分。
如果使用技巧 2 完成，你将获得 technique2[i] 分。
此外给你一个整数 k，表示 必须 使用技巧 1 完成的 最少 任务数量。
你 必须 使用技巧 1 完成 至少 k 个任务（不需要是前 k 个任务）。
剩余的任务可以使用 任一 技巧完成。
返回一个整数，表示你能获得的 最大总分数。

示例 1：
输入：technique1 = [5,2,10], technique2 = [10,3,8], k = 2
输出：22
解释：
我们必须使用 technique1 完成至少 k = 2 个任务。
选择 technique1[1] 和 technique1[2]（使用技巧 1 完成），以及 technique2[0]（使用技巧 2 完成），可以获得最大分数：2 + 10 + 10 = 22。

示例 2：
输入：technique1 = [10,20,30], technique2 = [5,15,25], k = 2
输出：60
解释：
我们必须使用 technique1 完成至少 k = 2 个任务。
选择所有任务都使用技巧 1 完成，可以获得最大分数：10 + 20 + 30 = 60。

示例 3：
输入：technique1 = [1,2,3], technique2 = [4,5,6], k = 0
输出：15
解释：
由于 k = 0，我们不需要选择任何使用 technique1 的任务。
选择所有任务都使用技巧 2 完成，可以获得最大分数：4 + 5 + 6 = 15。

提示：
1 <= n == technique1.length == technique2.length <= 10^5
1 <= technique1[i], technique2[i] <= 10^5
0 <= k <= n
*/

package greedy;

import java.util.Arrays;

public class NO3767 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] technique1 = new int[]{5,2,10};
        int[] technique2 = new int[]{10,3,8};
        System.out.println(solution.maxPoints(technique1, technique2, 2));
    }
    static class Solution {
        public long maxPoints(int[] technique1, int[] technique2, int k) {
            int n = technique1.length;
            int[] diff = new int[n];
            long ans = 0;
            for(int i = 0; i < n; i++){
                ans += technique1[i];
                diff[i] = technique1[i] - technique2[i];
            }
            Arrays.sort(diff);
            for(int i = 0; i < n - k; i++){
                if(diff[i] < 0){
                    ans -= diff[i];
                }
            }
            return ans;
        }
    }
}
