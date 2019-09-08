package suanfa;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 */
public class Sum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<Integer, Integer>();
        for(int i=0;i<nums.length;i++){
            int c=target-nums[i];
            if (map.containsKey(c)){
                return new int[]{c,nums[i]};
            }
            map.put(nums[i],i);
        }
        return null;

    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int  target = 9;
        int[] ints = twoSum(nums, target);
        System.out.println(ints[1]+""+ints[0]);
    }
}
