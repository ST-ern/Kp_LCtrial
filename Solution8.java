/*

请你来实现一个 atoi 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
当我们寻找到的第一个非空字符为正或者负号时，
则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，
它们对于函数不应该造成影响。

注意：
假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，
则你的函数不需要进行转换。
在任何情况下，若函数不能进行有效的转换时，请返回 0。

说明：
假设我们的环境只能存储 32 位大小的有符号整数，
那么其数值范围为 [−231,  231 − 1]。
如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。

示例 1:
输入: "42"
输出: 42

示例 2:
输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。

示例 3:
输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。

示例 4:
输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。

示例 5:
输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     因此返回 INT_MIN (−2^31) 。

 */



/**
 * @author stern
 * @date 2020/2/16 12:44
 */
public class Solution8 {
    /**
     * @author stern
     * @date 2020-02-16 15:50
     * @description
     *
     * 格外！注意！各种各样的边界情况！
     *
     * 执行用时 : 2 ms, 在所有 Java 提交中击败了96.40%的用户
     * 内存消耗 : 42.2 MB, 在所有 Java 提交中击败了5.01%的用户
     *
    */
    public static int myAtoi_1(String str) {
        long result = 0;
        int count = 0;
        boolean lessThanZero = false;

        // 去除开始空格，正负数区分，排除非数字情况
        if(str.equals("")) { return 0; }
        while(count < str.length() && str.charAt(count) == ' ') {
            count++;
        }
        if(count == str.length()) { return 0; }
        if(!Character.isDigit(str.charAt(count))) {
            if(str.charAt(count) == '-') {
                lessThanZero = true;
                count++;
            } else if(str.charAt(count) == '+') {
                lessThanZero = false;
                count++;
            } else {
                return 0;
            }
        }

        while (count < str.length() && Character.isDigit(str.charAt(count))) {
            result = result * 10 + (long)(str.charAt(count) - '0');
            count++;
            if(result > Integer.MAX_VALUE) {
                if(lessThanZero) { return Integer.MIN_VALUE; }
                else { return Integer.MAX_VALUE; }
            }
        }

        if(lessThanZero) {
            if(-result <= Integer.MIN_VALUE) { return Integer.MIN_VALUE; }
            else { return -(int)result; }
        } else {
            if(-result >= Integer.MAX_VALUE) { return Integer.MAX_VALUE; }
            else { return (int)result; }
        }
    }

    /**
     * @author liweiwei1419(Leetcode user)
     * @description
     * 这个问题其实没有过多的技巧，考察的是细心和耐心，并且需要不断地调试。
     * 在这里我简单罗列几个要点。
     *
     * Java 、Python 和 C++ 字符串的设计都是不可变的，
     * 即使用 trim() 会产生新的变量，因此我们尽量不使用库函数，
     * 使用一个变量 index 去做线性遍历，这样遍历完成以后就得到转换以后的数值。
     *
     * 根据示例 1，需要去掉前导空格；
     * 根据示例 2，需要判断第 1 个字符为 + 和 - 的情况，因此，可以设计一个变量 sign，初始化的时候为 1，如果遇到 - ，将 sign 修正为 -1；
     * 判断是否是数字，可以使用字符的 ASCII 码数值进行比较，即 0 <= c <= '9'；
     * 根据示例 3 和示例 4 ，在遇到第 1 个不是数字的字符的情况下，就得退出循环；
     * 根据示例 5，如果转换以后的数字超过了 int 类型的范围，需要截取。这里需要将结果 res 变量设计为 long 类型，注意：由于输入的字符串转换以后也有可能超过 long 类型，因此需要在循环内部就判断是否越界，只要越界就退出循环，这样也可以减少不必要的计算；
     * 因为涉及下标访问，因此全程需要考虑数组下标是否越界的情况。
     *
     * 特别注意：
     * 1、由于题目中说“环境只能保存 32 位整数”，
     * 因此这里在每一轮循环之前先要检查乘以 1010 以后是否溢出，具体细节请见编码；
     * 2、Python 代码在负数的时候整除和取模都不太一样，在这里先做硬编码处理。
     * (TA用的是python编程)
    */

    public static void main(String[] args) {
        String num1 = "";
        String num2 = " ";
        String num3 = "00000";
        String num4 = "999999999";
        String num5 = "  +23";
        String num6 = " -0000012";
        String num7 = "  0000000000012345678";
        int result = myAtoi_1(num1);
        System.out.println(result);
        System.out.println(Integer.MIN_VALUE);
    }
}
