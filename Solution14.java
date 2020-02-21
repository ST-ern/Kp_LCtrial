/*

编写一个函数来查找字符串数组中的最长公共前缀。
如果不存在公共前缀，返回空字符串 ""。

示例 1:
输入: ["flower","flow","flight"]
输出: "fl"

示例 2:
输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。

说明:
所有输入只包含小写字母 a-z 。

 */


/**
 * @author stern
 * @date 2020/2/21 12:26
 */
public class Solution14 {
    /**
     * @author stern
     * @date 2020-02-21 12:47
     * @description 执行用时 : 2 ms, 在所有 Java 提交中击败了38.46%的用户
     * 内存消耗 : 37.5 MB, 在所有 Java 提交中击败了45.57%的用户
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String s_max = strs[0], s_min = strs[0];
        int i = 0;
        for (i = 1; i < strs.length; i++) {
            s_max = max(s_max, strs[i]);
            s_min = min(s_min, strs[i]);
        }
        StringBuilder res = new StringBuilder();
        for (i = 0; i < s_min.length(); i++) {
            if (i >= s_max.length()) {
                return res.toString();
            }
            if (s_max.charAt(i) == s_min.charAt(i)) {
                res.append(s_max.charAt(i));
            } else {
                return res.toString();
            }
        }
        return s_min;
    }

    private String max(String str1, String str2) {
        for (int i = 0; i < str1.length(); i++) {
            if (i >= str2.length()) {
                return str1;
            }
            if (str1.charAt(i) < str2.charAt(i)) {
                return str2;
            } else if (str1.charAt(i) > str2.charAt(i)) {
                return str1;
            }
        }
        return str2;
    }

    private String min(String str1, String str2) {
        for (int i = 0; i < str1.length(); i++) {
            if (i >= str2.length()) {
                return str2;
            }
            if (str1.charAt(i) < str2.charAt(i)) {
                return str1;
            } else if (str1.charAt(i) > str2.charAt(i)) {
                return str2;
            }
        }
        return str1;
    }


    /**
     * @author Leetcode
     * @date 2020-02-21 12:50
     * @description 方法一：水平扫描法
     */
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    /**
     * @author Leetcode
     * @date 2020-02-21 12:54
     * @description 算法二：水平扫描
     * <p>
     * 想象数组的末尾有一个非常短的字符串，使用上述方法依旧会进行 S​ 次比较。
     * 优化这类情况的一种方法就是水平扫描。
     * 我们从前往后枚举字符串的每一列，
     * 先比较每个字符串相同列上的字符（即不同字符串相同下标的字符）然后再进行对下一列的比较。
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    /**
     * @author Leetcode
     * @date 2020-02-21 12:55
     * @description 算法三：分治
     */
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }
    private String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        } else {
            int mid = (l + r) / 2;
            String lcpLeft = longestCommonPrefix(strs, l, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, r);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }
    String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i))
                return left.substring(0, i);
        }
        return left.substring(0, min);
    }

    /**
     * @author Leetcode
     * @date 2020-02-21 12:58
     * @description
     * 方法四：二分查找法
     *
     * 这个想法是应用二分查找法找到所有字符串的公共前缀的最大长度 L。
     * 算法的查找区间是 (0…minLen)，其中 minLen 是输入数据中最短的字符串的长度，
     * 同时也是答案的最长可能长度。
     * 每一次将查找区间一分为二，然后丢弃一定不包含最终答案的那一个。
     * 算法进行的过程中一共会出现两种可能情况：
     * <p>
     * S[1...mid] 不是所有串的公共前缀。
     * 这表明对于所有的 j > i, S[1..j] 也不是公共前缀，于是我们就可以丢弃后半个查找区间。
     * S[1...mid] 是所有串的公共前缀。
     * 这表示对于所有的 i < j, S[1..i] 都是可行的公共前缀，
     * 因为我们要找最长的公共前缀，所以我们可以把前半个查找区间丢弃。
     */
    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            minLen = Math.min(minLen, str.length());
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle))
                low = middle + 1;
            else
                high = middle - 1;
        }
        return strs[0].substring(0, (low + high) / 2);
    }
    private boolean isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(str1))
                return false;
        return true;
    }

    /**
     * @author Leetcode
     * @date 2020-02-21 13:00
     * @description
     *
     * 方法五：字典树
     *
     * 我们可以通过将所有的键值 S 存储到一颗字典树中来优化最长公共前缀查询操作。
     * 如果你想学习更多关于字典树的内容，可以从 208. 实现 Trie (前缀树) 开始。
     * 在字典树中，从根向下的每一个节点都代表一些键值的公共前缀。
     * 但是我们需要找到字符串q 和所有键值字符串的最长公共前缀。
     * 这意味着我们需要从根找到一条最深的路径，满足以下条件：
     *
     * - 这是所查询的字符串 q 的一个前缀
     * - 路径上的每一个节点都有且仅有一个孩子。
     * 否则，找到的路径就不是所有字符串的公共前缀
     * - 路径不包含被标记成某一个键值字符串结尾的节点。
     * 因为最长公共前缀不可能比某个字符串本身长
     *
     * 算法
     * 最后的问题就是如何找到字典树中满足上述所有要求的最深节点。
     * 最有效的方法就是建立一颗包含字符串 [S1…Sn] 的字典树。
     * 然后在这颗树中匹配 q 的前缀。
     * 我们从根节点遍历这颗字典树，直到因为不能满足某个条件而不能再遍历为止。
    */
    public String longestCommonPrefix5(String q, String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        Trie trie = new Trie();
        for (int i = 1; i < strs.length ; i++) {
            trie.insert(strs[i]);
        }
        return trie.searchLongestPrefix(q);
    }

    static class TrieNode {

        // 子节点的链接数组
        private TrieNode[] links;

        private final int R = 26;

        private boolean isEnd;

        // 非空子节点的数量
        private int size;
        public void put(char ch, TrieNode node) {
            links[ch -'a'] = node;
            size++;
        }

        public int getLinks() {
            return size;
        }
        // 假设方法 containsKey、isEnd、get、put 都已经实现了
        // 可以参考文章：https://leetcode.com/articles/implement-trie-prefix-tree/
    }

    public class Trie {

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // 假设方法 insert、search、searchPrefix 都已经实现了
        // 可以参考文章：https://leetcode.com/articles/implement-trie-prefix-tree/
        private String searchLongestPrefix(String word) {
            TrieNode node = root;
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char curLetter = word.charAt(i);
                if (node.containsKey(curLetter) && (node.getLinks() == 1) && (!node.isEnd())) {
                    prefix.append(curLetter);
                    node = node.get(curLetter);
                }
                else
                    return prefix.toString();

            }
            return prefix.toString();
        }
    }
}

