/*

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:
输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:
输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:
输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author stern
 * @date 2020/2/11 15:39
 */
public class Solution3 {
    /**
     * @author stern
     * @date 2020-02-11 16:32
     * @description
     * 执行用时 : 4 ms, 在所有 Java 提交中击败了90.82%的用户
     * 内存消耗 : 42.5 MB, 在所有 Java 提交中击败了5.02%的用户
     *
    */
    public static int lengthOfLongestSubstring_1(String s) {
        // 思路：遇到重复字母，重制起始位置
        int before = -1;
        int end = 0;
        int length = 0;
        int maxLength = 0;

        while(end < s.length()) {
            for(int i=before+1; i<end; i++) {
                if(s.charAt(i) == s.charAt(end)) {
                    maxLength = Math.max(length, maxLength);
                    before = i;
                    length = end - before - 1;
                    break;
                }
            }
            length += 1;
            end++;
        }

        maxLength = Math.max(length, maxLength);
        return maxLength;
    }


    /**
     * @author LeetCode
     * @date 2020-02-11 16:35
     * @description 滑动窗口
     *
     * 最简单直接的思路：暴力法。暴力法非常简单，但它太慢了。那么我们该如何优化它呢？
     *
     * 在暴力法中，我们会反复检查一个子字符串是否含有有重复的字符，但这是没有必要的。
     * 如果从索引 i 到 j-1 之间的子字符串 s_{ij}已经被检查为没有重复字符,
     * 我们只需要检查 s[j] 对应的字符是否已经存在于子字符串 s_{ij}中。
     *
     * 要检查一个字符是否已经在子字符串中，我们可以检查整个子字符串，
     * 这将产生一个复杂度为 O(n^2) 的算法，但我们可以做得更好。
     *
     * 通过使用 HashSet 作为滑动窗口，
     * 我们可以用 O(1) 的时间来完成对字符是否在当前的子字符串中的检查。
     *
     * 滑动窗口是数组/字符串问题中常用的抽象概念。
     * 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，即 [i,j)（左闭，右开）。
     * 而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。
     * 例如，我们将 [i,j) 向右滑动 1 个元素，则它将变为 [i+1,j+1)（左闭，右开）。
     *
     * 回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i,j)（最初 j=i）中。
     * 然后我们向右侧滑动索引 j，如果它不在 HashSet 中，我们会继续滑动 j。
     * 直到 s[j] 已经存在于 HashSet 中。
     * 此时，我们找到的没有重复字符的最长子字符串将会以索引 i 开头。
     * 如果我们对所有的 i 这样做，就可以得到答案。
     *
     *
    */
    public int lengthOfLongestSubstring_model1(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
    /**
     * @author LeetCode
     * @date 2020-02-11 16:40
     * @description 优化的滑动窗口
     *
     * 上述的方法最多需要执行 2n 个步骤。
     * 事实上，它可以被进一步优化为仅需要 n 个步骤。
     *
     * 我们可以定义字符到索引的映射，而不是使用集合来判断一个字符是否存在。
     * 当我们找到重复的字符时，我们可以立即跳过该窗口。
     * 也就是说，如果 s[j] 在 [i,j) 范围内有与 j' 重复的字符，
     * 我们不需要逐渐增加 i 。 我们可以直接跳过 [i，j′] 范围内的所有元素，
     * 并将 i 变为 j'+1 。
     *
     * 执行用时 : 12 ms, 在所有 Java 提交中击败了55.99%的用户
     * 内存消耗 : 49 MB, 在所有 Java 提交中击败了5.02%的用户
     *
    */
    public int lengthOfLongestSubstring_model2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }



    public static void main(String[] args) {
        String s = " ";
        int result = lengthOfLongestSubstring_1(s);
        System.out.println(result);
    }
}
