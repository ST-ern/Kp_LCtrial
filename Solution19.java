/*

给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：
给定一个链表: 1->2->3->4->5, 和 n = 2.
当删除了倒数第二个节点后，链表变为 1->2->3->5.

说明：
给定的 n 保证是有效的。

进阶：
你能尝试使用一趟扫描实现吗？

 */


/**
 * @author stern
 * @date 2020/2/27 10:18
 */
public class Solution19 {
    //Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int x) { val = x; }
    }

    /**
     * @author stern
     * @date 2020-02-27 10:32
     * @description
     *
     * 执行用时 : 0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 : 37.6 MB, 在所有 Java 提交中击败了5.02%的用户
     *
    */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode behind = head;
        ListNode tmp = head;
        ListNode front = head;
        for(;n>0; n--) {
            front = front.next;
        }
        if(front==null) {
            tmp = tmp.next;
            return tmp;
        }
        tmp = tmp.next;
        front = front.next;
        while(front!=null) {
            behind = behind.next;
            tmp = tmp.next;
            front = front.next;
        }
        behind.next = tmp.next;
        return head;
    }
}
