/*

将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
请你实现这个将字符串进行指定行数变换的函数：
string convert(string s, int numRows);

示例 1:
输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"

示例 2:
输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:
L     D     R
E   O E   I I
E C   I H   N
T     S     G

 */


import java.util.ArrayList;
import java.util.List;

/**
 * @author stern
 * @date 2020/2/14 15:34
 */
public class Solution6 {
    /**
     * @author stern
     * @date 2020-02-14 16:08
     * @description
     *
     * 执行用时 : 5 ms, 在所有 Java 提交中击败了86.28%的用户
     * 内存消耗 : 45.6 MB, 在所有 Java 提交中击败了5.02%的用户
     *
    */
    public static String convert_1(String s, int numRows) {
        if(numRows == 1) {
            return s;
        }

        StringBuilder result = new StringBuilder(s.length());
        int circle = numRows * 2 - 2; //时常考虑边界情况！ numRows = 1时？
        int tmp;
        int i,j;
        for(j=0; j<s.length(); j+=circle) {
            result.append(s.charAt(j));
        }
        for(i=1; i<numRows-1; i++) {
            for(j=i; j<s.length(); j+=circle) {
                result.append(s.charAt(j));
                tmp = j + circle - 2 * i;
                if(tmp < s.length()) {
                    result.append(s.charAt(tmp));
                }
            }
        }
        for(j=numRows-1; j<s.length(); j+=circle) {
            result.append(s.charAt(j));
        }

        return result.toString();
    }

    /**
     * @author LeetCode
     * @date 2020-02-14 16:12
     * @description
     *
     * 思路: 通过从左向右迭代字符串，我们可以轻松地确定字符位于 Z 字形图案中的哪一行。
     *
     * 算法
     * 我们可以使用 min(numRows,len(s)) 个列表来表示 Z 字形图案中的非空行。
     * 从左到右迭代 ss，将每个字符添加到合适的行。
     * 可以使用当前行和当前方向这两个变量对合适的行进行跟踪。
     *
     * 只有当我们向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变。
     *
     *
     * 执行用时 : 10 ms, 在所有 Java 提交中击败了52.38%的用户
     * 内存消耗 : 46.4 MB, 在所有 Java 提交中击败了5.02%的用户
     *
    */
    public static String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

    public static void main(String[] args) {
        String s = "A";
        int r = 1;
        String res = convert(s, r);
        System.out.println(res);
    }
}
