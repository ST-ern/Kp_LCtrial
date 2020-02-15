/*

给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:
输入: 123
输出: 321

示例 2:
输入: -123
输出: -321

示例 3:
输入: 120
输出: 21

注意:
假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

 */


//import static java.lang.Math.pow;

/**
 * @author stern
 * @date 2020/2/15 20:48
 */
public class Solution7 {
    /**
     * @author stern
     * @date 2020-02-15 21:14
     * @description
     *
     * 执行用时 : 2 ms, 在所有 Java 提交中击败了51.64%的用户
     * 内存消耗 : 34.1 MB, 在所有 Java 提交中击败了13.15%的用户
     *
    */
    public static int reverse_1(int x) {
        int max = (int) (Math.pow(2, 31) -1);
        int min = -(int) (Math.pow(2, 31));
        int tmp;
        boolean lessThanZero;
        if(x < 0) {
            tmp = -x;
            lessThanZero = true;
        } else {
            tmp = x;
            lessThanZero = false;
        }
        String s = Integer.toString(tmp);
        StringBuilder res = new StringBuilder();
        for(int i=0; i< s.length(); i++) {
            res.append(s.charAt(s.length()-i-1));
        }

        try {
            if (lessThanZero) {
                tmp = -Integer.parseInt(res.toString());
            } else {
                tmp = Integer.parseInt(res.toString());
            }
        } catch (NumberFormatException ignored) {
            return 0;
        }
        return tmp;
    }

    /**
     * @author LeetCode
     * @date 2020-02-15 21:16
     * @description
     *
     * 思路
     * 我们可以一次构建反转整数的一位数字。
     * 在这样做的时候，我们可以预先检查向原整数附加另一位数字是否会导致溢出。
     *
     * 算法
     * 反转整数的方法可以与反转字符串进行类比。
     * 我们想重复“弹出” x 的最后一位数字，
     * 并将它“推入”到 rev 的后面。
     * 最后，rev 将与 x 相反。
     * 要在没有辅助堆栈 / 数组的帮助下 “弹出” 和 “推入” 数字，我们可以使用数学方法。
     *
     * //pop operation:
     * pop = x % 10;
     * x /= 10;
     * //push operation:
     * temp = rev * 10 + pop;
     * rev = temp;
     *
     * 但是，这种方法很危险，因为当 temp = rev⋅10 + pop 时会导致溢出。
     * 幸运的是，事先检查这个语句是否会导致溢出很容易。
     *
     * 执行用时 : 1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 : 39.7 MB, 在所有 Java 提交中击败了5.03%的用户
     *
    */
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        int num = 1534236469;
        int result = reverse(num);
        System.out.println(result);
    }
}
