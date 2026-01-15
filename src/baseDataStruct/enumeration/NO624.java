/*624. 数组列表中的最大距离
给定 m 个数组，每个数组都已经按照升序排好序了。
现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。
两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。
返回最大距离。

示例 1：
输入：[[1,2,3],[4,5],[1,2,3]]
输出：4
解释：
一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。

示例 2：
输入：arrays = [[1],[1]]
输出：0

提示：
m == arrays.length
2 <= m <= 10^5
1 <= arrays[i].length <= 500
-10^4 <= arrays[i][j] <= 10^4
arrays[i] 以 升序 排序。
所有数组中最多有 10^5 个整数。
*/

package baseDataStruct.enumeration;

import java.util.*;

public class NO624 {
    public static void main(String[] args) {
        List<List<Integer>> arrays = new ArrayList<>();
        arrays.add(Arrays.asList(1,2,3));
        arrays.add(Arrays.asList(4,5));
        arrays.add(Arrays.asList(1,2,3));
        Solution solution = new Solution();
        int result = solution.maxDistance(arrays);
        System.out.println(result);
    }
    static class Solution {
        public int maxDistance(List<List<Integer>> arrays) {
            int res = 0;
            int min = arrays.get(0).get(0);
            int max = arrays.get(0).get(arrays.get(0).size() - 1);
            for (int i = 1; i < arrays.size(); i++) {
                res = Math.max(res, Math.abs(max - arrays.get(i).get(0)));
                res = Math.max(res, Math.abs(arrays.get(i).get(arrays.get(i).size() - 1) - min));
                min = Math.min(min, arrays.get(i).get(0));
                max = Math.max(max, arrays.get(i).get(arrays.get(i).size() - 1));
            }
            return res;
        }
    }
}
