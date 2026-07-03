/*19. 删除链表的倒数第 N 个结点
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

示例 1：
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]

示例 2：
输入：head = [1], n = 1
输出：[]

示例 3：
输入：head = [1,2], n = 1
输出：[1]

提示：
链表中结点的数目为 sz
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
*/

package baseDataStruct.linkedList;

public class NO19 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode result = solution.removeNthFromEnd(head, 2);
        while(result != null){
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    static public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode fast = head;
            ListNode slow = head;
            ListNode pre = null;
            for (int i = 0; i < n; i++) {
                fast = fast.next;
            }
            while(fast != null){
                fast = fast.next;
                pre = slow;
                slow = slow.next;
            }
            if(pre != null && slow != null){
                pre.next = slow.next;
            }else if (pre == null && head != null){
                head = head.next;
            }
            return head;
        }
    }
}
