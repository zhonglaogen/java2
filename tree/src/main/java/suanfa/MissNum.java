package suanfa;

import java.util.ArrayList;
import java.util.List;

public class MissNum {
    public static List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> res=new ArrayList<Integer>();
        int n=nums.length;
        for(int i=1;i<n;i++){
            nums[nums[i]%n]=nums[nums[i]%n]+n;
        }
        for(int i=1;i<n;i++){
            if(nums[i]/n==0){
                res.add(i);
            }
        }
        if(nums[0]/n==0){
            res.add(n);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a={4,3,2,7,8,2,3,1};
        List<Integer> disappearedNumbers = findDisappearedNumbers(a);
        System.out.println(disappearedNumbers);
    }
}
