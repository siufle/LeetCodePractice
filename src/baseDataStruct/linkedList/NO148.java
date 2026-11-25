/*148. 排序链表
https://leetcode.cn/problems/sort-list/?envType=study-plan-v2&envId=top-100-liked

给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

示例 1
输入：head = [4,2,1,3]
输出：[1,2,3,4]

示例 2：
输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]

示例 3：
输入：head = []
输出：[]

提示：
链表中节点的数目在范围 [0, 5 * 10^4] 内
-10^5 <= Node.val <= 10^5
*/

package baseDataStruct.linkedList;

public class NO148 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        ListNode sortedHead = solution.sortList(head);
        while (sortedHead != null) {
            System.out.print(sortedHead.val + " ");
            sortedHead = sortedHead.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode mid = slow.next;
            slow.next = null;
            ListNode left = sortList(head);
            ListNode right = sortList(mid);
            return merge(left, right);
        }
        private ListNode merge(ListNode a, ListNode b) {
            ListNode dummy = new ListNode(0);
            ListNode curr = dummy;
            while (a != null && b != null) {
                if (a.val <= b.val) {
                    curr.next = a;
                    a = a.next;
                } else {
                    curr.next = b;
                    b = b.next;
                }
                curr = curr.next;
            }
            curr.next = (a != null) ? a : b;
            return dummy.next;
        }
    }
}
