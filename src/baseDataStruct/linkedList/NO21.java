/*21. 合并两个有序链表
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

示例 1：
输入：l1 = [1,2,4], l2 = [1,3,4]
输出：[1,1,2,3,4,4]

示例 2：
输入：l1 = [], l2 = []
输出：[]

示例 3：
输入：l1 = [], l2 = [0]
输出：[0]

提示：
两个链表的节点数目范围是 [0, 50]
-100 <= Node.val <= 100
l1 和 l2 均按 非递减顺序 排列
*/

package baseDataStruct.linkedList;

public class NO21 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);
        ListNode list3 = solution.mergeTwoLists(list1, list2);
        while (list3 != null) {
            System.out.print(list3.val + " ");
            list3 = list3.next;
        }
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
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode head = null;
            if(list1 == null){
                return list2;
            }
            if(list2 == null){
                return list1;
            }
            ListNode cur = null;
            ListNode l1 = list1;
            ListNode l2 = list2;
            if(list1.val < list2.val){
                cur = list1;
                head = list1;
                l1 = l1.next;
            }else{
                cur = list2;
                head = list2;
                l2 = l2.next;
            }
            while(l1 != null && l2 != null){
                if(l1.val < l2.val){
                    cur.next = l1;
                    l1 = l1.next;
                }else{
                    cur.next = l2;
                    l2 = l2.next;
                }
                cur = cur.next;
            }
            if(l1 == null){
                cur.next = l2;
            }else{
                cur.next = l1;
            }
            return head;
        }
    }
}
