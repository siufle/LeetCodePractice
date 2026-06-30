/*234. 回文链表
给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。

示例 1：
输入：head = [1,2,2,1]
输出：true

示例 2：
输入：head = [1,2]
输出：false

提示：
链表中节点数目在范围[1, 105] 内
0 <= Node.val <= 9
*/

package baseDataStruct.linkedList;

public class NO234 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(1);
        System.out.println(solution.isPalindrome(head));
    }

    static public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {
        public boolean isPalindrome(ListNode head) {
            int len = 0;
            ListNode temp = head;
            while (temp != null) {
                len++;
                temp = temp.next;
            }
            ListNode[] arr = new ListNode[len];
            temp = head;
            for (int i = 0; i < len; i++) {
                arr[i] = temp;
                temp = temp.next;
            }
            for (int i = 0; i < len / 2; i++) {
                if (arr[i].val != arr[len - i - 1].val) {
                    return false;
                }
            }
            return true;
        }
    }
}
