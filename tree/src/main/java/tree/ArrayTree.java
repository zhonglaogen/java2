package tree;

/**
 * ArrayTree
 *
 * @author zhonglx
 * @version 1.0.0 2021/8/10 12:26
 */
public class ArrayTree {
    private static int[] getTreeArray(int[] nums) {
        int[] treeArray = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            int index = i -(i & -i) + 1;
            while (true) {
                treeArray[i] += nums[index];
                index++;
                if (index > i) {
                    break;
                }
            }
        }
        return treeArray;
    }

    private static int getSumValue(int[] treeArray, int n) {
        int sum = 0;
        while (n > 0) {
            sum += treeArray[n];
            n -= n & -n;
        }
        return sum;
    }

    private int updateValue(int[] nums, int[] treeArray, int index, int val) {
        int sub = val - nums[index];
        nums[index] = val;
        while (index < treeArray.length) {
            treeArray[index] += sub;
            index += index & -index;
        }
        return 0;
    }
}


