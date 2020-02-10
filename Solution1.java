/*

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:
给定 nums = [2, 7, 11, 15], target = 9
因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]

 */



import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author stern
 * @date 2020/2/9 12:24
 */
public class Solution1 {
    
    /**
     * @author stern
     * @date 2020-02-09 16:14
     * @description
     * 执行用时 : 2 ms
     * 内存消耗 : 38.2 MB (发现多次提交结果都不一样，只取第一次)
    */
    public static int[] twoSum_1(int[] nums, int target) {
        int[] copy = nums.clone();

        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while(nums[left] + nums[right] != target) {
            if(nums[left] + nums[right] < target) {
                left += 1;
            } else {
                right -= 1;
            }
        }

        int begin = search(copy, nums[left], -1);
        int end = search(copy, nums[right], begin);

        int[] result = {begin, end};
        Arrays.sort(result);
        return result;
    }

    public static int search(int[] nums, int target, int not) {
        for (int i=0; i<nums.length; i++){
            if(nums[i]==target && i!=not){
                return i;
            }
        }
        return -1;
    }


    /**
     * @author LeetCode
     * @date 2020-02-10 16:15
     * @description
     * 事实证明，我们可以一次完成。在进行迭代并将元素插入到表中的同时，我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素。如果它存在，那我们已经找到了对应解，并立即将其返回。
     * (当然也可以暴力破解)
     *
     * 执行用时 : 4 ms, 在所有 Java 提交中击败了72.62%的用户
     * 内存消耗 : 37.3 MB, 在所有 Java 提交中击败了49.71%的用户
     *
    */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    public static void main(String[] args){
        int[] nums = {13, 7, 6, 45, 21, 9, 101, 102};
        int target = 66;

        int[] result = twoSum_1(nums, target);
        String arr = Arrays.toString(result);
        System.out.println(arr);
    }
}


