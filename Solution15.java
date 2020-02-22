/*

给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
注意：答案中不可以包含重复的三元组。

示例：
给定数组 nums = [-1, 0, 1, 2, -1, -4]，
满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]

 */


import java.util.*;

/**
 * @author stern
 * @date 2020/2/22 11:47
 */
public class Solution15 {
    /**
     * @author stern 借鉴Leetcode user：吴彦祖
     * @date 2020-02-22 13:02
     * @description
     *
     * 执行用时 : 33 ms, 在所有 Java 提交中击败了90.89%的用户
     * 内存消耗 : 45.7 MB, 在所有 Java 提交中击败了95.26%的用户
    */
    public static List<List<Integer>> threeSum(int[] nums) {
        if(nums.length == 0 || nums.length == 1 || nums.length == 2) {
            return Collections.emptyList(); // 返回空List
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp;
        Arrays.sort(nums);
        boolean single = true;
        int left, right;
        int plus;
        // 遍历nums，三个坐标分别为 i, left, right（从左到右）
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > 0) {
                return res;
            }
            left = i + 1;
            right =  nums.length - 1;
            while(left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[left]);
                    tmp.add(nums[right]);
                    res.add(tmp);
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    single = false;
                } else {
                    single = true;
                }

                // 跳过重复
                plus = 0;
                if(single) {
                    while(left+plus<nums.length &&
                            nums[left+plus]==nums[left]) {
                        plus++;
                    }
                    left += plus;
                } else {
                    while (right - plus >= 0 &&
                            nums[right - plus] == nums[right]) {
                        plus++;
                    }
                    right -= plus;
                }
            }
            plus = 0;
            while(i+plus<nums.length && nums[i+plus]==nums[i]) {
                plus++;
            }
            i += plus - 1;
        }

        /*
        失败的过程：
        while(right - left >= 2) {
            med = left+1;
            int addition = nums[left] + nums[right];
            for(;med<right; med++) {
                if(addition + nums[med] == 0) {
                    tmp = new ArrayList<>();
                    tmp.add(nums[left]);
                    tmp.add(nums[med]);
                    tmp.add(nums[right]);
                    res.add(tmp);
                } else if(addition + nums[med] > 0) {
                    single = false;
                } else {
                    single = true;
                }
                plus = 0;
                while(med+plus<right && nums[med+plus]==nums[med]) {
                    plus++;
                }
                med += plus - 1;
            }
            plus = 0;
            if(single) {
                while(left+plus<nums.length && nums[left+plus]==nums[left]) {
                    plus++;
                }
                left += plus;
            } else {
                while(right-plus>=0 && nums[right-plus]==nums[right]) {
                    plus++;
                }
                right -= plus;
            }
        }
         */
        return res;
    }

    public static void main(String[] args) {
        int[] num = {0,0,0};
        int[] num2 = {0,0,0,0, 0};
        int[] num3 = {-2, 0, 1,1 ,2};
        int[] num4 = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        List<List<Integer>> res = threeSum(num4);
        System.out.println(res);
    }
}
