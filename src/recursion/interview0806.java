/*面试08.06 汉诺塔问题
在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。
一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。
移动圆盘时受到以下限制:
(1) 每次只能移动一个盘子;
(2) 盘子只能从柱子顶端滑出移到下一根柱子;
(3) 盘子只能叠在比它大的盘子上。
请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
你需要原地修改栈。

示例 1：
 输入：A = [2, 1, 0], B = [], C = []
 输出：C = [2, 1, 0]

示例 2：
 输入：A = [1, 0], B = [], C = []
 输出：C = [1, 0]

提示：
A 中盘子的数目不大于 14 个。
*/

package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class interview0806 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> A = new ArrayList<>(Arrays.asList(6, 5, 4, 3, 2, 1, 0));
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        solution.hanota(A, B, C);
        C.forEach(System.out::println);
    }
    static class Solution {
        public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
            move(A, C, B, A.size());
        }
        private void move(List<Integer> s, List<Integer> e, List<Integer> t, int n){
            if(n == 1){
                e.add(s.remove(s.size()-1));
            } else{
                move(s, t, e, n-1);
                move(s, e, t, 1);
                move(t, e, s, n-1);
            }
        }
    }
}
