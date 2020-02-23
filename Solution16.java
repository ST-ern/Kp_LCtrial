/*

给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
找出 nums 中的三个整数，使得它们的和与 target 最接近。
返回这三个数的和。
假定每组输入只存在唯一答案。

例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

 */

import java.util.Arrays;
import java.util.List;

/**
 * @author stern
 * @date 2020/2/23 11:37
 */
public class Solution16 {
    /**
     * @author stern
     * @date 2020-02-23 12:47
     * @description
     *
     * 与T15类似，三数问题，for固定一个，剩下两个排序两端开始搜索
     *
     * 执行用时 : 5 ms, 在所有 Java 提交中击败了94.66%的用户
     * 内存消耗 : 38.6 MB, 在所有 Java 提交中击败了5.06%的用户
     *
    */
    public static int threeSumClosest(int[] nums, int target) {
        if(nums.length < 3) { return -1; } // 应该不会有
        int l,m,r;
        int addition = 0, tmp;
        int minDiff = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for(l=0; l<nums.length; l++) {
            m = l + 1;
            r = nums.length - 1;
            while(m<r) {
                tmp = nums[l]+nums[m]+nums[r];
                if(tmp==target) {
                    return target;
                } else if(tmp < target) {
                    if(minDiff > target - tmp) {
                        minDiff = target - tmp;
                        addition = tmp;
                    }
                    m++;
                } else {
                    if(minDiff > tmp - target) {
                        minDiff = tmp - target;
                        addition = tmp;
                    }
                    r--;
                }
            }
        }
        return addition;
    }

    public static void main(String[] args) {
        int[] num = {-1,2,1,4};
        int target = 1;
        int res = threeSumClosest(num, target);
        System.out.println(res);
    }
}
