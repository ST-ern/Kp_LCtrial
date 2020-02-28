/*

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
有效字符串需满足：
左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:
输入: "()"
输出: true
示例 2:
输入: "()[]{}"
输出: true
示例 3:
输入: "(]"
输出: false
示例 4:
输入: "([)]"
输出: false
示例 5:
输入: "{[]}"
输出: true

 */

import java.util.Stack;

/**
 * @author stern
 * @date 2020/2/28 10:33
 */
public class Solution20 {
    /**
     * @author stern
     * @date 2020-02-28 10:44
     * @description
     *
     * 执行用时 : 2 ms, 在所有 Java 提交中击败了92.78%的用户
     * 内存消耗 : 37.4 MB, 在所有 Java 提交中击败了5.03%的用户
     *
    */
    public static boolean isValid(String s) {
        Stack<Character> judge = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c=='(' || c=='[' || c=='{') {
                judge.push(c);
            } else if(c==')') {
                if(!judge.isEmpty() && judge.peek()=='(') {
                    judge.pop();
                } else {
                    return false;
                }
            } else if(c==']') {
                if(!judge.isEmpty() && judge.peek()=='[') {
                    judge.pop();
                } else {
                    return false;
                }
            } else {
                if(!judge.isEmpty() && judge.peek()=='{') {
                    judge.pop();
                } else {
                    return false;
                }
            }
        }
        if(judge.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String s = "";
        boolean res = isValid(s);
        System.out.println(res);
    }
}
