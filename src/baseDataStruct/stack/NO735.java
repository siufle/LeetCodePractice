/*735. 小行星碰撞
给定一个整数数组 asteroids，表示在同一行的小行星。数组中小行星的索引表示它们在空间中的相对位置。
对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
找出碰撞后剩下的所有小行星。碰撞规则：两个小行星相互碰撞，较小的小行星会爆炸。
如果两颗小行星大小相同，则两颗小行星都会爆炸。两颗移动方向相同的小行星，永远不会发生碰撞。

示例 1：
输入：asteroids = [5,10,-5]
输出：[5,10]
解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。

示例 2：
输入：asteroids = [8,-8]
输出：[]
解释：8 和 -8 碰撞后，两者都发生爆炸。

示例 3：
输入：asteroids = [10,2,-5]
输出：[10]
解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。

示例 4：
输入：asteroids = [3,5,-6,2,-1,4]
输出：[-6,2,4]
解释：小行星 -6 使小行星 3 和 5 爆炸，然后继续向左移动。在另一边，小行星 2 摧毁了小行星 -1。由于 2 和 4 都向右移动，它们永远不会相撞。

提示：
2 <= asteroids.length <= 10^4
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
*/

package baseDataStruct.stack;

import java.util.Arrays;

public class NO735 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] testCases = {
                {5, 10, -5},
                {8, -8},
                {10, 2, -5},
                {3, 5, -6, 2, -1, 4}
        };
        int[][] expectedResults = {
                {5, 10},
                {},
                {10},
                {-6, 2, 4}
        };

        for (int i = 0; i < testCases.length; i++) {
            try {
                int[] result = solution.asteroidCollision(testCases[i]);
                System.out.printf("输入: %s, 期望: %s, 实际: %s%n",
                        Arrays.toString(testCases[i]),
                        Arrays.toString(expectedResults[i]),
                        Arrays.toString(result));
            } catch (RuntimeException e) {
                System.out.printf("输入: %s, 期望: %s, 运行异常: %s%n",
                        Arrays.toString(testCases[i]),
                        Arrays.toString(expectedResults[i]),
                        e.getMessage());
            }
        }
    }

    static class Solution {
        public int[] asteroidCollision(int[] asteroids) {
            int[] stack = new int[100001];
            int top = 0;
            stack[top] = asteroids[0];
            int n = asteroids.length;
            int i = 1;
            while (i < n) {
                if (top < 0) {
                    top = 0;
                    stack[top] = asteroids[i];
                    i++;
                } else if (stack[top] < 0) {
                    top++;
                    stack[top] = asteroids[i];
                    i++;
                } else if (stack[top] > 0 && asteroids[i] > 0) {
                    top++;
                    stack[top] = asteroids[i];
                    i++;
                } else if (stack[top] > 0 && asteroids[i] < 0) {
                    if (stack[top] < -asteroids[i]) {
                        top--;
                    } else if (stack[top] == -asteroids[i]) {
                        top--;
                        i++;
                    } else {
                        i++;
                    }
                }
            }
            return Arrays.copyOfRange(stack, 0, top + 1);
        }
    }
}
