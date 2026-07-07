/*25. K 个一组翻转链表
给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

示例 1：
输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]

示例 2：
输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]

提示：
链表中的节点数目为 n
1 <= k <= n <= 5000
0 <= Node.val <= 1000
*/

package baseDataStruct.linkedList;

public class NO25 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        Solution solution = new Solution();
        ListNode result = solution.reverseKGroup(head, 3);
        while (result != null) {
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
        public ListNode reverseKGroup(ListNode head, int k) {
            if(head == null || head.next == null) return head;
            if(k == 1) return head;
            ListNode pre = null;
            ListNode l = head;
            ListNode r = head;
            int t = 1;
            while(r != null){
                if(t % k == 0){
                    ListNode next = r.next;
                    r.next = null;
                    ListNode reverse = reverse(l);
                    if(t == k) head = reverse;
                    if(pre != null) pre.next = reverse;
                    pre = l;
                    l.next = next;
                    l = next;
                    r = next;
                    t++;
                }
                t++;
                if(r == null) break;
                r = r.next;
            }
            return head;
        }
        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null){
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }
    }
}
