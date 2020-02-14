/*

给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

示例 2：
输入: "cbbd"
输出: "bb"

 */


/**
 * @author stern
 * @date 2020/2/13 17:58
 */
public class Solution5 {
    /**
     * @author stern
     * @date 2020-02-13 18:39
     * @description
     * 恰巧是解题指导里的"中心扩展法"
     *
     *
     * 执行用时 : 57 ms, 在所有 Java 提交中击败了43.58%的用户
     * 内存消耗 : 42 MB, 在所有 Java 提交中击败了11.78%的用户
     *
    */
    public static String longestPalindrome_1(String s) {
        int maxLeft = 0, maxRight = 0;
        int maxLength = 0;
        int left1, right1, left2, right2;
        int length1, length2;
        if(s.length() == 0) {
            return "";
        }
        for(int i=0; i<s.length()-1; i++) {
            length1 = 0;
            left1 = 0;
            right1 = 0;
            if(s.charAt(i)==s.charAt(i+1)) {
                length1 = 0;
                left1 = i;
                right1 = i + 1;
                while (left1 >= 0 && right1 < s.length() && s.charAt(left1) == s.charAt(right1)) {
                    length1 += 2;
                    left1--;
                    right1++;
                }
            }
            length2 = -1;
            left2 = i;
            right2 = i;
            while (left2 >= 0 && right2 < s.length() && s.charAt(left2) == s.charAt(right2)) {
                length2 += 2;
                left2--;
                right2++;
            }

            if(length1 > length2 && length1 > maxLength) {
                maxLength = length1;
                maxLeft = left1 + 1;
                maxRight = right1 - 1;
            } else if(length2 > length1 && length2 > maxLength) {
                maxLength = length2;
                maxLeft = left2 + 1;
                maxRight = right2 - 1;
            }
        }

        return s.substring(maxLeft, maxRight+1);
    }

    public static void main(String[] args) {
        String s = "ccb";
        String result = longestPalindrome_1(s);
        System.out.println(result);
    }
}
