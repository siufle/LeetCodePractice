/*LCR145. 判断对称二叉树
请设计一个函数判断一棵二叉树是否 轴对称 。

示例 1：
输入：root = [6,7,7,8,9,9,8]
输出：true
解释：从图中可看出树是轴对称的。

示例 2：
输入：root = [1,2,2,null,3,null,3]
输出：false
解释：从图中可看出最后一层的节点不对称。

提示：
0 <= 节点个数 <= 1000
*/

package baseDataStruct.binaryTree;

public class LCR145 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(7);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(8);
        root.left.right = new TreeNode(9);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(8);
        System.out.println(solution.checkSymmetricTree(root));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Solution {
        public boolean checkSymmetricTree(TreeNode root) {
            return check(root, root);
        }

        private boolean check(TreeNode left, TreeNode right) {
            if (left == null && right == null) return true;
            if (left == null || right == null || left.val != right.val) return false;
            return check(left.left, right.right) && check(left.right, right.left);
        }
    }
}
