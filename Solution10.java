/*

给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

说明:
s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。

示例 1:
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。

示例 2:
输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。
因此，字符串 "aa" 可被视为 'a' 重复了一次。

示例 3:
输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。

示例 4:
输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。

示例 5:
输入:
s = "mississippi"
p = "mis*is*p*."
输出: false

 */


/**
 * @author stern
 * @date 2020/2/18 11:26
 */
public class Solution10 {
    /**
     * @author stern
     * @date 2020-02-18 12:50
     * @description
     *
     * 执行用时 : 146 ms, 在所有 Java 提交中击败了6.50%的用户
     * 内存消耗 : 48.9 MB, 在所有 Java 提交中击败了5.02%的用户
     *
    */
    public static boolean isMatch_1(String s, String p) {
        if(s.isEmpty() && p.isEmpty()) { return true; }
        else if(p.isEmpty()) { return false; }

        if(p.length() == 1) {
            if(p.equals(".")) { return (s.length()==1); }
            return (p.equals(s));
        }

        int length_s = s.length();
        int length_p = p.length();
        int count_s = 0;
        int count_p = 0, next_p = 1;
        while(count_s < length_s && count_p < length_p) {
            if(next_p < length_p && p.charAt(next_p) == '*') {
                if(p.charAt(count_p)=='.') {
                    while(count_s <= length_s) {
                        boolean res = isMatch_1(s.substring(count_s, length_s),
                                p.substring(next_p+1, length_p));
                        if(res) {
                            return true;
                        }
                        count_s++;
                    }
                    return false;
                } else {
                    boolean res = isMatch_1(s.substring(count_s, length_s),
                            p.substring(next_p+1, length_p));
                    if(res) {
                        return true;
                    }
                    while(count_s < length_s && s.charAt(count_s) == p.charAt(count_p)) {
                        count_s++;
                        res = isMatch_1(s.substring(count_s, length_s),
                                p.substring(next_p+1, length_p));
                        if(res) {
                            return true;
                        }
                    }
                    return false;
                    //return isMatch(s.substring(count_s, length_s),
                            //p.substring(next_p+1, length_p));
                }
            } else if(s.charAt(count_s) == p.charAt(count_p) || p.charAt(count_p) == '.') {
                count_s++; count_p++; next_p++;
            } else {
                return false;
            }
        }

        if(count_s == length_s) {
            if(count_p == length_p) {
                return true;
            } else if(next_p == length_p) {
                return false;
            } else {
                if(p.charAt(next_p) == '*') {
                    return isMatch_1("",
                                p.substring(next_p+1, length_p));
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        // return count_s == length_s && count_p == length_p;

    }

    /**
     * @author Leetcode
     * @date 2020-02-18 12:53
     * @description
     *
     * 方法 1：回溯
     *
     * 想法
     * 如果没有星号（正则表达式中的 * ），
     * 问题会很简单——我们只需要从左到右检查匹配串 s 是否能匹配模式串 p 的每一个字符。
     * 当模式串中有星号时，我们需要检查匹配串 s 中的不同后缀，
     * 以判断它们是否能匹配模式串剩余的部分。
     * 一个直观的解法就是用回溯的方法来体现这种关系。
     *
     * 如果模式串中有星号，它会出现在第二个位置，即 pattern[1] 。
     * 这种情况下，我们可以直接忽略模式串中这一部分，或者删除匹配串的第一个字符，
     * 前提是它能够匹配模式串当前位置字符，即 pattern[0] 。
     * 如果两种操作中有任何一种使得剩下的字符串能匹配，那么初始时，
     * 匹配串和模式串就可以被匹配。
     *
    */
    public static boolean isMatch1(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch1(text, pattern.substring(2)) ||
                    (first_match && isMatch1(text.substring(1), pattern)));
        } else {
            return first_match && isMatch1(text.substring(1), pattern.substring(1));
        }
    }


    /**
     * @author LeetCode
     * @date 2020-02-18 12:56
     * @description
     *
     * 方法 2: 动态规划
     *
     * 想法
     * 因为题目拥有 最优子结构 ，一个自然的想法是将中间结果保存起来。
     * 我们通过用 dp(i,j) 表示 text[i:] 和 pattern[j:] 是否能匹配。
     * 我们可以用更短的字符串匹配问题来表示原本的问题。
     *
     * 算法
     * 我们用 [方法 1] 中同样的回溯方法，除此之外，
     * 因为函数 match(text[i:], pattern[j:]) 只会被调用一次，
     * 我们用 dp(i, j) 来应对剩余相同参数的函数调用，
     * 这帮助我们节省了字符串建立操作所需要的时间，也让我们可以将中间结果进行保存。
    */
    //==========自顶向下==========
    enum Result {
        TRUE, FALSE
    }
    Result[][] memo;
    public boolean isMatch2(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }
    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()){
            ans = i == text.length();
        } else{
            boolean first_match = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern) ||
                        first_match && dp(i+1, j, text, pattern));
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }
    //==========自底向上==========
    //注释来源：Leetcode user - ほ月
    public static boolean isMatch3(String text, String pattern) {
        // 用于保存中间结果
        boolean[][] dp = new boolean[text.length()+1][pattern.length()+1];
        // 将右下角置为true
        //从后往前匹配
        dp[text.length()][pattern.length()] = true;
        // 从后向前匹配
        // i从越界开始补齐了最后一列
        // 由于dp的右下角已经赋值
        // 所以不用重复判断所以j从length-1开始
        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length()-1; j >= 0; j++) {
                // 判断字符是否相等
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                // 涉及到了*号匹配
                if (j+1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    // 状态转换方程
                    // 若后一个字符为*那么就涉及到了*号匹配
                    // 规律就是看看跳过两个是否匹配（也代表了*号的可以是0个匹配字符的性质）
                    // 或者老老实实匹配*号当前字符是否匹配且j不用改变i继续后移看看是否匹配
                    dp[i][j] = dp[i][j+2] || first_match&&dp[i+1][j];
                }else {
                    // 不涉及*号的匹配
                    // 就看之前的字符是否匹配以及当前字符是否匹配即可
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        // dp[0][0]就代表着之后的全部匹配完成看看是否全部匹配
        return dp[0][0];
    }




    public static void main(String[] args) {
        String s = "a";
        String p = ".*..a";
        boolean res = isMatch3(s, p);
        System.out.println(res);
    }
}
