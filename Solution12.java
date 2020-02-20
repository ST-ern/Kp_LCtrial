/*

罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。
但也存在特例，例如 4 不写做 IIII，而是 IV。
数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
同样地，数字 9 表示为 IX。
这个特殊的规则只适用于以下六种情况：
I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。

示例 1:
输入: 3
输出: "III"

示例 2:
输入: 4
输出: "IV"

示例 3:
输入: 9
输出: "IX"

示例 4:
输入: 58
输出: "LVIII"
解释: L = 50, V = 5, III = 3.

示例 5:
输入: 1994
输出: "MCMXCIV"
解释: M = 1000, CM = 900, XC = 90, IV = 4.

 */

import java.util.HashMap;
import java.util.Map;

/**
 * @author stern
 * @date 2020/2/20 12:06
 */
public class Solution12 {
    /**
     * @author stern
     * @date 2020-02-20 12:37
     * @description
     *
     * 执行用时 : 6 ms, 在所有 Java 提交中击败了51.82%的用户
     * 内存消耗 : 41.3 MB, 在所有 Java 提交中击败了5.03%的用户
    */
    public static String intToRoman_1(int num) {
        String s_num = Integer.toString(num);
        StringBuilder res = new StringBuilder();
        int count = 0;
        for(int i=s_num.length()-1; i>=0; i--,count++) {
            int c = s_num.charAt(i) - '0';
            if(c==0) {
                continue;
            } else if(c>0 && c<=3) {
                while(c>0) {
                    switch (count) {
                        case 0:
                            res.append('I');
                            break;
                        case 1:
                            res.append('X');
                            break;
                        case 2:
                            res.append('C');
                            break;
                        default:
                            res.append('M');
                    }
                    c--;
                }
            } else if(c==4) {
                switch (count) {
                    case 0:
                        res.append("VI");
                        break;
                    case 1:
                        res.append("LX");
                        break;
                    default:
                        res.append("DC");
                }
            } else if(c==9) {
                switch (count) {
                    case 0:
                        res.append("XI");
                        break;
                    case 1:
                        res.append("CX");
                        break;
                    default:
                        res.append("MC");
                }
            } else {
                c -= 5;
                while(c>0) {
                    switch (count) {
                        case 0:
                            res.append('I');
                            break;
                        case 1:
                            res.append('X');
                            break;
                        default:
                            res.append('C');
                    }
                    c--;
                }
                switch (count) {
                    case 0:
                        res.append('V');
                        break;
                    case 1:
                        res.append('L');
                        break;
                    default:
                        res.append('D');
                }
            }
        }
        return res.reverse().toString();
    }



    /**
     * @author Leetcode user: liweiwei1419
     * @date 2020-02-20 12:39
     * @description
     *
     * 贪婪算法
     *
     * 说明：其实在题目中已经强调了一些特例，
     * 出现 4、9、40、90、400、900
     * （4000、9000 不讨论了，题目测试用例中有说，不会超过 3999）
     * 的情况比较特殊一些，做的是减法
     * 把它们也加入到“罗马数字”与阿拉伯数字的对应关系表中， !!!!!
     * 并且按照从大到小的顺序排列。
     *
     * 罗马数字	阿拉伯数字
     * M	    1000
     * CM	    900
     * D	    500
     * CD	    400
     * C	    100
     * XC	    90
     * L	    50
     * XL	    40
     * X	    10
     * IX	    9
     * V	    5
     * IV	    4
     * I	    1
     * 于是，“将整数转换为罗马数字”的过程，
     * 就是用上面这张表中右边的数字作为“加法因子”去分解一个整数，
     * 目的是“分解的整数个数”尽可能少，
     * 因此，对于这道问题，类似于用最少的纸币凑成一个整数，贪心算法的规则如下：
     *
     * 每一步都使用当前较大的罗马数字作为加法因子，最后得到罗马数字表示就是长度最少的。
     *
     * 执行用时 : 5 ms, 在所有 Java 提交中击败了90.57%的用户
     * 内存消耗 : 41.2 MB, 在所有 Java 提交中击败了5.03%的用户
     *
    */
    public static String intToRoman(int num) {
        // 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中
        // 并且按照阿拉伯数字的大小降序排列，这是贪心选择思想
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (index < 13) {
            // 特别注意：这里是等号
            while (num >= nums[index]) {
                // 注意：这里是等于号，表示尽量使用大的"面值"
                stringBuilder.append(romans[index]);
                num -= nums[index];
            }
            index++;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        int num = 10;
        String res = intToRoman(num);
        System.out.println(res);
    }
}
