import java.util.Arrays;

/*

给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

 */



/**
 * @author stern
 * @date 2020/2/10 11:55
 */
public class Solution2 {
    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }
     
    /**
     * @author stern
     * @date 2020-02-10 16:05
     * @description
     * 执行用时 : 3 ms
     * 内存消耗 : 44.8 MB (发现多次提交结果都不一样，只取第一次)
    */ 
    public static ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        // 思路：相加一次遍历，移位一次遍历
        ListNode resultBegin;
        ListNode i1 = l1;
        ListNode i2 = l2;

        resultBegin = new ListNode(i1.val+i2.val);
        ListNode tmp;
        tmp = resultBegin;
        i1 = i1.next;
        i2 = i2.next;

        while(i1 != null || i2 != null) {
            if(i1 == null) {
                tmp.next = new ListNode(i2.val);
                i2 = i2.next;
            } else if(i2 == null) {
                tmp.next = new ListNode(i1.val);
                i1 = i1.next;
            } else {
                tmp.next = new ListNode(i1.val + i2.val);
                i1 = i1.next;
                i2 = i2.next;
            }
            tmp = tmp.next;
        }

        ListNode temp = resultBegin;
        tmp = temp;
        boolean add = false;
        while(temp != null) {
            if(add) {
                temp.val += 1;
                add = false;
            }
            if(temp.val >= 10) {
                temp.val -= 10;
                add = true;
            }
            tmp = temp;
            temp = temp.next;
        }
        if(add) {
            tmp.next = new ListNode(1);
        }

        return resultBegin;
    }
    
    /**
     * @author stern
     * @date 2020-02-10 16:04
     * @description 
     * 执行用时 : 2 ms, 在所有 Java 提交中击败了99.97%的用户
     * 内存消耗 : 45.1 MB, 在所有 Java 提交中击败了8.61%的用户
     * 
    */
    public static ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {
        // 直接在其中一个链表的基础上运算

        ListNode tmp1 = l1;
        ListNode tmp_1 = tmp1;
        ListNode tmp2 = l2;
        ListNode tmp_2 = tmp1;
        boolean add = false;

        while(tmp1 != null || tmp2 != null) {
            // 求和
            if(tmp1 == null) {
                tmp_1.next = new ListNode(tmp2.val);
                tmp1 = tmp_1.next;
            } else if(tmp2 == null) {
                tmp1.val += 0;
                tmp2 = tmp_2;
            } else {
                tmp1.val += tmp2.val;
            }
            // 判断进位
            if(add) {
                tmp1.val += 1;
                add = false;
            }
            if(tmp1.val >= 10) {
                tmp1.val -= 10;
                add = true;
            }

            tmp_1 = tmp1;
            tmp1 = tmp1.next;
            tmp_2 = tmp2;
            tmp2 = tmp2.next;
        }
        if(add) {
            tmp_1.next = new ListNode(1);
        }

        return l1;
    }


    /**
     * @author LeetCode(Source)
     * @description
     * 伪代码如下：
     *
     * 将当前结点初始化为返回列表的哑结点。
     * 将进位 carrycarry 初始化为 00。
     * 将 pp 和 qq 分别初始化为列表 l1l1 和 l2l2 的头部。
     * 遍历列表 l1l1 和 l2l2 直至到达它们的尾端。
     * 将 xx 设为结点 pp 的值。如果 pp 已经到达 l1l1 的末尾，则将其值设置为 00。
     * 将 yy 设为结点 qq 的值。如果 qq 已经到达 l2l2 的末尾，则将其值设置为 00。
     * 设定 sum = x + y + carrysum=x+y+carry。
     * 更新进位的值，carry = sum / 10carry=sum/10。
     * 创建一个数值为 (sum \bmod 10)(summod10) 的新结点，并将其设置为当前结点的下一个结点，然后将当前结点前进到下一个结点。
     * 同时，将 pp 和 qq 前进到下一个结点。
     * 检查 carry = 1carry=1 是否成立，如果成立，则向返回列表追加一个含有数字 11 的新结点。
     * 返回哑结点的下一个结点。
     * 请注意，我们使用哑结点来简化代码。如果没有哑结点，则必须编写额外的条件语句来初始化表头的值。
     *
     *
     * 执行用时 : 3 ms, 在所有 Java 提交中击败了40.64%的用户
     * 内存消耗 : 44.6 MB, 在所有 Java 提交中击败了36.55%的用户
    */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }



    public static void main(String[] args){
        ListNode begin1 = new ListNode(5);
        ListNode temp1 = begin1;
//        begin1.next = new ListNode(4);
//        begin1 = begin1.next;
//        begin1.next = new ListNode(3);

        ListNode begin2 = new ListNode(5);
        ListNode temp2 = begin2;
//        begin2.next = new ListNode(6);
//        begin2 = begin2.next;
//        begin2.next = new ListNode(4);

        // bebug
        ListNode result = addTwoNumbers_2(temp1, temp2);
        System.out.println(result);

    }
}
